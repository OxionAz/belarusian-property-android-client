package com.oxionaz.belarussian_property.model.source.rest.api;

import com.google.gson.JsonObject;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface AnalysesAPI {

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/models/sale/flat")
    Observable<Float> getPredictionSaleFlat(
            @Query("key") String key,
            @Query("user") String user,
            @Body JsonObject parameters);

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/models/rent/flat/long")
    Observable<Float> getPredictionRentFlatLong(
            @Query("key") String key,
            @Query("user") String user,
            @Body JsonObject parameters);
}
