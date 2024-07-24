package com.oxionaz.belarussian_property.other.di;

import android.content.Context;
import android.support.annotation.NonNull;

import com.oxionaz.belarussian_property.other.Const;
import com.oxionaz.belarussian_property.other.util.ImageDownloader;
import com.oxionaz.belarussian_property.other.util.PreferencesHelper;
import com.oxionaz.belarussian_property.presenter.mapping.MapperDTO;

import javax.inject.Named;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class ServiceHelperModule {

    private Context appContext;

    public ServiceHelperModule(@NonNull Context context){ this.appContext = context; }

    @Provides
    @Singleton
    Context provideAppContext() {
        return appContext;
    }

    @Provides
    @Singleton
    @Named(Const.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return AndroidSchedulers.mainThread();
    }


    @Provides
    @Singleton
    @Named(Const.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper() {
        return new PreferencesHelper();
    }

    @Provides
    @Singleton
    MapperDTO provideMapperDTO() {
        return new MapperDTO();
    }

    @Provides
    @Singleton
    ImageDownloader provideImageDownloader() {
        return new ImageDownloader();
    }
}
