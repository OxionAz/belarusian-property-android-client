package com.oxionaz.belarussian_property.presenter;

import android.util.Log;
import android.widget.EditText;
import com.google.gson.JsonObject;
import com.oxionaz.belarussian_property.model.source.db.models.cache.FavoriteTable;
import com.oxionaz.belarussian_property.model.source.rest.dto.UserDTO;
import com.oxionaz.belarussian_property.other.Const;
import com.oxionaz.belarussian_property.view.fragments.UserFragmentView;
import org.json.JSONObject;
import java.net.ConnectException;
import java.util.List;

import retrofit2.HttpException;
import rx.Subscriber;
import rx.Subscription;

public class UserPresenter extends BasePropertyPresenter {

    private UserFragmentView view;

    public void onCreate(UserFragmentView view){
        this.view = view;
    }

    public void logIn(JsonObject parameters) {
        Subscription subscription = dataManager.logIn(Const.API_KEY, parameters)
                .subscribe(new Subscriber<UserDTO>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            switch (((HttpException) e).code()){
                                case 401: view.showErrorMessage("Неверный логин или пароль"); break;
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
                    public void onNext(UserDTO data) {
                        getUserData(data);
                    }
                });
        addSubscription(subscription);
    }

    public void logUp(JsonObject parameters, EditText login, EditText email) {
        Subscription subscription = dataManager.logUp(Const.API_KEY, parameters)
                .subscribe(new Subscriber<UserDTO>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof HttpException) {
                            switch (((HttpException) e).code()){
                                case 409:
                                    try {
                                        JSONObject response = new JSONObject(((HttpException) e).response().errorBody().source().readUtf8());
                                        if (!response.getString("login").isEmpty()) login.setError("Введенный логин уже существует");
                                        if (!response.getString("email").isEmpty()) email.setError("Введенный email уже существует");
                                    } catch (Exception e1) {
                                        e1.printStackTrace();
                                    }
                                    break;
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
                    public void onNext(UserDTO data) {
                        view.showMainActivity(data);
                    }
                });
        addSubscription(subscription);
    }

    private void getUserData(UserDTO data) {
        Subscription subscription = dataManager.downloadFavorites(Const.API_KEY, data.getId().toString())
                .subscribe(new Subscriber<List<FavoriteTable>>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        view.showMainActivity(data);
                    }

                    @Override
                    public void onNext(List<FavoriteTable> favoriteTables) {
                        view.showMainActivity(data);
                    }
                });
        addSubscription(subscription);
    }
}
