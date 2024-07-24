package com.oxionaz.belarussian_property.other.di;

import com.oxionaz.belarussian_property.presenter.FavoritePresenter;
import com.oxionaz.belarussian_property.presenter.PredictionRentFlatsPresenter;
import com.oxionaz.belarussian_property.presenter.PredictionSaleFlatsPresenter;
import com.oxionaz.belarussian_property.presenter.RentFlatPresenter;
import com.oxionaz.belarussian_property.presenter.RentFlatsPresenter;
import com.oxionaz.belarussian_property.presenter.RentHousePresenter;
import com.oxionaz.belarussian_property.presenter.RentHousesPresenter;
import com.oxionaz.belarussian_property.presenter.RentRoomPresenter;
import com.oxionaz.belarussian_property.presenter.RentRoomsPresenter;
import com.oxionaz.belarussian_property.presenter.SaleAreaPresenter;
import com.oxionaz.belarussian_property.presenter.SaleAreasPresenter;
import com.oxionaz.belarussian_property.presenter.SaleFlatPresenter;
import com.oxionaz.belarussian_property.presenter.SaleFlatsPresenter;
import com.oxionaz.belarussian_property.presenter.SaleHousePresenter;
import com.oxionaz.belarussian_property.presenter.SaleHousesPresenter;
import com.oxionaz.belarussian_property.presenter.SaleRoomPresenter;
import com.oxionaz.belarussian_property.presenter.SaleRoomsPresenter;
import com.oxionaz.belarussian_property.presenter.UserPresenter;
import dagger.Module;
import dagger.Provides;

@Module
class ViewModule {

    @Provides
    UserPresenter provideUserPresenter() {
        return new UserPresenter();
    }

    @Provides
    PredictionSaleFlatsPresenter providePredictionSaleFlatsPresenter() {
        return new PredictionSaleFlatsPresenter();
    }

    @Provides
    PredictionRentFlatsPresenter providePredictionRentFlatsPresenter() {
        return new PredictionRentFlatsPresenter();
    }

    @Provides
    FavoritePresenter provideFavoritePresenter() {
        return new FavoritePresenter();
    }

    @Provides
    SaleFlatPresenter provideSaleFlatPresenter() {
        return new SaleFlatPresenter();
    }

    @Provides
    SaleRoomPresenter provideSaleRoomPresenter() {
        return new SaleRoomPresenter();
    }

    @Provides
    SaleHousePresenter provideSaleHousePresenter() {
        return new SaleHousePresenter();
    }

    @Provides
    SaleAreaPresenter provideSaleAreaPresenter() {
        return new SaleAreaPresenter();
    }

    @Provides
    RentFlatPresenter provideRentFlatPresenter() {
        return new RentFlatPresenter();
    }

    @Provides
    RentRoomPresenter provideRentRoomPresenter() {
        return new RentRoomPresenter();
    }

    @Provides
    RentHousePresenter provideRentHousePresenter() {
        return new RentHousePresenter();
    }

    @Provides
    SaleFlatsPresenter provideSaleFlatsPresenter() {
        return new SaleFlatsPresenter();
    }

    @Provides
    SaleRoomsPresenter provideSaleRoomsPresenter() {
        return new SaleRoomsPresenter();
    }

    @Provides
    SaleHousesPresenter provideSaleHousesPresenter() {
        return new SaleHousesPresenter();
    }

    @Provides
    SaleAreasPresenter provideSaleAreasPresenter() {
        return new SaleAreasPresenter();
    }

    @Provides
    RentFlatsPresenter provideRentFlatsPresenter() {
        return new RentFlatsPresenter();
    }

    @Provides
    RentRoomsPresenter provideRentRoomsPresenter() {
        return new RentRoomsPresenter();
    }

    @Provides
    RentHousesPresenter provideRentHousesPresenter() {
        return new RentHousesPresenter();
    }
}
