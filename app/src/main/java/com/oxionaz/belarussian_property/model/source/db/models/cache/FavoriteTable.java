package com.oxionaz.belarussian_property.model.source.db.models.cache;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.oxionaz.belarussian_property.model.source.db.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import java.io.Serializable;
import java.util.Date;

@Table(database = AppDatabase.class)
public class FavoriteTable extends BaseModel implements Serializable {

    @SerializedName("property")
    @Expose
    @PrimaryKey
    String property;
    @SerializedName("id")
    @Expose
    @PrimaryKey
    Integer id;
    @SerializedName("section")
    @Expose
    @Column
    private Integer section;
    @SerializedName("price")
    @Expose
    @Column
    private Integer price;
    @SerializedName("modified_date")
    @Expose
    @Column
    private Date modifiedDate;
    @SerializedName("date")
    @Expose
    @Column
    private String date;
    @SerializedName("cost")
    @Expose
    @Column
    private String cost;
    @SerializedName("info")
    @Expose
    @Column
    private String info;
    @SerializedName("address")
    @Expose
    @Column
    private String address;
    @SerializedName("metro")
    @Expose
    @Column
    private String metro;
    @SerializedName("branch")
    @Expose
    @Column
    private String branch;
    @SerializedName("thumb")
    @Expose
    @Column
    private String thumb;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
