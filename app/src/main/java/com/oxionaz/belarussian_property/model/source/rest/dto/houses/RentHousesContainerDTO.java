package com.oxionaz.belarussian_property.model.source.rest.dto.houses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.oxionaz.belarussian_property.model.source.db.models.InfoTable;
import com.oxionaz.belarussian_property.model.source.db.models.cache.rent.RentHouses;

public class RentHousesContainerDTO {

    @SerializedName("info")
    @Expose
    private InfoTable infoTable;
    @SerializedName("rent_houses")
    @Expose
    private List<RentHouses> rentHouses;

    public InfoTable getInfo() {
        return infoTable;
    }

    public void setInfo(InfoTable info) {
        this.infoTable = info;
    }

    public List<RentHouses> getRentHouses() {
        return rentHouses;
    }

    public void setRentHouses(List<RentHouses> rentHouses) {
        this.rentHouses = rentHouses;
    }

}
