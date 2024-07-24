package com.oxionaz.belarussian_property.model.source.rest.dto.flats;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.oxionaz.belarussian_property.model.source.db.models.InfoTable;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleFlats;

public class SaleFlatsContainerDTO {

    @SerializedName("info")
    @Expose
    private InfoTable infoTable;
    @SerializedName("sale_flats")
    @Expose
    private List<SaleFlats> saleFlatses;

    public InfoTable getInfoTable() {
        return infoTable;
    }

    public void setInfoTable(InfoTable infoTable) {
        this.infoTable = infoTable;
    }

    public List<SaleFlats> getSaleFlatses() {
        return saleFlatses;
    }

    public void setSaleFlatses(List<SaleFlats> saleFlatses) {
        this.saleFlatses = saleFlatses;
    }

}
