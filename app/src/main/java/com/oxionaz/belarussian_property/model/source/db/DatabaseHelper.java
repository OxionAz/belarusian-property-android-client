package com.oxionaz.belarussian_property.model.source.db;

import android.util.Log;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oxionaz.belarussian_property.model.source.ServiceHelper;
import com.oxionaz.belarussian_property.model.source.db.models.InfoTable;
import com.oxionaz.belarussian_property.model.source.db.models.InfoTable_Table;
import com.oxionaz.belarussian_property.model.source.db.models.ParametersTable;
import com.oxionaz.belarussian_property.model.source.db.models.ParametersTable_Table;
import com.oxionaz.belarussian_property.model.source.db.models.cache.FavoriteTable;
import com.oxionaz.belarussian_property.model.source.db.models.cache.FavoriteTable_Table;
import com.oxionaz.belarussian_property.model.source.db.models.cache.rent.RentFlats;
import com.oxionaz.belarussian_property.model.source.db.models.cache.rent.RentHouses;
import com.oxionaz.belarussian_property.model.source.db.models.cache.rent.RentRooms;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleAreas;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleFlats;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleHouses;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleRooms;
import com.oxionaz.belarussian_property.model.source.rest.dto.areas.SaleAreasContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.RentFlatsContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.SaleFlatsContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.houses.RentHousesContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.houses.SaleHousesContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.rooms.RentRoomsContainerDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.rooms.SaleRoomsContainerDTO;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import java.util.List;
import rx.Observable;

public class DatabaseHelper extends ServiceHelper implements Database {

    private final String[] properties = new String[]{"sale_flats", "sale_rooms", "sale_houses",
            "sale_areas", "rent_flats", "rent_rooms", "rent_houses"};

    @Override
    public void saveProperty(SaleFlatsContainerDTO saleFlatsContainerDTO) {
        saveTransaction(saleFlatsContainerDTO.getSaleFlatses());
        InfoTable infoTable = saleFlatsContainerDTO.getInfoTable();
        infoTable.setProperty(properties[0]);
        infoTable.save();
    }

    @Override
    public void saveProperty(SaleRoomsContainerDTO saleRoomsContainerDTO) {
        saveTransaction(saleRoomsContainerDTO.getSaleRooms());
        InfoTable infoTable = saleRoomsContainerDTO.getInfo();
        infoTable.setProperty(properties[1]);
        infoTable.save();
    }

    @Override
    public void saveProperty(SaleHousesContainerDTO saleHousesContainerDTO) {
        saveTransaction(saleHousesContainerDTO.getSaleHouses());
        InfoTable infoTable = saleHousesContainerDTO.getInfo();
        infoTable.setProperty(properties[2]);
        infoTable.save();
    }

    @Override
    public void saveProperty(SaleAreasContainerDTO saleAreasContainerDTO) {
        saveTransaction(saleAreasContainerDTO.getSaleAreas());
        InfoTable infoTable = saleAreasContainerDTO.getInfoTable();
        infoTable.setProperty(properties[3]);
        infoTable.save();
    }

    @Override
    public void saveProperty(RentFlatsContainerDTO rentFlatsContainerDTO) {
        saveTransaction(rentFlatsContainerDTO.getRentFlats());
        InfoTable infoTable = rentFlatsContainerDTO.getInfo();
        infoTable.setProperty(properties[4]);
        infoTable.save();
    }

    @Override
    public void saveProperty(RentRoomsContainerDTO rentRoomsContainerDTO) {
        saveTransaction(rentRoomsContainerDTO.getRentRooms());
        InfoTable infoTable = rentRoomsContainerDTO.getInfo();
        infoTable.setProperty(properties[5]);
        infoTable.save();
    }

    @Override
    public void saveProperty(RentHousesContainerDTO rentHousesContainerDTO) {
        saveTransaction(rentHousesContainerDTO.getRentHouses());
        InfoTable infoTable = rentHousesContainerDTO.getInfo();
        infoTable.setProperty(properties[6]);
        infoTable.save();
    }

    @Override
    public Observable<SaleFlatsContainerDTO> getSaleFlats() {
        return Observable.create((Observable.OnSubscribe<SaleFlatsContainerDTO>) subscriber -> {
            SaleFlatsContainerDTO saleFlatsContainerDTO = new SaleFlatsContainerDTO();
            saleFlatsContainerDTO.setSaleFlatses(SQLite.select().from(SaleFlats.class).queryList());
            saleFlatsContainerDTO.setInfoTable(SQLite.select().from(InfoTable.class)
                    .where(InfoTable_Table.property.is(properties[0])).querySingle());
            subscriber.onNext(saleFlatsContainerDTO);
            subscriber.onCompleted();
        }).compose(applySchedulers());
    }

    @Override
    public Observable<SaleRoomsContainerDTO> getSaleRooms() {
        return Observable.create((Observable.OnSubscribe<SaleRoomsContainerDTO>) subscriber -> {
            SaleRoomsContainerDTO saleRoomsContainerDTO = new SaleRoomsContainerDTO();
            saleRoomsContainerDTO.setSaleRooms(SQLite.select().from(SaleRooms.class).queryList());
            saleRoomsContainerDTO.setInfo(SQLite.select().from(InfoTable.class)
                    .where(InfoTable_Table.property.is(properties[1])).querySingle());
            subscriber.onNext(saleRoomsContainerDTO);
            subscriber.onCompleted();
        }).compose(applySchedulers());
    }

    @Override
    public Observable<SaleHousesContainerDTO> getSaleHouses() {
        return Observable.create((Observable.OnSubscribe<SaleHousesContainerDTO>) subscriber -> {
            SaleHousesContainerDTO saleHousesContainerDTO = new SaleHousesContainerDTO();
            saleHousesContainerDTO.setSaleHouses(SQLite.select().from(SaleHouses.class).queryList());
            saleHousesContainerDTO.setInfo(SQLite.select().from(InfoTable.class)
                    .where(InfoTable_Table.property.is(properties[2])).querySingle());
            subscriber.onNext(saleHousesContainerDTO);
            subscriber.onCompleted();
        }).compose(applySchedulers());
    }

    @Override
    public Observable<SaleAreasContainerDTO> getSaleAreas() {
        return Observable.create((Observable.OnSubscribe<SaleAreasContainerDTO>) subscriber -> {
            SaleAreasContainerDTO saleAreasContainerDTO = new SaleAreasContainerDTO();
            saleAreasContainerDTO.setSaleAreas(SQLite.select().from(SaleAreas.class).queryList());
            saleAreasContainerDTO.setInfoTable(SQLite.select().from(InfoTable.class)
                    .where(InfoTable_Table.property.is(properties[3])).querySingle());
            subscriber.onNext(saleAreasContainerDTO);
            subscriber.onCompleted();
        }).compose(applySchedulers());
    }

    @Override
    public Observable<RentFlatsContainerDTO> getRentFlats() {
        return Observable.create((Observable.OnSubscribe<RentFlatsContainerDTO>) subscriber -> {
            RentFlatsContainerDTO rentFlatsContainerDTO = new RentFlatsContainerDTO();
            rentFlatsContainerDTO.setRentFlats(SQLite.select().from(RentFlats.class).queryList());
            rentFlatsContainerDTO.setInfo(SQLite.select().from(InfoTable.class)
                    .where(InfoTable_Table.property.is(properties[4])).querySingle());
            subscriber.onNext(rentFlatsContainerDTO);
            subscriber.onCompleted();
        }).compose(applySchedulers());
    }

    @Override
    public Observable<RentRoomsContainerDTO> getRentRooms() {
        return Observable.create((Observable.OnSubscribe<RentRoomsContainerDTO>) subscriber -> {
            RentRoomsContainerDTO rentRoomsContainerDTO = new RentRoomsContainerDTO();
            rentRoomsContainerDTO.setRentRooms(SQLite.select().from(RentRooms.class).queryList());
            rentRoomsContainerDTO.setInfo(SQLite.select().from(InfoTable.class)
                    .where(InfoTable_Table.property.is(properties[5])).querySingle());
            subscriber.onNext(rentRoomsContainerDTO);
            subscriber.onCompleted();
        }).compose(applySchedulers());
    }

    @Override
    public Observable<RentHousesContainerDTO> getRentHouses() {
        return Observable.create((Observable.OnSubscribe<RentHousesContainerDTO>) subscriber -> {
            RentHousesContainerDTO rentHousesContainerDTO = new RentHousesContainerDTO();
            rentHousesContainerDTO.setRentHouses(SQLite.select().from(RentHouses.class).queryList());
            rentHousesContainerDTO.setInfo(SQLite.select().from(InfoTable.class)
                    .where(InfoTable_Table.property.is(properties[6])).querySingle());
            subscriber.onNext(rentHousesContainerDTO);
            subscriber.onCompleted();
        }).compose(applySchedulers());
    }

    @Override
    public void clearPropertyTable(String property, Class table){
        SQLite.delete(table).execute();
        SQLite.delete().from(InfoTable.class).where(InfoTable_Table.property.is(property));
    }

    @Override
    public void clearAllProperties() {
        SQLite.delete(SaleFlats.class).execute();
        SQLite.delete(SaleRooms.class).execute();
        SQLite.delete(SaleHouses.class).execute();
        SQLite.delete(SaleAreas.class).execute();
        SQLite.delete(RentFlats.class).execute();
        SQLite.delete(RentRooms.class).execute();
        SQLite.delete(RentHouses.class).execute();
        SQLite.delete(InfoTable.class).execute();
    }

    @Override
    public void saveParameters(String property, JsonObject parameters) {
        ParametersTable parameter = new ParametersTable();
        parameter.setProperty(property);
        parameter.setJson(parameters.toString());
        parameter.save();
    }

    @Override
    public JsonObject getParameters(String property) {
        try {
            String json = SQLite.select().from(ParametersTable.class)
                    .where(ParametersTable_Table.property.is(property))
                    .querySingle().getJson();
            return ((JsonObject) new JsonParser().parse(json));
        } catch (Exception e) {
            return new JsonObject();
        }
    }

    @Override
    public void clearParameters(String property) {
        SQLite.delete().from(ParametersTable.class)
                .where(ParametersTable_Table.property.is(property));
    }

    @Override
    public void clearAllParameters() {
        SQLite.delete().from(ParametersTable.class).execute();
    }

    @Override
    public void saveFavorite(FavoriteTable favoriteTable) {
        favoriteTable.save();
    }

    @Override
    public void saveFavorites(List<FavoriteTable> favorites) {
        saveTransaction(favorites);
    }

    @Override
    public Observable<List<FavoriteTable>> getFavorites(String property) {
        return Observable.create((Observable.OnSubscribe<List<FavoriteTable>>) subscriber -> {
            subscriber.onNext(SQLite.select().from(FavoriteTable.class)
                    .where(FavoriteTable_Table.property.is(property))
                    .queryList());
            subscriber.onCompleted();
        }).compose(applySchedulers());
    }

    @Override
    public void clearFavorite(String property, Integer id, Integer section, Integer price) {
        if (price != null) {
            SQLite.delete().from(FavoriteTable.class)
                    .where(FavoriteTable_Table.property.is(property), FavoriteTable_Table.id.is(id),
                            FavoriteTable_Table.section.is(section), FavoriteTable_Table.price.eq(price))
                    .execute();
        } else {
            SQLite.delete().from(FavoriteTable.class)
                    .where(FavoriteTable_Table.property.is(property), FavoriteTable_Table.id.is(id),
                            FavoriteTable_Table.section.is(section), FavoriteTable_Table.price.isNull())
                    .execute();
        }
    }

    @Override
    public void clearAllFavorites() {
        SQLite.delete(FavoriteTable.class).execute();
    }

    // Transaction for save
    private <T extends BaseModel> void saveTransaction(List<T> rows){
        FlowManager.getDatabase(AppDatabase.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                        (ProcessModelTransaction.ProcessModel<T>) (row, wrapper) -> {
                            // do work here -- i.e. user.delete() or user.update()
                            row.save();
                        }).addAll(rows).build()) // add elements (can also handle multiple)
            .build().execute();
    }
}
