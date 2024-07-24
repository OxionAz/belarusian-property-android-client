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
import com.oxionaz.belarussian_property.model.source.rest.dto.rooms.SaleRoomDTO;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.presenter.BasePropertyPresenter;
import com.oxionaz.belarussian_property.presenter.SaleRoomPresenter;
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

@EActivity(R.layout.activity_sale_room)
@OptionsMenu(R.menu.property_info_menu)
public class SaleRoomActivity extends BasePropertyActivity implements OnMapReadyCallback {

    private Bundle state;
    private PropertyItem propertyItem;
    private SaleRoomDTO saleRoomDTO;
    private boolean favorite;

    @Inject
    SaleRoomPresenter saleRoomPresenter;

    @OptionsMenuItem
    MenuItem option_favorite, option_share;

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
    TextView property_sales_terms;

    @ViewById
    TextView property_own;

    @ViewById
    TextView property_price;

    @ViewById
    TextView property_psm;

    @ViewById
    TextView property_site;

    @ViewById
    Button site_button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        state = savedInstanceState;
        saleRoomPresenter.onCreate(this);
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
        saleRoomPresenter.downloadPropertyInfo(propertyItem.getId(),
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
        sharingIntent.putExtra(Intent.EXTRA_TEXT, saleRoomDTO.getUrl());
        startActivity(Intent.createChooser(sharingIntent, "Поделиться"));
    }

    @OptionsItem(R.id.option_favorite)
    void favorite(){
        if (favorite) {
            favorite = false;
            option_favorite.setIcon(R.drawable.ic_bookmark_border_black_24dp);
            saleRoomPresenter.deleteFavorite(propertyItem);
        } else {
            favorite = true;
            option_favorite.setIcon(R.drawable.ic_bookmark_black_24dp);
            saleRoomPresenter.saveFavorite(propertyItem);
        }
    }

    @Click(R.id.download_button)
    void downloadAgain(){
        saleRoomPresenter.downloadPropertyInfo(propertyItem.getId(),
                propertyItem.getPrice() != null ? propertyItem.getPrice().toString() : "NULL");
    }

    @Click(R.id.phone_button)
    void showPhones(View view){
       if (saleRoomDTO.getNumbers() != null) {
           PopupMenu phones = new PopupMenu(this, view);
           for (String number : saleRoomDTO.getNumbers()){
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
        Uri uriUrl = Uri.parse(saleRoomDTO.getUrl());
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @OptionsItem(android.R.id.home)
    void back(){
        onBackPressed();
    }

    @Override
    public <P> void showPropertyInfo(P item) {
        SaleRoomDTO saleRoom = (SaleRoomDTO) item;
        this.saleRoomDTO = saleRoom;

        if (saleRoom.getPhotos() != null) {
            ImageAdapter imageAdapter = new ImageAdapter(saleRoom.getPhotos(), id ->
                    ZGallery.with(this, (ArrayList<String>) saleRoom.getPhotos())
                            .setToolbarTitleColor(ZColor.WHITE) // toolbar title color
                            .setGalleryBackgroundColor(ZColor.BLACK) // activity background color
                            .setToolbarColorResId(R.color.colorPrimaryDark) // toolbar color
                            .setTitle(String.valueOf(saleRoom.getPhotos().size()))
                            .setSelectedImgPosition(id)
                            .show());
            image_recycler_view.setAdapter(imageAdapter);
            property_photo_container.setVisibility(View.VISIBLE);
        }

        property_date.setText(propertyItem.getDate());
        property_views.setText(String.valueOf(saleRoom.getViews()));
        property_cost.setText(propertyItem.getCost());
        property_info.setText(propertyItem.getInfo());
        property_address.setText(propertyItem.getAddress());
        property_update_date.setText(propertyItem.getDate());
        property_agent.setText(saleRoom.getAgent() != null ? saleRoom.getAgent() : "Не указано");
        property_contact.setText(saleRoom.getContact() != null ? saleRoom.getContact() : "Не указано");
        property_email.setText(saleRoom.getEmail() != null ? saleRoom.getEmail() : "Не указано");
        property_region.setText(saleRoom.getRegion() != null ? saleRoom.getRegion() : "Не указано");
        property_direction.setText(saleRoom.getDirection() != null ? saleRoom.getDirection() : "Не указано");
        property_city.setText(saleRoom.getCity() != null ? saleRoom.getCity() : "Не указано");
        property_district.setText(saleRoom.getDistrict() != null ? saleRoom.getDistrict() : "Не указано");
        property_street.setText(saleRoom.getStreet() != null ? saleRoom.getStreet() : "Не указано");
        property_house.setText(saleRoom.getCorp());
        if (saleRoom.getMetro() != null) {
            property_station.setText(saleRoom.getMetro() != null ? saleRoom.getMetro() : "Не указано");
            property_metro_time.setText(saleRoom.getMetroTime() != null ? saleRoom.getMetroTime() : "Не указано");
        } else {
            property_metro_container.setVisibility(View.GONE);
        }
        property_floor.setText(saleRoom.getFloor() != null ?
                (saleRoom.getTotalFloors() != null ? saleRoom.getFloor() + " из " + saleRoom.getTotalFloors() : saleRoom.getFloor().toString())
                : "Не указано");
        property_square.setText(saleRoom.getSquare() != null ? saleRoom.getSquare() + " м²" : "Не указано");
        property_square_useful.setText(saleRoom.getSquareUseful() != null ? saleRoom.getSquareUseful() + " м²" : "Не указано");
        property_kitchen.setText(saleRoom.getKitchen() != null ? saleRoom.getKitchen() + " м²" : "Не указано");
        property_ceiling_height.setText(saleRoom.getCeilingHeight() != null ? saleRoom.getCeilingHeight() + " м" : "Не указано");
        property_plan.setText(saleRoom.getPlan() != null ? saleRoom.getPlan() : "Не указано");
        property_repair.setText(saleRoom.getRepair() != null ? saleRoom.getRepair() : "Не указано");
        property_floors.setText(saleRoom.getFloors() != null ? saleRoom.getFloors() : "Не указано");
        property_balcony.setText(saleRoom.getBalcony() != null ? saleRoom.getBalcony() : "Не указано");
        property_wc.setText(saleRoom.getWc() != null ? saleRoom.getWc() : "Не указано");
        if (saleRoom.getType() != null || saleRoom.getYearOfConstruction() != null) {
            property_type.setText(saleRoom.getType() != null ? saleRoom.getType() : "Не указано");
            property_year_of_construction.setText(saleRoom.getYearOfConstruction() != null ? saleRoom.getYearOfConstruction().toString() : "Не указано");
        } else {
            property_house_container.setVisibility(View.GONE);
        }
        if (saleRoom.getAdditionally() != null || saleRoom.getNotes() != null) {
            if (saleRoom.getAdditionally() != null) property_additionally.setText(saleRoom.getAdditionally()); else property_additionally.setVisibility(View.GONE);
            if (saleRoom.getNotes() != null) property_notes.setText(saleRoom.getNotes()); else property_notes.setVisibility(View.GONE);
            if (saleRoom.getAdditionally() != null && saleRoom.getNotes() != null) seller_separator.setVisibility(View.VISIBLE);
            else seller_separator.setVisibility(View.GONE);
        } else {
            property_seller_info_container.setVisibility(View.GONE);
        }
        property_sales_terms.setText(saleRoom.getSalesTerms() != null ? saleRoom.getSalesTerms() : "Не указано");
        property_own.setText(saleRoom.getOwn() != null ? saleRoom.getOwn() : "Не указано");
        property_price.setText(saleRoom.getPrice() != null ? saleRoom.getPrice() + " $" : "Не указано");
        property_psm.setText(saleRoom.getPsm() != null ? saleRoom.getPsm() + " $" : "Не указано");
        property_site.setText(saleRoom.getSite() != null ? saleRoom.getSite() : "Не указано");

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
        return saleRoomPresenter;
    }
}
