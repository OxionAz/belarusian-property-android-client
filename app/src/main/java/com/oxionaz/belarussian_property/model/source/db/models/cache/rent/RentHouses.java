package com.oxionaz.belarussian_property.model.source.db.models.cache.rent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.oxionaz.belarussian_property.model.source.db.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import java.util.Date;

@Table(database = AppDatabase.class)
public class RentHouses extends BaseModel {

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
    @SerializedName("direction")
    @Expose
    @Column
    private String direction;
    @SerializedName("district")
    @Expose
    @Column
    private String district;
    @SerializedName("house")
    @Expose
    @Column
    private Integer house;
    @SerializedName("land_area")
    @Expose
    @Column
    private Float landArea;
    @SerializedName("levels")
    @Expose
    @Column
    private Integer levels;
    @SerializedName("modified_date")
    @Expose
    @Column
    private Date modifiedDate;
    @SerializedName("price")
    @Expose
    @Column
    private Integer price;
    @SerializedName("section")
    @Expose
    @Column
    private Integer section;
    @SerializedName("square")
    @Expose
    @Column
    private Float square;
    @SerializedName("street")
    @Expose
    @Column
    private String street;
    @SerializedName("thumb")
    @Expose
    @Column
    private String thumb;
    @SerializedName("view")
    @Expose
    @Column
    private String view;

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

    public Float getLandArea() {
        return landArea;
    }

    public void setLandArea(Float landArea) {
        this.landArea = landArea;
    }

    public Integer getLevels() {
        return levels;
    }

    public void setLevels(Integer levels) {
        this.levels = levels;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
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

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
