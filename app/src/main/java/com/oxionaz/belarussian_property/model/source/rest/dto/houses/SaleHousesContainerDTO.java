package com.oxionaz.belarussian_property.model.source.rest.dto.houses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.oxionaz.belarussian_property.model.source.db.models.InfoTable;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleHouses;

public class SaleHousesContainerDTO {

    @SerializedName("info")
    @Expose
    private InfoTable infoTable;
    @SerializedName("sale_houses")
    @Expose
    private List<SaleHouses> saleHouses;

    public InfoTable getInfo() {
        return infoTable;
    }

    public void setInfo(InfoTable info) {
        this.infoTable = info;
    }

    public List<SaleHouses> getSaleHouses() {
        return saleHouses;
    }

    public void setSaleHouses(List<SaleHouses> saleHouses) {
        this.saleHouses = saleHouses;
    }

}
