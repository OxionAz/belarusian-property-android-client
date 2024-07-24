package com.oxionaz.belarussian_property.model.source.rest;

import com.google.gson.JsonObject;
import com.oxionaz.belarussian_property.model.source.ServiceHelper;
import com.oxionaz.belarussian_property.model.source.db.models.cache.FavoriteTable;
import com.oxionaz.belarussian_property.model.source.rest.api.AnalysesAPI;
import com.oxionaz.belarussian_property.model.source.rest.api.CommentAPI;
import com.oxionaz.belarussian_property.model.source.rest.api.DictionaryAPI;
import com.oxionaz.belarussian_property.model.source.rest.api.ExchangeRatesAPI;
import com.oxionaz.belarussian_property.model.source.rest.api.PropertyAPI;
import com.oxionaz.belarussian_property.model.source.rest.api.UserAPI;
import com.oxionaz.belarussian_property.model.source.rest.dto.ExchangeRatesDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.MessageDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.UserDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.comments.CommentsContainerDTO;
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
import com.oxionaz.belarussian_property.other.App;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

public class RestService extends ServiceHelper implements Rest {

    @Inject
    PropertyAPI propertyAPI;

    @Inject
    AnalysesAPI analysesAPI;

    @Inject
    DictionaryAPI dictionaryAPI;

    @Inject
    UserAPI userAPI;

    @Inject
    CommentAPI commentAPI;

    @Inject
    ExchangeRatesAPI exchangeRatesAPI;

    public RestService(){
        App.getAppComponent().inject(this);
    }


    @Override
    public Observable<SaleFlatsContainerDTO> downloadFlats(
            String key, String user, int start, int count, boolean sortDateASC, boolean sortDateDESC,
            boolean sortPriceASC, boolean sortPriceDESC, JsonObject parameters) {
        return propertyAPI.getSaleFlats(key, user, start, count,
                sortDateASC, sortDateDESC, sortPriceASC,sortPriceDESC, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<SaleFlatDTO> downloadFlat(String key, int id, String price) {
        return propertyAPI.getSaleFlat(key, id, price).
                compose(applySchedulers());
    }

    @Override
    public Observable<SaleRoomsContainerDTO> downloadRooms(
            String key, String user, int start, int count, boolean sortDateASC, boolean sortDateDESC,
            boolean sortPriceASC, boolean sortPriceDESC, JsonObject parameters) {
        return propertyAPI.getSaleRooms(key, user, start, count,
                sortDateASC, sortDateDESC, sortPriceASC,sortPriceDESC, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<SaleRoomDTO> downloadRoom(String key, int id, String price) {
        return propertyAPI.getSaleRoom(key, id, price).
                compose(applySchedulers());
    }

    @Override
    public Observable<SaleHousesContainerDTO> downloadHouses(
            String key, String user, int start, int count, boolean sortDateASC, boolean sortDateDESC,
            boolean sortPriceASC, boolean sortPriceDESC, JsonObject parameters) {
        return propertyAPI.getSaleHouses(key, user, start, count,
                sortDateASC, sortDateDESC, sortPriceASC,sortPriceDESC, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<SaleHouseDTO> downloadHouse(String key, int id, String price) {
        return propertyAPI.getSaleHouse(key, id, price).
                compose(applySchedulers());
    }

    @Override
    public Observable<SaleAreasContainerDTO> downloadAreas(
            String key, String user, int start, int count, boolean sortDateASC, boolean sortDateDESC,
            boolean sortPriceASC, boolean sortPriceDESC, JsonObject parameters) {
        return propertyAPI.getSaleAreas(key, user, start, count,
                sortDateASC, sortDateDESC, sortPriceASC,sortPriceDESC, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<SaleAreaDTO> downloadArea(String key, int id, String price) {
        return propertyAPI.getSaleArea(key, id, price).
                compose(applySchedulers());
    }

    @Override
    public Observable<RentFlatsContainerDTO> downloadRentFlats(
            String key, String user, int start, int count, boolean sortDateASC, boolean sortDateDESC,
            boolean sortPriceASC, boolean sortPriceDESC, JsonObject parameters) {
        return propertyAPI.getRentFlats(key, user, start, count,
                sortDateASC, sortDateDESC, sortPriceASC,sortPriceDESC, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<RentFlatDTO> downloadRentFlat(String key, int id, int section, String price) {
        return propertyAPI.getRentFlat(key, id, section, price).
                compose(applySchedulers());
    }

    @Override
    public Observable<RentRoomsContainerDTO> downloadRentRooms(
            String key, String user, int start, int count, boolean sortDateASC, boolean sortDateDESC,
            boolean sortPriceASC, boolean sortPriceDESC, JsonObject parameters) {
        return propertyAPI.getRentRooms(key, user, start, count,
                sortDateASC, sortDateDESC, sortPriceASC,sortPriceDESC, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<RentRoomDTO> downloadRentRoom(String key, int id, int section, String price) {
        return propertyAPI.getRentRoom(key, id, section, price).
                compose(applySchedulers());
    }

    @Override
    public Observable<RentHousesContainerDTO> downloadRentHouses(
            String key, String user, int start, int count, boolean sortDateASC, boolean sortDateDESC,
            boolean sortPriceASC, boolean sortPriceDESC, JsonObject parameters) {
        return propertyAPI.getRentHouses(key, user, start, count,
                sortDateASC, sortDateDESC, sortPriceASC,sortPriceDESC, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<RentHouseDTO> downloadRentHouse(String key, int id, int section, String price) {
        return propertyAPI.getRentHouse(key, id, section, price).
                compose(applySchedulers());
    }

    @Override
    public Observable<Float> downloadPredictionSaleFlat(String key, String user, JsonObject parameters) {
        return analysesAPI.getPredictionSaleFlat(key, user, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<Float> downloadPredictionRentFlatLong(String key, String user, JsonObject parameters) {
        return analysesAPI.getPredictionRentFlatLong(key, user, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<List<String>> downloadPropertyValues(String key, JsonObject parameters) {
        return dictionaryAPI.getPropertyValues(key, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<List<String>> downloadPropertyMultiValues(String key, JsonObject parameters) {
        return dictionaryAPI.getPropertyMultiValues(key, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<List<String>> downloadModelValues(String key, JsonObject parameters) {
        return dictionaryAPI.getModelValues(key, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<MessageDTO> postComment(String key, JsonObject parameters) {
        return commentAPI.addComment(key, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<MessageDTO> deleteComment(String key, JsonObject parameters) {
        return commentAPI.deleteComment(key, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<MessageDTO> updateComment(String key, JsonObject parameters) {
        return commentAPI.updateComment(key, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<CommentsContainerDTO> downloadComments(
            String key, int start, int count, JsonObject parameters) {
        return commentAPI.getComments(key, start, count, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<UserDTO> logIn(String key, JsonObject parameters) {
        return userAPI.logIn(key, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<UserDTO> logUp(String key, JsonObject parameters) {
        return userAPI.logUp(key, parameters).
                compose(applySchedulers());
    }

    @Override
    public Observable<ExchangeRatesDTO> downloadExchangeRate(String rate) {
        return exchangeRatesAPI.getExchangeRate(rate).
                compose(applySchedulers());
    }

    @Override
    public Observable<MessageDTO> postBookmarks(String key, String user, List<FavoriteTable> favorites) {
        return userAPI.saveBookmarks(key, user, favorites).
                compose(applySchedulers());
    }

    @Override
    public Observable<List<FavoriteTable>> downloadsBookmarks(String key, String user) {
        return userAPI.getBookmarks(key, user).
                compose(applySchedulers());
    }
}
