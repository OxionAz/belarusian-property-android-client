package com.oxionaz.belarussian_property.model.source.rest.dto.rooms;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.oxionaz.belarussian_property.model.source.db.models.InfoTable;
import com.oxionaz.belarussian_property.model.source.db.models.cache.rent.RentRooms;

public class RentRoomsContainerDTO {

    @SerializedName("info")
    @Expose
    private InfoTable infoTable;
    @SerializedName("rent_rooms")
    @Expose
    private List<RentRooms> rentRoomses;

    public InfoTable getInfo() {
        return infoTable;
    }

    public void setInfo(InfoTable info) {
        this.infoTable = info;
    }

    public List<RentRooms> getRentRooms() {
        return rentRoomses;
    }

    public void setRentRooms(List<RentRooms> rentRooms) {
        this.rentRoomses = rentRooms;
    }

}
