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
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.SaleFlatDTO;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.presenter.BasePropertyPresenter;
import com.oxionaz.belarussian_property.presenter.SaleFlatPresenter;
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

@EActivity(R.layout.activity_sale_flat)
@OptionsMenu(R.menu.property_info_menu)
public class SaleFlatActivity extends BasePropertyActivity implements OnMapReadyCallback {

    private Bundle state;
    private PropertyItem propertyItem;
    private SaleFlatDTO saleFlatDTO;
    private boolean favorite;

    @Inject
    SaleFlatPresenter saleFlatPresenter;

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
        saleFlatPresenter.onCreate(this);
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
        saleFlatPresenter.downloadPropertyInfo(propertyItem.getId(),
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
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Site URL");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, saleFlatDTO.getUrl());
        startActivity(Intent.createChooser(sharingIntent, "Поделиться"));
    }

    @OptionsItem(R.id.option_favorite)
    void favorite(){
        if (favorite) {
            favorite = false;
            option_favorite.setIcon(R.drawable.ic_bookmark_border_black_24dp);
            saleFlatPresenter.deleteFavorite(propertyItem);
        } else {
            favorite = true;
            option_favorite.setIcon(R.drawable.ic_bookmark_black_24dp);
            saleFlatPresenter.saveFavorite(propertyItem);
        }
    }

    @Click(R.id.download_button)
    void downloadAgain(){
        saleFlatPresenter.downloadPropertyInfo(propertyItem.getId(),
                propertyItem.getPrice() != null ? propertyItem.getPrice().toString() : "NULL");
    }

    @Click(R.id.phone_button)
    void showPhones(View view){
       if (saleFlatDTO.getNumbers() != null) {
           PopupMenu phones = new PopupMenu(this, view);
           for (String number : saleFlatDTO.getNumbers()){
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
        Uri uriUrl = Uri.parse(saleFlatDTO.getUrl());
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    @OptionsItem(android.R.id.home)
    void back(){
        onBackPressed();
    }

    @Override
    public <P> void showPropertyInfo(P item) {
        SaleFlatDTO saleFlat = (SaleFlatDTO) item;
        this.saleFlatDTO = saleFlat;

        if (saleFlat.getPhotos() != null) {
            ImageAdapter imageAdapter = new ImageAdapter(saleFlat.getPhotos(), id ->
                    ZGallery.with(this, (ArrayList<String>) saleFlat.getPhotos())
                            .setToolbarTitleColor(ZColor.WHITE) // toolbar title color
                            .setGalleryBackgroundColor(ZColor.BLACK) // activity background color
                            .setToolbarColorResId(R.color.colorPrimaryDark) // toolbar color
                            .setTitle(String.valueOf(saleFlat.getPhotos().size()))
                            .setSelectedImgPosition(id)
                            .show());
            image_recycler_view.setAdapter(imageAdapter);
            property_photo_container.setVisibility(View.VISIBLE);
        }

        property_date.setText(propertyItem.getDate());
        property_views.setText(String.valueOf(saleFlat.getViews()));
        property_cost.setText(propertyItem.getCost());
        property_info.setText(propertyItem.getInfo());
        property_address.setText(propertyItem.getAddress());
        property_update_date.setText(propertyItem.getDate());
        property_agent.setText(saleFlat.getAgent() != null ? saleFlat.getAgent() : "Не указано");
        property_contact.setText(saleFlat.getContact() != null ? saleFlat.getContact() : "Не указано");
        property_email.setText(saleFlat.getEmail() != null ? saleFlat.getEmail() : "Не указано");
        property_region.setText(saleFlat.getRegion() != null ? saleFlat.getRegion() : "Не указано");
        property_direction.setText(saleFlat.getDirection() != null ? saleFlat.getDirection() : "Не указано");
        property_city.setText(saleFlat.getCity() != null ? saleFlat.getCity() : "Не указано");
        property_district.setText(saleFlat.getDistrict() != null ? saleFlat.getDistrict() : "Не указано");
        property_street.setText(saleFlat.getStreet() != null ? saleFlat.getStreet() : "Не указано");
        property_house.setText(saleFlat.getCorp());
        if (saleFlat.getMetro() != null) {
            property_station.setText(saleFlat.getMetro() != null ? saleFlat.getMetro() : "Не указано");
            property_metro_time.setText(saleFlat.getMetroTime() != null ? saleFlat.getMetroTime() : "Не указано");
        } else {
            property_metro_container.setVisibility(View.GONE);
        }
        property_floor.setText(saleFlat.getFloor() != null ?
                (saleFlat.getTotalFloors() != null ? saleFlat.getFloor() + " из " + saleFlat.getTotalFloors() : saleFlat.getFloor().toString())
                : "Не указано");
        property_rooms.setText(saleFlat.getRooms() != null ? saleFlat.getRooms() : "Не указано");
        property_square.setText(saleFlat.getSquare() != null ? saleFlat.getSquare() + " м²" : "Не указано");
        property_square_useful.setText(saleFlat.getSquareUseful() != null ? saleFlat.getSquareUseful() + " м²" : "Не указано");
        property_kitchen.setText(saleFlat.getKitchen() != null ? saleFlat.getKitchen() + " м²" : "Не указано");
        property_ceiling_height.setText(saleFlat.getCeilingHeight() != null ? saleFlat.getCeilingHeight() + " м" : "Не указано");
        property_plan.setText(saleFlat.getPlan() != null ? saleFlat.getPlan() : "Не указано");
        property_repair.setText(saleFlat.getRepair() != null ? saleFlat.getRepair() : "Не указано");
        property_floors.setText(saleFlat.getFloors() != null ? saleFlat.getFloors() : "Не указано");
        property_balcony.setText(saleFlat.getBalcony() != null ? saleFlat.getBalcony() : "Не указано");
        property_wc.setText(saleFlat.getWc() != null ? saleFlat.getWc() : "Не указано");
        if (saleFlat.getType() != null || saleFlat.getYearOfConstruction() != null) {
            property_type.setText(saleFlat.getType() != null ? saleFlat.getType() : "Не указано");
            property_year_of_construction.setText(saleFlat.getYearOfConstruction() != null ? saleFlat.getYearOfConstruction().toString() : "Не указано");
        } else {
            property_house_container.setVisibility(View.GONE);
        }
        if (saleFlat.getAdditionally() != null || saleFlat.getNotes() != null) {
            if (saleFlat.getAdditionally() != null) property_additionally.setText(saleFlat.getAdditionally()); else property_additionally.setVisibility(View.GONE);
            if (saleFlat.getNotes() != null) property_notes.setText(saleFlat.getNotes()); else property_notes.setVisibility(View.GONE);
            if (saleFlat.getAdditionally() != null && saleFlat.getNotes() != null) seller_separator.setVisibility(View.VISIBLE);
            else seller_separator.setVisibility(View.GONE);
        } else {
            property_seller_info_container.setVisibility(View.GONE);
        }
        property_sales_terms.setText(saleFlat.getSalesTerms() != null ? saleFlat.getSalesTerms() : "Не указано");
        property_own.setText(saleFlat.getOwn() != null ? saleFlat.getOwn() : "Не указано");
        property_price.setText(saleFlat.getPrice() != null ? saleFlat.getPrice() + " $" : "Не указано");
        property_psm.setText(saleFlat.getPsm() != null ? saleFlat.getPsm() + " $" : "Не указано");
        property_site.setText(saleFlat.getSite() != null ? saleFlat.getSite() : "Не указано");

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
        return saleFlatPresenter;
    }
}
