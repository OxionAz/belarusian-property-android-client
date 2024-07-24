package com.oxionaz.belarussian_property.model.source.rest.dto.areas;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleAreas;
import com.oxionaz.belarussian_property.model.source.db.models.InfoTable;

public class SaleAreasContainerDTO {

    @SerializedName("info")
    @Expose
    private InfoTable infoTable;
    @SerializedName("sale_areas")
    @Expose
    private List<SaleAreas> saleAreases;

    public InfoTable getInfoTable() {
        return infoTable;
    }

    public void setInfoTable(InfoTable infoTable) {
        this.infoTable = infoTable;
    }

    public List<SaleAreas> getSaleAreas() {
        return saleAreases;
    }

    public void setSaleAreas(List<SaleAreas> saleAreas) {
        this.saleAreases = saleAreas;
    }

}
