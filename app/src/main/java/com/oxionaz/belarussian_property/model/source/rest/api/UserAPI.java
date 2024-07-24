package com.oxionaz.belarussian_property.model.source.rest.api;

import com.google.gson.JsonObject;
import com.oxionaz.belarussian_property.model.source.db.models.cache.FavoriteTable;
import com.oxionaz.belarussian_property.model.source.rest.dto.MessageDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.UserDTO;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface UserAPI {

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/user/log_in")
    Observable<UserDTO> logIn(
            @Query("key") String key,
            @Body JsonObject parameters);

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/user/log_up")
    Observable<UserDTO> logUp(
            @Query("key") String key,
            @Body JsonObject parameters);

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/bookmarks/save")
    Observable<MessageDTO> saveBookmarks(
            @Query("key") String key,
            @Query("user") String user,
            @Body List<FavoriteTable> favorites);

    @GET("/api/bookmarks")
    Observable<List<FavoriteTable>> getBookmarks(
            @Query("key") String key,
            @Query("user") String user);
}
