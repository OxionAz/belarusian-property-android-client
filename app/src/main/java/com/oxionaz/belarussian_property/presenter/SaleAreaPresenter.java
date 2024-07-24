package com.oxionaz.belarussian_property.presenter;

import android.util.Log;
import com.oxionaz.belarussian_property.model.source.rest.dto.areas.SaleAreaDTO;
import com.oxionaz.belarussian_property.other.Const;
import com.oxionaz.belarussian_property.presenter.mapping.PropertyItem;
import com.oxionaz.belarussian_property.view.activities.PropertyActivityView;
import java.net.ConnectException;
import retrofit2.HttpException;
import rx.Subscriber;
import rx.Subscription;

public class SaleAreaPresenter extends BasePropertyPresenter {

    private PropertyActivityView view;

    public void onCreate(PropertyActivityView view){
        this.view = view;
    }

    public void downloadPropertyInfo(Integer id, String price) {
        view.showLoading();
        Subscription subscription = dataManager.downloadArea(Const.API_KEY, id, price)
            .subscribe(new Subscriber<SaleAreaDTO>() {
                @Override
                public void onCompleted() {}

                @Override
                public void onError(Throwable e) {
                    if (e instanceof HttpException) {
                        switch (((HttpException) e).code()){
                            case 404: view.showNotFoundMessage("Объявление устарело"); break;
                            case 505: view.showErrorMessage("Сервер временно не доступен"); break;
                            default: view.showErrorMessage("Что-то пошло не так..."); break;
                        }
                    } else if (e instanceof ConnectException) {
                        view.showErrorMessage("Отсутствует интернет подключение");
                    } else {
                        Log.e("DATA ERROR", e.getMessage() + " - " + e.getClass());
                    }
                }

                @Override
                public void onNext(SaleAreaDTO saleAreaDTO) {
                    view.showPropertyInfo(saleAreaDTO);
                }
            });
        addSubscription(subscription);
    }

    public void saveFavorite(PropertyItem item) {
        dataManager.saveFavorite("sale_areas", item);
    }

    public void deleteFavorite(PropertyItem item) {
        dataManager.clearFavorite("sale_areas", item.getId(), item.getSection(), item.getPrice());
    }
}
