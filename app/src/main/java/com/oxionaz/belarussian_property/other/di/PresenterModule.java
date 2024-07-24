package com.oxionaz.belarussian_property.other.di;

import com.oxionaz.belarussian_property.model.DataManager;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

@Module
class PresenterModule {

    @Provides
    @Singleton
    DataManager provideDataManager(){
        return new DataManager();
    }

    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }
}
