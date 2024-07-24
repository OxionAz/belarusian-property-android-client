package com.oxionaz.belarussian_property.presenter;

import android.util.Log;
import com.oxionaz.belarussian_property.model.source.db.models.cache.FavoriteTable;
import com.oxionaz.belarussian_property.view.fragments.FavoritesFragmentView;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public class FavoritePresenter extends BasePropertyPresenter {

    private FavoritesFragmentView view;

    public void onCreate(FavoritesFragmentView view){
        this.view = view;
    }

    public void getFavorites(){
        Log.e("FAVORITES  ", String.valueOf(SQLite.select().from(FavoriteTable.class).queryList().size()));
        getFavoritesFromCache("sale_flats");
        getFavoritesFromCache("sale_rooms");
        getFavoritesFromCache("sale_houses");
        getFavoritesFromCache("sale_areas");
        getFavoritesFromCache("rent_flats");
        getFavoritesFromCache("rent_rooms");
        getFavoritesFromCache("rent_houses");
    }

    private void getFavoritesFromCache(String property) {
        Subscription subscription = dataManager.getFavorites(property)
                .flatMap(Observable::from)
                .map(f -> {
                    f.setDate(mapperDTO.transformDate(f.getModifiedDate()));
                    f.setCost(mapperDTO.transformPrice(f.getPrice()));
                    return f;
                })
                .toList()
                .subscribe(new Subscriber<List<FavoriteTable>>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        Log.e("FAVORITES DATA", e.getMessage());
                    }

                    @Override
                    public void onNext(List<FavoriteTable> favorites) {
                        Log.e("DATA ", property + " - " + String.valueOf(favorites.size()));
                        view.showFavoritesList(property, favorites);
                    }
                });
        addSubscription(subscription);
    }

    public void deleteFavorite(String property, FavoriteTable favorite) {
        dataManager.clearFavorite(property, favorite.getId(), favorite.getSection(), favorite.getPrice());
    }

    public void clearAll() {
        dataManager.clearAllFavorites();
    }
}
