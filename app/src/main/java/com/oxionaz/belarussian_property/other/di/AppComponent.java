package com.oxionaz.belarussian_property.other.di;

import com.oxionaz.belarussian_property.model.DataManager;
import com.oxionaz.belarussian_property.model.source.ServiceHelper;
import com.oxionaz.belarussian_property.model.source.rest.RestService;
import com.oxionaz.belarussian_property.other.util.ImageDownloader;
import com.oxionaz.belarussian_property.other.util.PreferencesHelper;
import com.oxionaz.belarussian_property.presenter.BasePropertyPresenter;
import com.oxionaz.belarussian_property.presenter.mapping.MapperDTO;
import com.oxionaz.belarussian_property.view.activities.RentFlatActivity;
import com.oxionaz.belarussian_property.view.activities.RentFlatsSearchActivity;
import com.oxionaz.belarussian_property.view.activities.RentHouseActivity;
import com.oxionaz.belarussian_property.view.activities.RentHousesSearchActivity;
import com.oxionaz.belarussian_property.view.activities.RentRoomActivity;
import com.oxionaz.belarussian_property.view.activities.RentRoomsSearchActivity;
import com.oxionaz.belarussian_property.view.activities.SaleAreaActivity;
import com.oxionaz.belarussian_property.view.activities.SaleAreasSearchActivity;
import com.oxionaz.belarussian_property.view.activities.SaleFlatActivity;
import com.oxionaz.belarussian_property.view.activities.MainActivity;
import com.oxionaz.belarussian_property.view.activities.SaleFlatsSearchActivity;
import com.oxionaz.belarussian_property.view.activities.SaleHouseActivity;
import com.oxionaz.belarussian_property.view.activities.SaleHousesSearchActivity;
import com.oxionaz.belarussian_property.view.activities.SaleRoomActivity;
import com.oxionaz.belarussian_property.view.activities.SaleRoomsSearchActivity;
import com.oxionaz.belarussian_property.view.activities.SplashActivity;
import com.oxionaz.belarussian_property.view.adapters.FavoriteAdapter;
import com.oxionaz.belarussian_property.view.adapters.ImageAdapter;
import com.oxionaz.belarussian_property.view.adapters.PropertyAdapter;
import com.oxionaz.belarussian_property.view.fragments.FavoritesFragment;
import com.oxionaz.belarussian_property.view.fragments.LoginFragment;
import com.oxionaz.belarussian_property.view.fragments.PredictionRentFlatsFragment;
import com.oxionaz.belarussian_property.view.fragments.PredictionSaleFlatsFragment;
import com.oxionaz.belarussian_property.view.fragments.QueryTestFragment;
import com.oxionaz.belarussian_property.view.fragments.RegistrationFragment;
import com.oxionaz.belarussian_property.view.fragments.RentFlatsFragment;
import com.oxionaz.belarussian_property.view.fragments.RentHousesFragment;
import com.oxionaz.belarussian_property.view.fragments.RentRoomsFragment;
import com.oxionaz.belarussian_property.view.fragments.SaleAreasFragment;
import com.oxionaz.belarussian_property.view.fragments.SaleFlatsFragment;
import com.oxionaz.belarussian_property.view.fragments.SaleHousesFragment;
import com.oxionaz.belarussian_property.view.fragments.SaleRoomsFragment;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {ServiceHelperModule.class, RestModule.class,
        ModelModule.class, PresenterModule.class, ViewModule.class})
public interface AppComponent {

    void inject(QueryTestFragment queryTestFragment);

    void inject(SplashActivity splashActivity);

    void inject(MainActivity mainActivity);

    void inject(ImageDownloader imageDownloader);

    void inject(ImageAdapter imageAdapter);

    void inject(FavoriteAdapter favoriteAdapter);

    void inject(PropertyAdapter propertyAdapter);

    void inject(MapperDTO mapperDTO);

    void inject(PreferencesHelper preferencesHelper);

    void inject(ServiceHelper serviceHelper);

    void inject(RestService restService);

    void inject(DataManager dataManager);

    void inject(BasePropertyPresenter basePropertyPresenter);

    void inject(LoginFragment loginFragment);

    void inject(RegistrationFragment registrationFragment);

    void inject(PredictionSaleFlatsFragment predictionSaleFlatsFragment);

    void inject(PredictionRentFlatsFragment predictionRentFlatsFragment);

    void inject(FavoritesFragment favoritesFragment);

    void inject(SaleFlatActivity saleFlatActivity);

    void inject(SaleRoomActivity saleRoomActivity);

    void inject(SaleHouseActivity saleHouseActivity);

    void inject(SaleAreaActivity saleAreaActivity);

    void inject(RentFlatActivity rentFlatActivity);

    void inject(RentRoomActivity rentRoomActivity);

    void inject(RentHouseActivity rentHouseActivity);

    void inject(SaleFlatsFragment saleFlatsFragment);

    void inject(SaleRoomsFragment saleRoomsFragment);

    void inject(SaleHousesFragment saleHousesFragment);

    void inject(SaleAreasFragment saleAreasFragment);

    void inject(RentFlatsFragment rentFlatsFragment);

    void inject(RentRoomsFragment rentRoomsFragment);

    void inject(RentHousesFragment rentHousesFragment);

    void inject(SaleFlatsSearchActivity saleFlatsSearchActivity);

    void inject(SaleRoomsSearchActivity saleRoomsSearchActivity);

    void inject(SaleHousesSearchActivity saleHousesSearchActivity);

    void inject(SaleAreasSearchActivity saleAreasSearchActivity);

    void inject(RentFlatsSearchActivity rentFlatsSearchActivity);

    void inject(RentRoomsSearchActivity rentRoomsSearchActivity);

    void inject(RentHousesSearchActivity rentHousesSearchActivity);
}
