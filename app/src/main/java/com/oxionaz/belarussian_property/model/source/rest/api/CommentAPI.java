package com.oxionaz.belarussian_property.model.source.rest.api;

import com.google.gson.JsonObject;
import com.oxionaz.belarussian_property.model.source.rest.dto.MessageDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.comments.CommentsContainerDTO;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Observable;

public interface CommentAPI {

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/comment/add")
    Observable<MessageDTO> addComment(
            @Query("key") String key,
            @Body JsonObject parameters);

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/comment/del")
    Observable<MessageDTO> deleteComment(
            @Query("key") String key,
            @Body JsonObject parameters);

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @PUT("/api/comment/upd")
    Observable<MessageDTO> updateComment(
            @Query("key") String key,
            @Body JsonObject parameters);

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/comments")
    Observable<CommentsContainerDTO> getComments(
            @Query("key") String key,
            @Query("start") int start,
            @Query("count") int count,
            @Body JsonObject parameters);
}
