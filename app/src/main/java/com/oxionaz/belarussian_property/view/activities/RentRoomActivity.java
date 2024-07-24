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
import com.oxionaz.belarussian_property.model.source.rest.dto.rooms.RentRoomDTO;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.presenter.BasePropertyPresenter;
import com.oxionaz.belarussian_property.presenter.RentRoomPresenter;
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

@EActivity(R.layout.activity_rent_room)
@OptionsMenu(R.menu.property_info_menu)
public class RentRoomActivity extends BasePropertyActivity implements OnMapReadyCallback {

    private Bundle state;
    private PropertyItem propertyItem;
    private RentRoomDTO rentRoomDTO;
    private boolean favorite;

    @Inject
    RentRoomPresenter rentRoomPresenter;

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
    TextView property_neighbors;

    @ViewById
    TextView property_beds;

    @ViewById
    TextView property_furniture;

    @ViewById
    TextView property_cooker;

    @ViewById
    TextView property_floor;   

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
        rentRoomPresenter.onCreate(this);
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
        rentRoomPresenter.downloadPropertyInfo(propertyItem.getId(), propertyItem.getSection(),
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
        sharingIntent.putExtra(Intent.EXTRA_TEXT, rentRoomDTO.getUrl());
        startActivity(Intent.createChooser(sharingIntent, "Поделиться"));
    }

    @OptionsItem(R.id.option_favorite)
    void favorite(){
        if (favorite) {
            favorite = false;
            option_favorite.setIcon(R.drawable.ic_bookmark_border_black_24dp);
            rentRoomPresenter.deleteFavorite(propertyItem);
        } else {
            favorite = true;
            option_favorite.setIcon(R.drawable.ic_bookmark_black_24dp);
            rentRoomPresenter.saveFavorite(propertyItem);
        }
    }

    @Click(R.id.download_button)
    void downloadAgain(){
        rentRoomPresenter.downloadPropertyInfo(propertyItem.getId(), propertyItem.getSection(),
                propertyItem.getPrice() != null ? propertyItem.getPrice().toString() : "NULL");
    }

    @Click(R.id.phone_button)
    void showPhones(View view){
       if (rentRoomDTO.getNumbers() != null) {
           PopupMenu phones = new PopupMenu(this, view);
           for (String number : rentRoomDTO.getNumbers()){
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
        Uri uriUrl = Uri.parse(rentRoomDTO.getUrl());
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @OptionsItem(android.R.id.home)
    void back(){
        onBackPressed();
    }

    @Override
    public <P> void showPropertyInfo(P item) {
        RentRoomDTO rentRoom = (RentRoomDTO) item;
        this.rentRoomDTO = rentRoom;

        if (rentRoom.getPhotos() != null) {
            ImageAdapter imageAdapter = new ImageAdapter(rentRoom.getPhotos(), id ->
                    ZGallery.with(this, (ArrayList<String>) rentRoom.getPhotos())
                            .setToolbarTitleColor(ZColor.WHITE) // toolbar title color
                            .setGalleryBackgroundColor(ZColor.BLACK) // activity background color
                            .setToolbarColorResId(R.color.colorPrimaryDark) // toolbar color
                            .setTitle(String.valueOf(rentRoom.getPhotos().size()))
                            .setSelectedImgPosition(id)
                            .show());
            image_recycler_view.setAdapter(imageAdapter);
            property_photo_container.setVisibility(View.VISIBLE);
        }

        property_date.setText(propertyItem.getDate());
        property_views.setText(String.valueOf(rentRoom.getViews()));
        property_cost.setText(propertyItem.getCost());
        property_info.setText(propertyItem.getInfo());
        property_address.setText(propertyItem.getAddress());
        property_update_date.setText(propertyItem.getDate());
        property_agent.setText(rentRoom.getAgent() != null ? rentRoom.getAgent() : "Не указано");
        property_contact.setText(rentRoom.getContact() != null ? rentRoom.getContact() : "Не указано");
        property_email.setText(rentRoom.getEmail() != null ? rentRoom.getEmail() : "Не указано");
        property_region.setText(rentRoom.getRegion() != null ? rentRoom.getRegion() : "Не указано");
        property_direction.setText(rentRoom.getDirection() != null ? rentRoom.getDirection() : "Не указано");
        property_city.setText(rentRoom.getCity() != null ? rentRoom.getCity() : "Не указано");
        property_district.setText(rentRoom.getDistrict() != null ? rentRoom.getDistrict() : "Не указано");
        property_street.setText(rentRoom.getStreet() != null ? rentRoom.getStreet() : "Не указано");
        property_house.setText(rentRoom.getCorp());
        if (rentRoom.getMetro() != null) {
            property_station.setText(rentRoom.getMetro() != null ? rentRoom.getMetro() : "Не указано");
            property_metro_time.setText(rentRoom.getMetroTime() != null ? rentRoom.getMetroTime() : "Не указано");
        } else {
            property_metro_container.setVisibility(View.GONE);
        }
        property_neighbors.setText(rentRoom.getNeighbors() != null ? rentRoom.getNeighbors() : "Не указано");
        property_beds.setText(rentRoom.getBeds() != null ? rentRoom.getBeds() : "Не указано");
        property_furniture.setText(rentRoom.isFurniture() ? "Есть" : "Не указано");
        property_cooker.setText(rentRoom.getCooker() != null ? rentRoom.getCooker() : "Не указано");
        property_floor.setText(rentRoom.getFloor() != null ?
                (rentRoom.getTotalFloors() != null ? rentRoom.getFloor() + " из " + rentRoom.getTotalFloors() : rentRoom.getFloor().toString())
                : "Не указано");
        property_square.setText(rentRoom.getSquare() != null ? rentRoom.getSquare() + " м²" : "Не указано");
        property_square_useful.setText(rentRoom.getSquareUseful() != null ? rentRoom.getSquareUseful() + " м²" : "Не указано");
        property_kitchen.setText(rentRoom.getKitchen() != null ? rentRoom.getKitchen() + " м²" : "Не указано");
        property_ceiling_height.setText(rentRoom.getCeilingHeight() != null ? rentRoom.getCeilingHeight() + " м" : "Не указано");
        property_plan.setText(rentRoom.getPlan() != null ? rentRoom.getPlan() : "Не указано");
        property_repair.setText(rentRoom.getRepair() != null ? rentRoom.getRepair() : "Не указано");
        property_floors.setText(rentRoom.getFloors() != null ? rentRoom.getFloors() : "Не указано");
        property_balcony.setText(rentRoom.getBalcony() != null ? rentRoom.getBalcony() : "Не указано");
        property_wc.setText(rentRoom.getWc() != null ? rentRoom.getWc() : "Не указано");
        if (rentRoom.getAppliances() != null) {
            property_appliances.setText(rentRoom.getAppliances());
        } else {
            property_appliances_container.setVisibility(View.GONE);
        }
        if (rentRoom.getType() != null || rentRoom.getYearOfConstruction() != null) {
            property_type.setText(rentRoom.getType() != null ? rentRoom.getType() : "Не указано");
            property_year_of_construction.setText(rentRoom.getYearOfConstruction() != null ? rentRoom.getYearOfConstruction().toString() : "Не указано");
        } else {
            property_house_container.setVisibility(View.GONE);
        }
        if (rentRoom.getAdditionally() != null || rentRoom.getNotes() != null) {
            if (rentRoom.getAdditionally() != null) property_additionally.setText(rentRoom.getAdditionally()); else property_additionally.setVisibility(View.GONE);
            if (rentRoom.getNotes() != null) property_notes.setText(rentRoom.getNotes()); else property_notes.setVisibility(View.GONE);
            if (rentRoom.getAdditionally() != null && rentRoom.getNotes() != null) seller_separator.setVisibility(View.VISIBLE);
            else seller_separator.setVisibility(View.GONE);
        } else {
            property_seller_info_container.setVisibility(View.GONE);
        }
        property_rent_conditions.setText(rentRoom.getRentConditions() != null ? rentRoom.getRentConditions() : "Не указано");
        property_rent_terms.setText(rentRoom.getRentTerms() != null ? rentRoom.getRentTerms() : "Не указано");
        property_prepayment.setText(rentRoom.getPrepayment() != null ? rentRoom.getPrepayment() : "Не указано");
        property_price.setText(rentRoom.getPrice() != null ? rentRoom.getPrice() + " $" : "Не указано");
        property_site.setText(rentRoom.getSite() != null ? rentRoom.getSite() : "Не указано");

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
        return rentRoomPresenter;
    }
}
