package com.oxionaz.belarussian_property.presenter;

import android.util.Log;
import com.google.gson.JsonObject;
import com.oxionaz.belarussian_property.other.Const;
import com.oxionaz.belarussian_property.view.fragments.PredictionFragmentView;
import java.net.ConnectException;
import retrofit2.HttpException;
import rx.Subscriber;
import rx.Subscription;

public class PredictionRentFlatsPresenter extends BasePropertyPresenter {

    private PredictionFragmentView view;

    public void onCreate(PredictionFragmentView view){
        this.view = view;
    }

    public void gePrediction(JsonObject parameters) {
        view.showLoading();
        Subscription subscription = dataManager.downloadPredictionRentFlatLong(
                Const.API_KEY, preferencesHelper.getPreference("user_id"), parameters)
                .subscribe(new Subscriber<Float>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            switch (((HttpException) e).code()){
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
                    public void onNext(Float prediction) {
                        view.showPrediction(prediction);
                    }
                });
        addSubscription(subscription);
    }
}
