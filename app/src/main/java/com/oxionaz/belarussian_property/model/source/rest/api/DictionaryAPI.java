package com.oxionaz.belarussian_property.model.source.rest.api;

import com.google.gson.JsonObject;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface DictionaryAPI {

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/dictionary/property")
    Observable<List<String>> getPropertyValues(
            @Query("key") String key,
            @Body JsonObject parameters);

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/dictionary/property/multi")
    Observable<List<String>> getPropertyMultiValues(
            @Query("key") String key,
            @Body JsonObject parameters);

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/dictionary/model")
    Observable<List<String>> getModelValues(
            @Query("key") String key,
            @Body JsonObject parameters);
}
