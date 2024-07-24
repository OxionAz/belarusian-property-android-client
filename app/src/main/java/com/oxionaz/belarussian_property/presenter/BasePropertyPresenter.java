package com.oxionaz.belarussian_property.presenter;

import com.oxionaz.belarussian_property.model.DataManager;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.other.util.PreferencesHelper;
import com.oxionaz.belarussian_property.presenter.mapping.MapperDTO;

import javax.inject.Inject;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePropertyPresenter implements Presenter {

    @Inject
    MapperDTO mapperDTO;

    @Inject
    PreferencesHelper preferencesHelper;

    @Inject
    DataManager dataManager;

    @Inject
    CompositeSubscription compositeSubscription;

    public BasePropertyPresenter(){
        App.getAppComponent().inject(this);
    }

    // Clear all subscriptions
    @Override
    public void onStop() {
        compositeSubscription.clear();
    }

    // Add subscription
    void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }
}
