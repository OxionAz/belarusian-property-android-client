package com.oxionaz.belarussian_property.view.activities;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mzelzoghbi.zgallery.ZGallery;
import com.mzelzoghbi.zgallery.entities.ZColor;
import com.oxionaz.belarussian_property.R;
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.RentFlatDTO;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.presenter.BasePropertyPresenter;
import com.oxionaz.belarussian_property.presenter.RentFlatPresenter;
import com.oxionaz.belarussian_property.presenter.mapping.PropertyItem;
import com.oxionaz.belarussian_property.view.adapters.ImageAdapter;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

@EActivity(R.layout.activity_rent_flat)
@OptionsMenu(R.menu.property_info_menu)
public class RentFlatActivity extends BasePropertyActivity implements OnMapReadyCallback {

    private Bundle state;
    private PropertyItem propertyItem;
    private RentFlatDTO rentFlatDTO;
    private boolean favorite;

    @Inject
    RentFlatPresenter rentFlatPresenter;

    @OptionsMenuItem
    MenuItem option_favorite;

    @ViewById(R.id.main_toolbar)
    Toolbar toolbar;

    @ViewById
    LinearLayout property_photo_container;

    @ViewById
    RecyclerView image_recycler_view;

    @ViewById
    MapView map;

    @ViewById
    TextView message_text;

    @ViewById
    Button download_button;

    @ViewById
    ProgressBar progress_bar;

    @ViewById
    NestedScrollView info_container;

    @ViewById
    FloatingActionButton phone_button;

    @ViewById
    TextView property_date;

    @ViewById
    TextView property_views;

    @ViewById
    TextView property_cost;

    @ViewById
    TextView property_info;

    @ViewById
    TextView property_address;

    @ViewById
    TextView property_update_date;

    @ViewById
    TextView property_agent;

    @ViewById
    TextView property_contact;

    @ViewById
    TextView property_email;

    @ViewById
    TextView property_region;

    @ViewById
    TextView property_direction;

    @ViewById
    TextView property_city;

    @ViewById
    TextView property_district;

    @ViewById
    TextView property_street;

    @ViewById
    TextView property_house;

    @ViewById
    LinearLayout property_metro_container;

    @ViewById
    TextView property_station;

    @ViewById
    TextView property_metro_time;

    @ViewById
    TextView property_beds;

    @ViewById
    TextView property_furniture;

    @ViewById
    TextView property_cooker;

    @ViewById
    TextView property_floor;

    @ViewById
    TextView property_rooms;

    @ViewById
    TextView property_square;

    @ViewById
    TextView property_square_useful;

    @ViewById
    TextView property_kitchen;

    @ViewById
    TextView property_ceiling_height;

    @ViewById
    TextView property_plan;

    @ViewById
    TextView property_repair;

    @ViewById
    TextView property_floors;

    @ViewById
    TextView property_balcony;

    @ViewById
    TextView property_wc;

    @ViewById
    LinearLayout property_appliances_container;

    @ViewById
    TextView property_appliances;

    @ViewById
    LinearLayout property_house_container;

    @ViewById
    TextView property_type;

    @ViewById
    TextView property_year_of_construction;

    @ViewById
    LinearLayout property_seller_info_container;

    @ViewById
    TextView property_additionally;

    @ViewById
    LinearLayout seller_separator;

    @ViewById
    TextView property_notes;

    @ViewById
    TextView property_rent_conditions;

    @ViewById
    TextView property_rent_terms;

    @ViewById
    TextView property_prepayment;

    @ViewById
    TextView property_price;

    @ViewById
    TextView property_site;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        state = savedInstanceState;
        rentFlatPresenter.onCreate(this);
        propertyItem = (PropertyItem) getIntent().getSerializableExtra("property");
        favorite = (boolean) getIntent().getSerializableExtra("favorite");
    }

    @AfterViews
    void ready(){
        setActionBar();
        setTitle(null);
        map.onCreate(state);
        image_recycler_view.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        image_recycler_view.setLayoutManager(linearLayoutManager);
        rentFlatPresenter.downloadPropertyInfo(propertyItem.getId(), propertyItem.getSection(),
                propertyItem.getPrice() != null ? propertyItem.getPrice().toString() : "NULL");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            Geocoder geocoder = new Geocoder(this);
            List<Address> addresses = geocoder.getFromLocationName(propertyItem.getAddress(), 1);
            double latitude= addresses.get(0).getLatitude();
            double longitude= addresses.get(0).getLongitude();

            LatLng location = new LatLng(latitude, longitude);
            googleMap.getUiSettings().setAllGesturesEnabled(false);
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(10));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            googleMap.addMarker(new MarkerOptions().position(location).draggable(false));
            googleMap.setOnMarkerClickListener(marker -> {
                Intent map1 = new Intent(getContext(), MapActivity.class);
                map1.putExtra("address", propertyItem.getAddress());
                startActivity(map1);
                return true;
            });
            googleMap.setOnMapClickListener(latLng -> {
                Intent map1 = new Intent(getContext(), MapActivity.class);
                map1.putExtra("address", propertyItem.getAddress());
                startActivity(map1);
            });
        } catch (Exception e) {
            map.setVisibility(View.GONE);
            message_text.setText("Не удалось найти на карте");
            message_text.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        map.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        map.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        map.onLowMemory();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (favorite) {
            option_favorite.setIcon(R.drawable.ic_bookmark_black_24dp);
        } else {
            option_favorite.setIcon(R.drawable.ic_bookmark_border_black_24dp);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private void setActionBar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @OptionsItem(R.id.option_share)
    void share(){
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Site URL");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, rentFlatDTO.getUrl());
        startActivity(Intent.createChooser(sharingIntent, "Поделиться"));
    }

    @OptionsItem(R.id.option_favorite)
    void favorite(){
        if (favorite) {
            favorite = false;
            option_favorite.setIcon(R.drawable.ic_bookmark_border_black_24dp);
            rentFlatPresenter.deleteFavorite(propertyItem);
        } else {
            favorite = true;
            option_favorite.setIcon(R.drawable.ic_bookmark_black_24dp);
            rentFlatPresenter.saveFavorite(propertyItem);
        }
    }

    @Click(R.id.download_button)
    void downloadAgain(){
        rentFlatPresenter.downloadPropertyInfo(propertyItem.getId(), propertyItem.getSection(),
                propertyItem.getPrice() != null ? propertyItem.getPrice().toString() : "NULL");
    }

    @Click(R.id.phone_button)
    void showPhones(View view){
       if (rentFlatDTO.getNumbers() != null) {
           PopupMenu phones = new PopupMenu(this, view);
           for (String number : rentFlatDTO.getNumbers()){
               phones.getMenu().add(number);
           }
           phones.setOnMenuItemClickListener(item -> {
               Intent intent = new Intent(Intent.ACTION_DIAL);
               intent.setData(Uri.parse("tel:" + item.getTitle().toString()));
               startActivity(intent);
               return true;
           });
           phones.show();
       }
    }

    @Click(R.id.site_button)
    void showOnSite(){
        Uri uriUrl = Uri.parse(rentFlatDTO.getUrl());
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @OptionsItem(android.R.id.home)
    void back(){
        onBackPressed();
    }

    @Override
    public <P> void showPropertyInfo(P item) {
        RentFlatDTO rentFlat = (RentFlatDTO) item;
        this.rentFlatDTO = rentFlat;

        if (rentFlat.getPhotos() != null) {
            ImageAdapter imageAdapter = new ImageAdapter(rentFlat.getPhotos(), id ->
                    ZGallery.with(this, (ArrayList<String>) rentFlat.getPhotos())
                            .setToolbarTitleColor(ZColor.WHITE) // toolbar title color
                            .setGalleryBackgroundColor(ZColor.BLACK) // activity background color
                            .setToolbarColorResId(R.color.colorPrimaryDark) // toolbar color
                            .setTitle(String.valueOf(rentFlat.getPhotos().size()))
                            .setSelectedImgPosition(id)
                            .show());
            image_recycler_view.setAdapter(imageAdapter);
            property_photo_container.setVisibility(View.VISIBLE);
        }

        property_date.setText(propertyItem.getDate());
        property_views.setText(String.valueOf(rentFlat.getViews()));
        property_cost.setText(propertyItem.getCost());
        property_info.setText(propertyItem.getInfo());
        property_address.setText(propertyItem.getAddress());
        property_update_date.setText(propertyItem.getDate());
        property_agent.setText(rentFlat.getAgent() != null ? rentFlat.getAgent() : "Не указано");
        property_contact.setText(rentFlat.getContact() != null ? rentFlat.getContact() : "Не указано");
        property_email.setText(rentFlat.getEmail() != null ? rentFlat.getEmail() : "Не указано");
        property_region.setText(rentFlat.getRegion() != null ? rentFlat.getRegion() : "Не указано");
        property_direction.setText(rentFlat.getDirection() != null ? rentFlat.getDirection() : "Не указано");
        property_city.setText(rentFlat.getCity() != null ? rentFlat.getCity() : "Не указано");
        property_district.setText(rentFlat.getDistrict() != null ? rentFlat.getDistrict() : "Не указано");
        property_street.setText(rentFlat.getStreet() != null ? rentFlat.getStreet() : "Не указано");
        property_house.setText(rentFlat.getCorp());
        if (rentFlat.getMetro() != null) {
            property_station.setText(rentFlat.getMetro() != null ? rentFlat.getMetro() : "Не указано");
            property_metro_time.setText(rentFlat.getMetroTime() != null ? rentFlat.getMetroTime() : "Не указано");
        } else {
            property_metro_container.setVisibility(View.GONE);
        }
        property_beds.setText(rentFlat.getBeds() != null ? rentFlat.getBeds() : "Не указано");
        property_furniture.setText(rentFlat.isFurniture() ? "Есть" : "Не указано");
        property_cooker.setText(rentFlat.getCooker() != null ? rentFlat.getCooker() : "Не указано");
        property_floor.setText(rentFlat.getFloor() != null ?
                (rentFlat.getTotalFloors() != null ? rentFlat.getFloor() + " из " + rentFlat.getTotalFloors() : rentFlat.getFloor().toString())
                : "Не указано");
        property_rooms.setText(rentFlat.getRooms() != null ? rentFlat.getRooms() : "Не указано");
        property_square.setText(rentFlat.getSquare() != null ? rentFlat.getSquare() + " м²" : "Не указано");
        property_square_useful.setText(rentFlat.getSquareUseful() != null ? rentFlat.getSquareUseful() + " м²" : "Не указано");
        property_kitchen.setText(rentFlat.getKitchen() != null ? rentFlat.getKitchen() + " м²" : "Не указано");
        property_ceiling_height.setText(rentFlat.getCeilingHeight() != null ? rentFlat.getCeilingHeight() + " м" : "Не указано");
        property_plan.setText(rentFlat.getPlan() != null ? rentFlat.getPlan() : "Не указано");
        property_repair.setText(rentFlat.getRepair() != null ? rentFlat.getRepair() : "Не указано");
        property_floors.setText(rentFlat.getFloors() != null ? rentFlat.getFloors() : "Не указано");
        property_balcony.setText(rentFlat.getBalcony() != null ? rentFlat.getBalcony() : "Не указано");
        property_wc.setText(rentFlat.getWc() != null ? rentFlat.getWc() : "Не указано");
        if (rentFlat.getAppliances() != null) {
            property_appliances.setText(rentFlat.getAppliances());
        } else {
            property_appliances_container.setVisibility(View.GONE);
        }
        if (rentFlat.getType() != null || rentFlat.getYearOfConstruction() != null) {
            property_type.setText(rentFlat.getType() != null ? rentFlat.getType() : "Не указано");
            property_year_of_construction.setText(rentFlat.getYearOfConstruction() != null ? rentFlat.getYearOfConstruction().toString() : "Не указано");
        } else {
            property_house_container.setVisibility(View.GONE);
        }
        if (rentFlat.getAdditionally() != null || rentFlat.getNotes() != null) {
            if (rentFlat.getAdditionally() != null) property_additionally.setText(rentFlat.getAdditionally()); else property_additionally.setVisibility(View.GONE);
            if (rentFlat.getNotes() != null) property_notes.setText(rentFlat.getNotes()); else property_notes.setVisibility(View.GONE);
            if (rentFlat.getAdditionally() != null && rentFlat.getNotes() != null) seller_separator.setVisibility(View.VISIBLE);
            else seller_separator.setVisibility(View.GONE);
        } else {
            property_seller_info_container.setVisibility(View.GONE);
        }
        property_rent_conditions.setText(rentFlat.getRentConditions() != null ? rentFlat.getRentConditions() : "Не указано");
        property_rent_terms.setText(rentFlat.getRentTerms() != null ? rentFlat.getRentTerms() : "Не указано");
        property_prepayment.setText(rentFlat.getPrepayment() != null ? rentFlat.getPrepayment() : "Не указано");
        property_price.setText(rentFlat.getPrice() != null ? rentFlat.getPrice() + " $" : "Не указано");
        property_site.setText(rentFlat.getSite() != null ? rentFlat.getSite() : "Не указано");

        hideViews();
        setTitle("Информация");
        phone_button.setVisibility(View.VISIBLE);
        info_container.setVisibility(View.VISIBLE);
        map.setVisibility(View.VISIBLE);
        map.getMapAsync(this);
    }

    @Override
    public void showLoading() {
        progress_bar.setVisibility(View.VISIBLE);
        message_text.setVisibility(View.GONE);
        download_button.setVisibility(View.GONE);
    }

    @Override
    public void hideViews() {
        progress_bar.setVisibility(View.GONE);
    }

    @Override
    public void showNotFoundMessage(String message) {
        hideViews();
        message_text.setText(message);
        message_text.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String message) {
        hideViews();
        message_text.setText(message);
        message_text.setVisibility(View.VISIBLE);
        download_button.setVisibility(View.VISIBLE);
    }

    @Override
    protected BasePropertyPresenter getPresenter() {
        return rentFlatPresenter;
    }
}
