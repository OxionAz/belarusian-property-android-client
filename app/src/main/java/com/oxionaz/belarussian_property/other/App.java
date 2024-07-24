package com.oxionaz.belarussian_property.other;

import android.app.Application;
import com.oxionaz.belarussian_property.other.di.AppComponent;
import com.oxionaz.belarussian_property.other.di.DaggerAppComponent;
import com.oxionaz.belarussian_property.other.di.ServiceHelperModule;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
        appComponent = buildComponent();
    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .serviceHelperModule(new ServiceHelperModule(this))
                .build();
    }
}