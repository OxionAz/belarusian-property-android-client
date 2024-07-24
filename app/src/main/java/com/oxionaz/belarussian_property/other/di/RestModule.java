package com.oxionaz.belarussian_property.other.di;

import com.oxionaz.belarussian_property.model.source.rest.RestClient;
import com.oxionaz.belarussian_property.model.source.rest.api.AnalysesAPI;
import com.oxionaz.belarussian_property.model.source.rest.api.CommentAPI;
import com.oxionaz.belarussian_property.model.source.rest.api.DictionaryAPI;
import com.oxionaz.belarussian_property.model.source.rest.api.ExchangeRatesAPI;
import com.oxionaz.belarussian_property.model.source.rest.api.PropertyAPI;
import com.oxionaz.belarussian_property.model.source.rest.api.UserAPI;
import com.oxionaz.belarussian_property.other.Const;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
class RestModule {

    @Provides
    @Singleton
    Retrofit provideRestClient(){
        return new RestClient(Const.BASE_URL).getRetrofit();
    }

    @Provides
    @Singleton
    PropertyAPI providePropertyAPI(Retrofit retrofit){
        return retrofit.create(PropertyAPI.class);
    }

    @Provides
    @Singleton
    AnalysesAPI provideAnalysesAPI(Retrofit retrofit){
        return retrofit.create(AnalysesAPI.class);
    }

    @Provides
    @Singleton
    DictionaryAPI provideDictionaryAPI(Retrofit retrofit){
        return retrofit.create(DictionaryAPI.class);
    }

    @Provides
    @Singleton
    UserAPI provideUserAPI(Retrofit retrofit){
        return retrofit.create(UserAPI.class);
    }

    @Provides
    @Singleton
    CommentAPI provideCommentAPI(Retrofit retrofit){
        return retrofit.create(CommentAPI.class);
    }

    @Provides
    @Singleton
    ExchangeRatesAPI provideExchangeRatesAPI(){
        return new RestClient(Const.EXCHANGE_RATES_URL).getRetrofit().create(ExchangeRatesAPI.class);
    }
}
