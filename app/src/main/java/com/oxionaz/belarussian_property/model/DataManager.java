package com.oxionaz.belarussian_property.model;

import com.google.gson.JsonObject;
import com.oxionaz.belarussian_property.model.source.db.Database;
import com.oxionaz.belarussian_property.model.source.db.models.cache.FavoriteTable;
import com.oxionaz.belarussian_property.model.source.rest.Rest;
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
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.other.Const;
import com.oxionaz.belarussian_property.other.util.PreferencesHelper;
import com.oxionaz.belarussian_property.presenter.mapping.PropertyItem;
import com.oxionaz.belarussian_property.view.adapters.FavoriteData;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

public class DataManager implements Model {

    @Inject
    Rest restService;

    @Inject
    Database databaseHelper;

    @Inject
    PreferencesHelper preferencesHelper;

    public DataManager(){
        App.getAppComponent().inject(this);
    }

    @Override
    public Observable<SaleFlatsContainerDTO> downloadFlats(
            String key, String user, int start, int count, boolean sortDateASC, boolean sortDateDESC,
            boolean sortPriceASC, boolean sortPriceDESC, JsonObject parameters) {
        return restService.downloadFlats(
                key,user,start,count,sortDateASC,sortDateDESC,sortPriceASC,sortPriceDESC,parameters)
                .doOnNext(databaseHelper::saveProperty);
    }

    @Override
    public Observable<SaleFlatDTO> downloadFlat(String key, int id, String price) {
        return restService.downloadFlat(key,id,price);
    }

    @Override
    public Observable<SaleRoomsContainerDTO> downloadRooms(
            String key, String user, int start, int count, boolean sortDateASC, boolean sortDateDESC,
            boolean sortPriceASC, boolean sortPriceDESC, JsonObject parameters) {
        return restService.downloadRooms(
                key,user,start,count,sortDateASC,sortDateDESC,sortPriceASC,sortPriceDESC,parameters)
                .doOnNext(databaseHelper::saveProperty);
    }

    @Override
    public Observable<SaleRoomDTO> downloadRoom(String key, int id, String price) {
        return restService.downloadRoom(key,id,price);
    }

    @Override
    public Observable<SaleHousesContainerDTO> downloadHouses(
            String key, String user, int start, int count, boolean sortDateASC, boolean sortDateDESC,
            boolean sortPriceASC, boolean sortPriceDESC, JsonObject parameters) {
        return restService.downloadHouses(
                key,user,start,count,sortDateASC,sortDateDESC,sortPriceASC,sortPriceDESC,parameters)
                .doOnNext(databaseHelper::saveProperty);
    }

    @Override
    public Observable<SaleHouseDTO> downloadHouse(String key, int id, String price) {
        return restService.downloadHouse(key,id,price);
    }

    @Override
    public Observable<SaleAreasContainerDTO> downloadAreas(
            String key, String user, int start, int count, boolean sortDateASC, boolean sortDateDESC,
            boolean sortPriceASC, boolean sortPriceDESC, JsonObject parameters) {
        return restService.downloadAreas(
                key,user,start,count,sortDateASC,sortDateDESC,sortPriceASC,sortPriceDESC,parameters)
                .doOnNext(databaseHelper::saveProperty);
    }

    @Override
    public Observable<SaleAreaDTO> downloadArea(String key, int id, String price) {
        return restService.downloadArea(key,id,price);
    }

    @Override
    public Observable<RentFlatsContainerDTO> downloadRentFlats(
            String key, String user, int start, int count, boolean sortDateASC, boolean sortDateDESC,
            boolean sortPriceASC, boolean sortPriceDESC, JsonObject parameters) {
        return restService.downloadRentFlats(
                key,user,start,count,sortDateASC,sortDateDESC,sortPriceASC,sortPriceDESC,parameters)
                .doOnNext(databaseHelper::saveProperty);
    }

    @Override
    public Observable<RentFlatDTO> downloadRentFlat(String key, int id, int section, String price) {
        return restService.downloadRentFlat(key,id,section,price);
    }

    @Override
    public Observable<RentRoomsContainerDTO> downloadRentRooms(
            String key, String user, int start, int count, boolean sortDateASC, boolean sortDateDESC,
            boolean sortPriceASC, boolean sortPriceDESC, JsonObject parameters) {
        return restService.downloadRentRooms(
                key,user,start,count,sortDateASC,sortDateDESC,sortPriceASC,sortPriceDESC,parameters)
                .doOnNext(databaseHelper::saveProperty);
    }

    @Override
    public Observable<RentRoomDTO> downloadRentRoom(String key, int id, int section, String price) {
        return restService.downloadRentRoom(key,id,section,price);
    }

    @Override
    public Observable<RentHousesContainerDTO> downloadRentHouses(
            String key, String user, int start, int count, boolean sortDateASC, boolean sortDateDESC,
            boolean sortPriceASC, boolean sortPriceDESC, JsonObject parameters) {
        return restService.downloadRentHouses(
                key,user,start,count,sortDateASC,sortDateDESC,sortPriceASC,sortPriceDESC,parameters)
                .doOnNext(databaseHelper::saveProperty);
    }

    @Override
    public Observable<RentHouseDTO> downloadRentHouse(String key, int id, int section, String price) {
        return restService.downloadRentHouse(key,id,section,price);
    }

    @Override
    public Observable<Float> downloadPredictionSaleFlat(String key, String user, JsonObject parameters) {
        return restService.downloadPredictionSaleFlat(key,user,parameters);
    }

    @Override
    public Observable<Float> downloadPredictionRentFlatLong(String key, String user, JsonObject parameters) {
        return restService.downloadPredictionRentFlatLong(key,user,parameters);
    }

    @Override
    public Observable<List<String>> downloadPropertyValues(String key, JsonObject parameters) {
        return restService.downloadPropertyValues(key,parameters);
    }

    @Override
    public Observable<List<String>> downloadPropertyMultiValues(String key, JsonObject parameters) {
        return restService.downloadPropertyMultiValues(key,parameters);
    }

    @Override
    public Observable<List<String>> downloadModelValues(String key, JsonObject parameters) {
        return restService.downloadModelValues(key,parameters);
    }

    @Override
    public Observable<MessageDTO> postComment(String key, JsonObject parameters) {
        return restService.postComment(key,parameters);
    }

    @Override
    public Observable<MessageDTO> deleteComment(String key, JsonObject parameters) {
        return restService.deleteComment(key,parameters);
    }

    @Override
    public Observable<MessageDTO> updateComment(String key, JsonObject parameters) {
        return restService.updateComment(key,parameters);
    }

    @Override
    public Observable<CommentsContainerDTO> downloadComments(
            String key, int start, int count, JsonObject parameters) {
        return restService.downloadComments(key,start,count,parameters);
    }

    @Override
    public Observable<UserDTO> logIn(String key, JsonObject parameters) {
        return restService.logIn(key,parameters);
    }

    @Override
    public Observable<UserDTO> logUp(String key, JsonObject parameters) {
        return restService.logUp(key,parameters);
    }

    @Override
    public Observable<ExchangeRatesDTO> downloadExchangeRate(String rate) {
        return restService.downloadExchangeRate(rate);
    }

    @Override
    public Observable<SaleFlatsContainerDTO> getSaleFlats() {
        return databaseHelper.getSaleFlats();
    }

    @Override
    public Observable<SaleRoomsContainerDTO> getSaleRooms() {
        return databaseHelper.getSaleRooms();
    }

    @Override
    public Observable<SaleHousesContainerDTO> getSaleHouses() {
        return databaseHelper.getSaleHouses();
    }

    @Override
    public Observable<SaleAreasContainerDTO> getSaleAreas() {
        return databaseHelper.getSaleAreas();
    }

    @Override
    public Observable<RentFlatsContainerDTO> getRentFlats() {
        return databaseHelper.getRentFlats();
    }

    @Override
    public Observable<RentRoomsContainerDTO> getRentRooms() {
        return databaseHelper.getRentRooms();
    }

    @Override
    public Observable<RentHousesContainerDTO> getRentHouses() {
        return databaseHelper.getRentHouses();
    }

    @Override
    public void clearPropertyTable(String property, Class table) {
        databaseHelper.clearPropertyTable(property, table);
    }

    @Override
    public void clearAllProperties() {
        databaseHelper.clearAllProperties();
    }

    @Override
    public void saveParameters(String property, JsonObject parameters) {
        databaseHelper.saveParameters(property, parameters);
    }

    @Override
    public JsonObject getParameters(String property) {
        return databaseHelper.getParameters(property);
    }

    @Override
    public void clearParameters(String property) {
        databaseHelper.clearParameters(property);
    }

    @Override
    public void clearAllParameters() { databaseHelper.clearAllParameters(); }

    @Override
    public void saveFavorite(String property, PropertyItem item) {
        FavoriteTable favoriteTable = new FavoriteTable();
        favoriteTable.setProperty(property);
        favoriteTable.setId(item.getId());
        favoriteTable.setSection(item.getSection());
        favoriteTable.setPrice(item.getPrice());
        favoriteTable.setModifiedDate(item.getModifiedDate());
        favoriteTable.setDate(item.getDate());
        favoriteTable.setCost(item.getCost());
        favoriteTable.setInfo(item.getInfo());
        favoriteTable.setAddress(item.getAddress());
        favoriteTable.setMetro(item.getMetro());
        favoriteTable.setBranch(item.getBranch());
        favoriteTable.setThumb(item.getThumb());
        databaseHelper.saveFavorite(favoriteTable);
    }

    @Override
    public Observable<List<FavoriteTable>> getFavorites(String property) {
        return databaseHelper.getFavorites(property);
    }

    @Override
    public Observable<List<FavoriteData>> getFavoritesData(String property) {
        return databaseHelper.getFavorites(property)
                .flatMap(Observable::from)
                .map(favoriteTable -> new FavoriteData(favoriteTable.getId(),
                        favoriteTable.getSection(), favoriteTable.getPrice())).toList();
    }

    @Override
    public void clearFavorite(String property, Integer id, Integer section, Integer price) {
        databaseHelper.clearFavorite(property, id, section, price);
    }

    @Override
    public void clearAllFavorites() {
        databaseHelper.clearAllFavorites();
    }

    @Override
    public void postFavorites() {
        restService.postBookmarks(
                Const.API_KEY,
                preferencesHelper.getPreference("user_id"),
                SQLite.select().from(FavoriteTable.class).queryList()).
                subscribe(new Subscriber<MessageDTO>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        unsubscribe();
                    }

                    @Override
                    public void onNext(MessageDTO messageDTO) {
                        unsubscribe();
                    }
                });
    }

    @Override
    public Observable<List<FavoriteTable>> downloadFavorites(String key, String user) {
        return restService.downloadsBookmarks(key, user)
                .doOnNext(databaseHelper::saveFavorites);
    }
}