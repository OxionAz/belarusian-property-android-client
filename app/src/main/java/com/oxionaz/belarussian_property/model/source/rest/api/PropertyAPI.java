package com.oxionaz.belarussian_property.model.source.rest.api;

import com.google.gson.JsonObject;
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.RentFlatDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.RentFlatsContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.houses.RentHouseDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.houses.RentHousesContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.rooms.RentRoomDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.rooms.RentRoomsContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.areas.SaleAreaDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.areas.SaleAreasContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.SaleFlatDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.SaleFlatsContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.houses.SaleHouseDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.houses.SaleHousesContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.rooms.SaleRoomDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.rooms.SaleRoomsContainerDTO;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface PropertyAPI {

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/sale/flats")
    Observable<SaleFlatsContainerDTO> getSaleFlats(
            @Query("key") String key,
            @Query("user") String user,
            @Query("start") int start,
            @Query("count") int count,
            @Query("sort_date_asc") boolean sortDateASC,
            @Query("sort_date_desc") boolean sortDateDESC,
            @Query("sort_price_asc") boolean sortPriceASC,
            @Query("sort_price_desc") boolean sortPriceDESC,
            @Body JsonObject parameters);

    @GET("/api/sale/flat")
    Observable<SaleFlatDTO> getSaleFlat(
            @Query("key") String key,
            @Query("id") int id,
            @Query("price") String price);

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/sale/rooms")
    Observable<SaleRoomsContainerDTO> getSaleRooms(
            @Query("key") String key,
            @Query("user") String user,
            @Query("start") int start,
            @Query("count") int count,
            @Query("sort_date_asc") boolean sortDateASC,
            @Query("sort_date_desc") boolean sortDateDESC,
            @Query("sort_price_asc") boolean sortPriceASC,
            @Query("sort_price_desc") boolean sortPriceDESC,
            @Body JsonObject parameters);

    @GET("/api/sale/room")
    Observable<SaleRoomDTO> getSaleRoom(
            @Query("key") String key,
            @Query("id") int id,
            @Query("price") String price);

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/sale/houses")
    Observable<SaleHousesContainerDTO> getSaleHouses(
            @Query("key") String key,
            @Query("user") String user,
            @Query("start") int start,
            @Query("count") int count,
            @Query("sort_date_asc") boolean sortDateASC,
            @Query("sort_date_desc") boolean sortDateDESC,
            @Query("sort_price_asc") boolean sortPriceASC,
            @Query("sort_price_desc") boolean sortPriceDESC,
            @Body JsonObject parameters);

    @GET("/api/sale/house")
    Observable<SaleHouseDTO> getSaleHouse(
            @Query("key") String key,
            @Query("id") int id,
            @Query("price") String price);

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/sale/areas")
    Observable<SaleAreasContainerDTO> getSaleAreas(
            @Query("key") String key,
            @Query("user") String user,
            @Query("start") int start,
            @Query("count") int count,
            @Query("sort_date_asc") boolean sortDateASC,
            @Query("sort_date_desc") boolean sortDateDESC,
            @Query("sort_price_asc") boolean sortPriceASC,
            @Query("sort_price_desc") boolean sortPriceDESC,
            @Body JsonObject parameters);

    @GET("/api/sale/area")
    Observable<SaleAreaDTO> getSaleArea(
            @Query("key") String key,
            @Query("id") int id,
            @Query("price") String price);

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/rent/flats")
    Observable<RentFlatsContainerDTO> getRentFlats(
            @Query("key") String key,
            @Query("user") String user,
            @Query("start") int start,
            @Query("count") int count,
            @Query("sort_date_asc") boolean sortDateASC,
            @Query("sort_date_desc") boolean sortDateDESC,
            @Query("sort_price_asc") boolean sortPriceASC,
            @Query("sort_price_desc") boolean sortPriceDESC,
            @Body JsonObject parameters);

    @GET("/api/rent/flat")
    Observable<RentFlatDTO> getRentFlat(
            @Query("key") String key,
            @Query("id") int id,
            @Query("section") int section,
            @Query("price") String price);

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/rent/rooms")
    Observable<RentRoomsContainerDTO> getRentRooms(
            @Query("key") String key,
            @Query("user") String user,
            @Query("start") int start,
            @Query("count") int count,
            @Query("sort_date_asc") boolean sortDateASC,
            @Query("sort_date_desc") boolean sortDateDESC,
            @Query("sort_price_asc") boolean sortPriceASC,
            @Query("sort_price_desc") boolean sortPriceDESC,
            @Body JsonObject parameters);

    @GET("/api/rent/room")
    Observable<RentRoomDTO> getRentRoom(
            @Query("key") String key,
            @Query("id") int id,
            @Query("section") int section,
            @Query("price") String price);

    @Headers({"Content-type: application/json; charset=utf-8", "Accept: */*"})
    @POST("/api/rent/houses")
    Observable<RentHousesContainerDTO> getRentHouses(
            @Query("key") String key,
            @Query("user") String user,
            @Query("start") int start,
            @Query("count") int count,
            @Query("sort_date_asc") boolean sortDateASC,
            @Query("sort_date_desc") boolean sortDateDESC,
            @Query("sort_price_asc") boolean sortPriceASC,
            @Query("sort_price_desc") boolean sortPriceDESC,
            @Body JsonObject parameters);

    @GET("/api/rent/house")
    Observable<RentHouseDTO> getRentHouse(
            @Query("key") String key,
            @Query("id") int id,
            @Query("section") int section,
            @Query("price") String price);
}
