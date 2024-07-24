package com.oxionaz.belarussian_property.model.source.rest.api;

import com.oxionaz.belarussian_property.model.source.rest.dto.ExchangeRatesDTO;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface ExchangeRatesAPI {

    @GET("/API/ExRates/Rates/{rate}?ParamMode=2")
    Observable<ExchangeRatesDTO> getExchangeRate(@Path("rate") String rate);
}
