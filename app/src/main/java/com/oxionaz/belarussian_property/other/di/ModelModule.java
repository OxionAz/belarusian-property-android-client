package com.oxionaz.belarussian_property.other.di;

import com.oxionaz.belarussian_property.model.source.db.Database;
import com.oxionaz.belarussian_property.model.source.db.DatabaseHelper;
import com.oxionaz.belarussian_property.model.source.rest.Rest;
import com.oxionaz.belarussian_property.model.source.rest.RestService;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
class ModelModule {

    @Provides
    @Singleton
    Rest provideRestService(){
        return new RestService();
    }

    @Provides
    @Singleton
    Database provideDatabaseHelper(){
        return new DatabaseHelper();
    }

}
