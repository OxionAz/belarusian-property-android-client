package com.oxionaz.belarussian_property.model;

import com.google.gson.JsonObject;
import com.oxionaz.belarussian_property.model.source.db.models.cache.FavoriteTable;
import com.oxionaz.belarussian_property.model.source.rest.dto.ExchangeRatesDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.MessageDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.UserDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.areas.SaleAreaDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.areas.SaleAreasContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.comments.CommentsContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.RentFlatDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.RentFlatsContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.SaleFlatDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.SaleFlatsContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.houses.RentHouseDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.houses.RentHousesContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.houses.SaleHouseDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.houses.SaleHousesContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.rooms.RentRoomDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.rooms.RentRoomsContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.rooms.SaleRoomDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.rooms.SaleRoomsContainerDTO;
import com.oxionaz.belarussian_property.presenter.mapping.PropertyItem;
import com.oxionaz.belarussian_property.view.adapters.FavoriteData;
import java.util.List;
import rx.Observable;

public interface Model {

    Observable<SaleFlatsContainerDTO> downloadFlats(String key, String user, int start, int count,
                                                    boolean sortDateASC, boolean sortDateDESC,
                                                    boolean sortPriceASC, boolean sortPriceDESC,
                                                    JsonObject parameters);
    Observable<SaleFlatDTO> downloadFlat(String key, int id, String price);
    Observable<SaleRoomsContainerDTO> downloadRooms(String key, String user, int start, int count,
                                                    boolean sortDateASC, boolean sortDateDESC,
                                                    boolean sortPriceASC, boolean sortPriceDESC,
                                                    JsonObject parameters);
    Observable<SaleRoomDTO> downloadRoom(String key, int id, String price);
    Observable<SaleHousesContainerDTO> downloadHouses(String key, String user, int start, int count,
                                                      boolean sortDateASC, boolean sortDateDESC,
                                                      boolean sortPriceASC, boolean sortPriceDESC,
                                                      JsonObject parameters);
    Observable<SaleHouseDTO> downloadHouse(String key, int id, String price);
    Observable<SaleAreasContainerDTO> downloadAreas(String key, String user, int start, int count,
                                                    boolean sortDateASC, boolean sortDateDESC,
                                                    boolean sortPriceASC, boolean sortPriceDESC,
                                                    JsonObject parameters);
    Observable<SaleAreaDTO> downloadArea(String key, int id, String price);
    Observable<RentFlatsContainerDTO> downloadRentFlats(String key, String user, int start, int count,
                                                        boolean sortDateASC, boolean sortDateDESC,
                                                        boolean sortPriceASC, boolean sortPriceDESC,
                                                        JsonObject parameters);
    Observable<RentFlatDTO> downloadRentFlat(String key, int id, int section, String price);
    Observable<RentRoomsContainerDTO> downloadRentRooms(String key, String user, int start, int count,
                                                        boolean sortDateASC, boolean sortDateDESC,
                                                        boolean sortPriceASC, boolean sortPriceDESC,
                                                        JsonObject parameters);
    Observable<RentRoomDTO> downloadRentRoom(String key, int id, int section, String price);
    Observable<RentHousesContainerDTO> downloadRentHouses(String key, String user, int start, int count,
                                                          boolean sortDateASC, boolean sortDateDESC,
                                                          boolean sortPriceASC, boolean sortPriceDESC,
                                                          JsonObject parameters);
    Observable<RentHouseDTO> downloadRentHouse(String key, int id, int section, String price);

    Observable<Float> downloadPredictionSaleFlat(String key, String user, JsonObject parameters);
    Observable<Float> downloadPredictionRentFlatLong(String key, String user, JsonObject parameters);

    Observable<List<String>> downloadPropertyValues(String key, JsonObject parameters);
    Observable<List<String>> downloadPropertyMultiValues(String key, JsonObject parameters);
    Observable<List<String>> downloadModelValues(String key, JsonObject parameters);

    Observable<MessageDTO> postComment(String key, JsonObject parameters);
    Observable<MessageDTO> deleteComment(String key, JsonObject parameters);
    Observable<MessageDTO> updateComment(String key, JsonObject parameters);
    Observable<CommentsContainerDTO> downloadComments(String key, int start, int count,
                                                      JsonObject parameters);

    Observable<UserDTO> logIn(String key, JsonObject parameters);
    Observable<UserDTO> logUp(String key, JsonObject parameters);

    Observable<ExchangeRatesDTO> downloadExchangeRate(String rate);

    Observable<SaleFlatsContainerDTO> getSaleFlats();
    Observable<SaleRoomsContainerDTO> getSaleRooms();
    Observable<SaleHousesContainerDTO> getSaleHouses();
    Observable<SaleAreasContainerDTO> getSaleAreas();
    Observable<RentFlatsContainerDTO> getRentFlats();
    Observable<RentRoomsContainerDTO> getRentRooms();
    Observable<RentHousesContainerDTO> getRentHouses();

    void clearPropertyTable(String property, Class table);
    void clearAllProperties();

    void saveParameters(String property, JsonObject parameters);
    JsonObject getParameters(String property);
    void clearParameters(String property);
    void clearAllParameters();

    void saveFavorite(String property, PropertyItem item);
    Observable<List<FavoriteTable>> getFavorites(String property);
    Observable<List<FavoriteData>> getFavoritesData(String property);
    void clearFavorite(String property, Integer id, Integer section, Integer price);
    void clearAllFavorites();

    void postFavorites();
    Observable<List<FavoriteTable>> downloadFavorites(String key, String user);
}
