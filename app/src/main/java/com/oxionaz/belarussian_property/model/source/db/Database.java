package com.oxionaz.belarussian_property.model.source.db;

import com.google.gson.JsonObject;
import com.oxionaz.belarussian_property.model.source.db.models.cache.FavoriteTable;
import com.oxionaz.belarussian_property.model.source.rest.dto.areas.SaleAreasContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.RentFlatsContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.SaleFlatsContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.houses.RentHousesContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.houses.SaleHousesContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.rooms.RentRoomsContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.rooms.SaleRoomsContainerDTO;
import java.util.List;
import rx.Observable;

public interface Database {

    void saveProperty(SaleFlatsContainerDTO saleFlatsContainerDTO);
    void saveProperty(SaleRoomsContainerDTO saleFlatsContainerDTO);
    void saveProperty(SaleHousesContainerDTO saleFlatsContainerDTO);
    void saveProperty(SaleAreasContainerDTO saleFlatsContainerDTO);
    void saveProperty(RentFlatsContainerDTO saleFlatsContainerDTO);
    void saveProperty(RentRoomsContainerDTO saleFlatsContainerDTO);
    void saveProperty(RentHousesContainerDTO saleFlatsContainerDTO);

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

    void saveFavorite(FavoriteTable favoriteTable);
    void saveFavorites(List<FavoriteTable> favorites);
    Observable<List<FavoriteTable>> getFavorites(String property);
    void clearFavorite(String property, Integer id, Integer section, Integer price);
    void clearAllFavorites();
}
