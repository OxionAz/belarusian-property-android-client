package com.oxionaz.belarussian_property.model.source.db.models.cache.sale;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.oxionaz.belarussian_property.model.source.db.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import java.util.Date;

@Table(database = AppDatabase.class)
public class SaleFlats extends BaseModel {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Integer id;
    @SerializedName("city")
    @Expose
    @Column
    private String city;
    @SerializedName("corp")
    @Expose
    @Column
    private String corp;
    @SerializedName("district")
    @Expose
    @Column
    private String district;
    @SerializedName("floor")
    @Expose
    @Column
    private Integer floor;
    @SerializedName("house")
    @Expose
    @Column
    private Integer house;
    @SerializedName("metro")
    @Expose
    @Column
    private String metro;
    @SerializedName("metro_branch")
    @Expose
    @Column
    private String metroBranch;
    @SerializedName("metro_time")
    @Expose
    @Column
    private String metroTime;
    @SerializedName("modified_date")
    @Expose
    @Column
    private Date modifiedDate;
    @SerializedName("price")
    @Expose
    @Column
    private Integer price;
    @SerializedName("rooms")
    @Expose
    @Column
    private String rooms;
    @SerializedName("square")
    @Expose
    @Column
    private Float square;
    @SerializedName("section")
    @Expose
    @Column
    private Integer section;
    @SerializedName("street")
    @Expose
    @Column
    private String street;
    @SerializedName("thumb")
    @Expose
    @Column
    private String thumb;
    @SerializedName("total_floors")
    @Expose
    @Column
    private Integer totalFloors;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }

    public String getMetroBranch() {
        return metroBranch;
    }

    public void setMetroBranch(String metroBranch) {
        this.metroBranch = metroBranch;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public Float getSquare() {
        return square;
    }

    public void setSquare(Float square) {
        this.square = square;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public Integer getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(Integer totalFloors) {
        this.totalFloors = totalFloors;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getMetroTime() {
        return metroTime;
    }

    public void setMetroTime(String metroTime) {
        this.metroTime = metroTime;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }
}
