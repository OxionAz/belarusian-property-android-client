
package com.oxionaz.belarussian_property.model.source.rest.dto.flats;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.oxionaz.belarussian_property.model.source.db.models.cache.rent.RentFlats;
import com.oxionaz.belarussian_property.model.source.db.models.InfoTable;

public class RentFlatsContainerDTO {

    @SerializedName("info")
    @Expose
    private InfoTable infoTable;
    @SerializedName("rent_flats")
    @Expose
    private List<RentFlats> rentFlatses;

    public InfoTable getInfo() {
        return infoTable;
    }

    public void setInfo(InfoTable info) {
        this.infoTable = info;
    }

    public List<RentFlats> getRentFlats() {
        return rentFlatses;
    }

    public void setRentFlats(List<RentFlats> rentFlats) {
        this.rentFlatses = rentFlats;
    }

}
