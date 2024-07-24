package com.oxionaz.belarussian_property.model.source.rest.dto.rooms;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.oxionaz.belarussian_property.model.source.db.models.InfoTable;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleRooms;

public class SaleRoomsContainerDTO {

    @SerializedName("info")
    @Expose
    private InfoTable infoTable;
    @SerializedName("sale_rooms")
    @Expose
    private List<SaleRooms> saleRoomses;

    public InfoTable getInfo() {
        return infoTable;
    }

    public void setInfo(InfoTable info) {
        this.infoTable = info;
    }

    public List<SaleRooms> getSaleRooms() {
        return saleRoomses;
    }

    public void setSaleRooms(List<SaleRooms> saleRooms) {
        this.saleRoomses = saleRooms;
    }

}
