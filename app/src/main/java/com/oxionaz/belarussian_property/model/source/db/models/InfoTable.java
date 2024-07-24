package com.oxionaz.belarussian_property.model.source.db.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.oxionaz.belarussian_property.model.source.db.AppDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class InfoTable extends BaseModel {

    @PrimaryKey
    String property;
    @SerializedName("count")
    @Expose
    @Column
    private Integer count;
    @SerializedName("next")
    @Expose
    @Column
    private Integer next;
    @SerializedName("price_median")
    @Expose
    @Column
    private Float priceMedian;
    @SerializedName("quantity")
    @Expose
    @Column
    private Integer quantity;
    @SerializedName("sort_date_asc")
    @Expose
    @Column
    private boolean sortDateAsc;
    @SerializedName("sort_date_desc")
    @Expose
    @Column
    private boolean sortDateDesc;
    @SerializedName("sort_price_asc")
    @Expose
    @Column
    private boolean sortPriceAsc;
    @SerializedName("sort_price_desc")
    @Expose
    @Column
    private boolean sortPriceDesc;
    @SerializedName("start")
    @Expose
    @Column
    private Integer start;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getNext() {
        return next;
    }

    public void setNext(Integer next) {
        this.next = next;
    }

    public Float getPriceMedian() {
        return priceMedian;
    }

    public void setPriceMedian(Float priceMedian) {
        this.priceMedian = priceMedian;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public boolean isSortDateAsc() {
        return sortDateAsc;
    }

    public void setSortDateAsc(boolean sortDateAsc) {
        this.sortDateAsc = sortDateAsc;
    }

    public boolean isSortDateDesc() {
        return sortDateDesc;
    }

    public void setSortDateDesc(boolean sortDateDesc) {
        this.sortDateDesc = sortDateDesc;
    }

    public boolean isSortPriceAsc() {
        return sortPriceAsc;
    }

    public void setSortPriceAsc(boolean sortPriceAsc) {
        this.sortPriceAsc = sortPriceAsc;
    }

    public boolean isSortPriceDesc() {
        return sortPriceDesc;
    }

    public void setSortPriceDesc(boolean sortPriceDesc) {
        this.sortPriceDesc = sortPriceDesc;
    }
}
