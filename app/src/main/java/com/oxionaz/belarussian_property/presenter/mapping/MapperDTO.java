package com.oxionaz.belarussian_property.presenter.mapping;

import android.annotation.SuppressLint;
import android.content.Context;
import com.oxionaz.belarussian_property.R;
import com.oxionaz.belarussian_property.model.source.db.models.cache.rent.RentFlats;
import com.oxionaz.belarussian_property.model.source.db.models.cache.rent.RentHouses;
import com.oxionaz.belarussian_property.model.source.db.models.cache.rent.RentRooms;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleAreas;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleFlats;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleHouses;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleRooms;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.other.util.PreferencesHelper;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;

public class MapperDTO {

    @Inject
    Context context;

    @Inject
    PreferencesHelper preferencesHelper;

    // Initialise constants and variables
    private final float USD_RATE;
    private final String NOT_PRICE;
    private final String NOT_INFO;
    private final String NOT_ADDRESS;
    private final List<String> red_stations;
    @SuppressLint("SimpleDateFormat")
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("d MMMM y", new DateFormatSymbols(){
        @Override
        public String[] getMonths() {
            return new String[]{"Января", "Февраля", "Марта", "Апреля", "Мая", "Июня",
                    "Июля", "Августа", "Сентября", "Октября", "Ноября", "Декабря"};
        }
    });

    public MapperDTO(){
        App.getAppComponent().inject(this);
        this.USD_RATE = preferencesHelper.getExchangeRate("USD");
        this.NOT_PRICE = context.getString(R.string.not_price);
        this.NOT_INFO = context.getString(R.string.not_info);
        this.NOT_ADDRESS = context.getString(R.string.not_address);
        this.red_stations = Arrays.asList(context.getResources().getStringArray(R.array.metro_stations));
    }

    public PropertyItem mapProperty(SaleFlats item){
        PropertyItem propertyItem = new PropertyItem();
        propertyItem.setId(item.getId());
        propertyItem.setSection(item.getSection());
        propertyItem.setPrice(item.getPrice());
        propertyItem.setModifiedDate(item.getModifiedDate());
        propertyItem.setDate(transformDate(item.getModifiedDate()));
        propertyItem.setCost(transformPrice(item.getPrice()));
        propertyItem.setInfo(transformFlatsInfo(item.getRooms(), item.getSquare(), item.getFloor(), item.getTotalFloors()));
        propertyItem.setAddress(transformAddress(item.getCity(), item.getDistrict(), item.getStreet(), item.getHouse(), item.getCorp(), null));
        propertyItem.setMetro(transformMetro(item.getMetro(), item.getMetroTime()));
        propertyItem.setBranch(transformMetroBranch(item.getMetro(), item.getMetroBranch()));
        propertyItem.setThumb(item.getThumb());
        return propertyItem;
    }

    public PropertyItem mapProperty(SaleRooms item){
        PropertyItem propertyItem = new PropertyItem();
        propertyItem.setId(item.getId());
        propertyItem.setSection(item.getSection());
        propertyItem.setPrice(item.getPrice());
        propertyItem.setModifiedDate(item.getModifiedDate());
        propertyItem.setDate(transformDate(item.getModifiedDate()));
        propertyItem.setCost(transformPrice(item.getPrice()));
        propertyItem.setInfo(transformRoomsInfo(item.getRooms(), item.getSquareUseful(), item.getFloor(), item.getTotalFloors()));
        propertyItem.setAddress(transformAddress(item.getCity(), item.getDistrict(), item.getStreet(), item.getHouse(), item.getCorp(), null));
        propertyItem.setMetro(transformMetro(item.getMetro(), item.getMetroTime()));
        propertyItem.setBranch(transformMetroBranch(item.getMetro(), item.getMetroBranch()));
        propertyItem.setThumb(item.getThumb());
        return propertyItem;
    }

    public PropertyItem mapProperty(SaleHouses item){
        PropertyItem propertyItem = new PropertyItem();
        propertyItem.setId(item.getId());
        propertyItem.setSection(item.getSection());
        propertyItem.setPrice(item.getPrice());
        propertyItem.setModifiedDate(item.getModifiedDate());
        propertyItem.setDate(transformDate(item.getModifiedDate()));
        propertyItem.setCost(transformPrice(item.getPrice()));
        propertyItem.setInfo(transformHousesInfo(item.getView(), item.getSquare(), item.getLandArea(), item.getLevels()));
        propertyItem.setAddress(transformAddress(item.getCity(), item.getDistrict(), item.getStreet(), item.getHouse(), item.getCorp(), item.getDirection()));
        propertyItem.setMetro(null);
        propertyItem.setBranch(null);
        propertyItem.setThumb(item.getThumb());
        return propertyItem;
    }

    public PropertyItem mapProperty(SaleAreas item){
        PropertyItem propertyItem = new PropertyItem();
        propertyItem.setId(item.getId());
        propertyItem.setSection(item.getSection());
        propertyItem.setPrice(item.getPrice());
        propertyItem.setModifiedDate(item.getModifiedDate());
        propertyItem.setDate(transformDate(item.getModifiedDate()));
        propertyItem.setCost(transformPrice(item.getPrice()));
        propertyItem.setInfo(transformAreasInfo(item.getLandArea()));
        propertyItem.setAddress(transformAddress(item.getCity(), item.getDistrict(), item.getStreet(), null, null, item.getDirection()));
        propertyItem.setMetro(null);
        propertyItem.setBranch(null);
        propertyItem.setThumb(item.getThumb());
        return propertyItem;
    }

    public PropertyItem mapProperty(RentFlats item){
        PropertyItem propertyItem = new PropertyItem();
        propertyItem.setId(item.getId());
        propertyItem.setSection(item.getSection());
        propertyItem.setPrice(item.getPrice());
        propertyItem.setModifiedDate(item.getModifiedDate());
        propertyItem.setDate(transformDate(item.getModifiedDate()));
        propertyItem.setCost(transformPrice(item.getPrice()));
        propertyItem.setInfo(transformFlatsInfo(item.getRooms(), item.getSquare(), item.getFloor(), item.getTotalFloors()));
        propertyItem.setAddress(transformAddress(item.getCity(), item.getDistrict(), item.getStreet(), item.getHouse(), item.getCorp(), null));
        propertyItem.setMetro(transformMetro(item.getMetro(), item.getMetroTime()));
        propertyItem.setBranch(transformMetroBranch(item.getMetro(), item.getMetroBranch()));
        propertyItem.setThumb(item.getThumb());
        return propertyItem;
    }

    public PropertyItem mapProperty(RentRooms item){
        PropertyItem propertyItem = new PropertyItem();
        propertyItem.setId(item.getId());
        propertyItem.setSection(item.getSection());
        propertyItem.setPrice(item.getPrice());
        propertyItem.setModifiedDate(item.getModifiedDate());
        propertyItem.setDate(transformDate(item.getModifiedDate()));
        propertyItem.setCost(transformPrice(item.getPrice()));
        propertyItem.setInfo(transformRoomsInfo(item.getRooms(), item.getSquareUseful(), item.getFloor(), item.getTotalFloors()));
        propertyItem.setAddress(transformAddress(item.getCity(), item.getDistrict(), item.getStreet(), item.getHouse(), item.getCorp(), null));
        propertyItem.setMetro(transformMetro(item.getMetro(), item.getMetroTime()));
        propertyItem.setBranch(transformMetroBranch(item.getMetro(), item.getMetroBranch()));
        propertyItem.setThumb(item.getThumb());
        return propertyItem;
    }

    public PropertyItem mapProperty(RentHouses item){
        PropertyItem propertyItem = new PropertyItem();
        propertyItem.setId(item.getId());
        propertyItem.setSection(item.getSection());
        propertyItem.setPrice(item.getPrice());
        propertyItem.setModifiedDate(item.getModifiedDate());
        propertyItem.setDate(transformDate(item.getModifiedDate()));
        propertyItem.setCost(transformPrice(item.getPrice()));
        propertyItem.setInfo(transformHousesInfo(item.getView(), item.getSquare(), item.getLandArea(), item.getLevels()));
        propertyItem.setAddress(transformAddress(item.getCity(), item.getDistrict(), item.getStreet(), item.getHouse(), item.getCorp(), item.getDirection()));
        propertyItem.setMetro(null);
        propertyItem.setBranch(null);
        propertyItem.setThumb(item.getThumb());
        return propertyItem;
    }

    // Reformatting date
    public String transformDate(Date date){
        return DATE_FORMAT.format(date);
    }

    // Reformatting and checking price
    public String transformPrice(Integer price){
        if (price != null) {
            String sign = "$";
            if (preferencesHelper.getRate().equals("BYN")){
                price = Math.round(price * USD_RATE);
                sign = "руб.";
            }
            DecimalFormat fmt = new DecimalFormat();
            DecimalFormatSymbols fmts = new DecimalFormatSymbols();
            fmts.setGroupingSeparator(' ');
            fmt.setGroupingSize(3);
            fmt.setGroupingUsed(true);
            fmt.setDecimalFormatSymbols(fmts);
            return String.format("%s %s", fmt.format(price), sign);
        } else {
            return NOT_PRICE;
        }
    }

    // Checking and building info

    private String transformFlatsInfo(String rooms, Float square, Integer floor, Integer total_floor) {
        List<String> info = new ArrayList<>(3);
        if (rooms != null) info.add(rooms + " комн.");
        if (square != null) info.add(square + " м²");
        if (floor != null) info.add(total_floor != null ? floor + " этаж из " + total_floor : floor + " этаж");
        if (!info.isEmpty()) {
            return separateData(info);
        } else {
            return NOT_INFO;
        }
    }

    private String transformRoomsInfo(String rooms, Float square, Integer floor, Integer total_floor) {
        List<String> info = new ArrayList<>(3);
        if (rooms != null) info.add(rooms);
        if (square != null) info.add(square + " м²");
        if (floor != null)
            info.add(total_floor != null ? floor + " этаж из " + total_floor : floor + " этаж");
        if (!info.isEmpty()) {
            return separateData(info);
        } else {
            return NOT_INFO;
        }
    }

    private String transformHousesInfo(String view, Float square, Float area, Integer levels) {
        List<String> info = new ArrayList<>(4);
        if (view != null) info.add(view);
        if (square != null) info.add(square + " м²");
        if (area != null) info.add(area + " соток");
        if (levels != null) info.add("уровней " + levels);
        if (!info.isEmpty()) {
            return separateData(info);
        } else {
            return NOT_INFO;
        }
    }

    private String transformAreasInfo(Float area) {
        if (area != null) {
            return area + " соток";
        } else {
            return NOT_INFO;
        }
    }

    // Checking and building address
    private String transformAddress(String city, String district, String street, Integer house, String corp, String direction){
        List<String> info = new ArrayList<>(4);
        if (city != null) info.add(city);
        if (district != null && city == null) info.add(district);
        if (street != null) info.add(street);
        if (house != null) info.add(corp != null ? house + "-" + corp + " д." : house + " д.");
        if (direction != null) info.add(direction);
        if (!info.isEmpty()) {
            return separateData(info);
        } else {
            return NOT_ADDRESS;
        }
    }

    // Checking and adding metro station
    private String transformMetro(String name, String time){
        if (name != null) {
            return time != null ? name + ", " + time : name;
        } else {
            return null;
        }
    }

    // Checking and adding metro branch
    private String transformMetroBranch(String name, String branch){
        if (name != null && branch != null) {
            if (branch.equals("Заводская линия")) {
                return "RED";
            } else {
               return "BLUE";
            }
        } else if (name != null) {
            if (red_stations.contains(name)) {
                return "RED";
            } else {
                return "BLUE";
            }
        } else {
            return null;
        }
    }

    // Separates data by ","
    private String separateData(List<String> items){
        String first = items.get(0);
        StringBuilder builder = new StringBuilder();
        for (String item:items) {
            if (!item.equals(first)){
                builder.append(", ");
            }
            builder.append(item);
        }
       return builder.toString();
    }
}
