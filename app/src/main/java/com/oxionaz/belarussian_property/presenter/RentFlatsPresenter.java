package com.oxionaz.belarussian_property.presenter;

import android.util.Log;
import com.google.gson.JsonObject;
import com.oxionaz.belarussian_property.model.source.db.models.InfoTable;
import com.oxionaz.belarussian_property.model.source.db.models.InfoTable_Table;
import com.oxionaz.belarussian_property.model.source.db.models.cache.rent.RentFlats;
import com.oxionaz.belarussian_property.other.Const;
import com.oxionaz.belarussian_property.presenter.mapping.PropertyItem;
import com.oxionaz.belarussian_property.view.adapters.FavoriteData;
import com.oxionaz.belarussian_property.view.fragments.PropertyFragmentView;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import java.net.ConnectException;
import java.util.List;
import retrofit2.HttpException;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public class RentFlatsPresenter extends BasePropertyPresenter {

    private PropertyFragmentView view;
    private InfoTable info;
    private JsonObject parameters;

    public void onCreate(PropertyFragmentView view){
        this.view = view;
    }

    public void firstLoad(boolean value){
        if (value) view.showLoading(true);
        parameters = dataManager.getParameters("rent_flats");
        if (SQLite.selectCountOf().from(RentFlats.class).hasData()) {
            getPropertiesFromCache();
        } else {
            downloadProperties(false);
        }
        getFavoritesFromCache();
    }

    public void nextLoad(boolean update){
       if (info.getNext() != null) {
           downloadProperties(update);
       } else {
           view.disableFooter();
       }
    }

    public void updateList(boolean value){
        dataManager.clearPropertyTable("rent_flats", RentFlats.class);
        info = null;
        firstLoad(value);
    }

    private void getPropertiesFromCache() {
        Subscription subscription = dataManager.getRentFlats()
                .doOnNext(rentFlatsContainerDTO -> setInfo(rentFlatsContainerDTO.getInfo()))
                .flatMap(rentFlatsContainerDTO -> Observable.from(rentFlatsContainerDTO.getRentFlats()))
                .map(mapperDTO::mapProperty)
                .toList()
                .subscribe(new Subscriber<List<PropertyItem>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showErrorMessage(e.getMessage());
                        view.showLoading(false);
                    }

                    @Override
                    public void onNext(List<PropertyItem> propertyItems) {
                        view.showPropertyList(propertyItems,
                                SQLite.select().from(InfoTable.class)
                                        .where(InfoTable_Table.property.is("rent_flats"))
                                        .querySingle());
                        view.showLoading(false);
                    }
                });
        addSubscription(subscription);
    }

    private void downloadProperties(boolean update) {
        Subscription subscription = dataManager
                .downloadRentFlats(
                        Const.API_KEY,
                        preferencesHelper.getPreference("user_id"),
                        info != null ? info.getNext() : 0, 30,
                        preferencesHelper.getFragmentPreference("rent_flats", "date_asc"),
                        preferencesHelper.getFragmentPreference("rent_flats", "date_desc"),
                        preferencesHelper.getFragmentPreference("rent_flats", "price_asc"),
                        preferencesHelper.getFragmentPreference("rent_flats", "price_desc"),
                        parameters)
                .doOnNext(rentFlatsContainerDTO -> setInfo(rentFlatsContainerDTO.getInfo()))
                .flatMap(rentFlatsContainerDTO -> Observable.from(rentFlatsContainerDTO.getRentFlats()))
                .map(mapperDTO::mapProperty)
                .toList()
                .subscribe(new Subscriber<List<PropertyItem>>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            switch (((HttpException) e).code()){
                                case 404: view.showErrorMessage("Не найдено! Попробуйте изменить условия поиска"); break;
                                case 505: view.showErrorMessage("Сервер временно не доступен"); break;
                                default: view.showErrorMessage("Что-то пошло не так..."); break;
                            }
                        } else if (e instanceof ConnectException) {
                            view.showErrorMessage("Отсутствует интернет подключение");
                        } else {
                            Log.e("DATA ERROR", e.getMessage() + " - " + e.getClass());
                        }
                        if (update) {
                            view.updatePropertyList(null, true);
                            view.showLoading(false);
                        } else {
                            view.showLoading(false);
                        }
                    }

                    @Override
                    public void onNext(List<PropertyItem> propertyItems) {
                       if (update) {
                           view.updatePropertyList(propertyItems, true);
                           view.showLoading(false);
                       } else {
                           view.showPropertyList(propertyItems,
                                   SQLite.select().from(InfoTable.class)
                                           .where(InfoTable_Table.property.is("rent_flats"))
                                           .querySingle());
                           view.showLoading(false);
                       }
                    }
                });
        addSubscription(subscription);
    }

    public void getFavoritesFromCache() {
        Subscription subscription = dataManager.getFavoritesData("rent_flats")
                .subscribe(new Subscriber<List<FavoriteData>>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        Log.e("FAVORITES DATA", e.getMessage());
                    }

                    @Override
                    public void onNext(List<FavoriteData> favorites) {
                        view.updateFavoriteList(favorites);
                    }
                });
        addSubscription(subscription);
    }

    public void saveFavorite(PropertyItem item) {
        dataManager.saveFavorite("rent_flats", item);
    }

    public void deleteFavorite(FavoriteData favorite) {
        dataManager.clearFavorite("rent_flats", favorite.id, favorite.section, favorite.price);
    }

    public void setInfo(InfoTable info) {
        this.info = info;
    }
}
