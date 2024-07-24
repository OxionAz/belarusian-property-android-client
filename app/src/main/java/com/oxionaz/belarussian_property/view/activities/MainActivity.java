package com.oxionaz.belarussian_property.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.oxionaz.belarussian_property.R;
import com.oxionaz.belarussian_property.model.DataManager;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.other.util.PreferencesHelper;
import com.oxionaz.belarussian_property.view.fragments.FavoritesFragment_;
import com.oxionaz.belarussian_property.view.fragments.PredictionRentFlatsFragment_;
import com.oxionaz.belarussian_property.view.fragments.PredictionSaleFlatsFragment_;
import com.oxionaz.belarussian_property.view.fragments.RentFlatsFragment_;
import com.oxionaz.belarussian_property.view.fragments.RentHousesFragment_;
import com.oxionaz.belarussian_property.view.fragments.RentRoomsFragment_;
import com.oxionaz.belarussian_property.view.fragments.SaleAreasFragment_;
import com.oxionaz.belarussian_property.view.fragments.SaleFlatsFragment_;
import com.oxionaz.belarussian_property.view.fragments.SaleHousesFragment_;
import com.oxionaz.belarussian_property.view.fragments.SaleRoomsFragment_;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import javax.inject.Inject;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainActivityView {

    FragmentManager fragmentManager;

    @Inject
    DataManager dataManager;

    @Inject
    PreferencesHelper preferencesHelper;

    @ViewById
    Toolbar toolbar;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawer;

    @ViewById(R.id.nav_view)
    NavigationView navigationView;

    @AfterViews
    void ready(){
        setToolbarAndDrawer();
        if (!preferencesHelper.getPreference("user_login").equals("0")) {
            TextView user = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user);
            user.setText(preferencesHelper.getPreference("user_login"));
            user.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().replace(R.id.main_frame, new SaleFlatsFragment_()).commit();
        }
    }

    private void setToolbarAndDrawer(){
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drawer_sale_analyses:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new PredictionSaleFlatsFragment_()).commit();
                break;
            case R.id.drawer_rent_analyses:
                fragmentManager.beginTransaction().replace(R.id.main_frame, new PredictionRentFlatsFragment_()).commit();
                break;
            case R.id.drawer_sale_flats:
                new Handler().postDelayed(() -> fragmentManager.beginTransaction().replace(R.id.main_frame, new SaleFlatsFragment_()).commit(), 400);
                break;
            case R.id.drawer_sale_rooms:
                new Handler().postDelayed(() -> fragmentManager.beginTransaction().replace(R.id.main_frame, new SaleRoomsFragment_()).commit(), 400);
                break;
            case R.id.drawer_sale_houses:
                new Handler().postDelayed(() -> fragmentManager.beginTransaction().replace(R.id.main_frame, new SaleHousesFragment_()).commit(), 400);
                break;
            case R.id.drawer_sale_areas:
                new Handler().postDelayed(() -> fragmentManager.beginTransaction().replace(R.id.main_frame, new SaleAreasFragment_()).commit(), 400);
                break;
            case R.id.drawer_rent_flats:
                new Handler().postDelayed(() -> fragmentManager.beginTransaction().replace(R.id.main_frame, new RentFlatsFragment_()).commit(), 400);
                break;
            case R.id.drawer_rent_rooms:
                new Handler().postDelayed(() -> fragmentManager.beginTransaction().replace(R.id.main_frame, new RentRoomsFragment_()).commit(), 400);
                break;
            case R.id.drawer_rent_houses:
                new Handler().postDelayed(() -> fragmentManager.beginTransaction().replace(R.id.main_frame, new RentHousesFragment_()).commit(), 400);
                break;
            case R.id.drawer_bookmarks:
                new Handler().postDelayed(() -> fragmentManager.beginTransaction().replace(R.id.main_frame, new FavoritesFragment_()).commit(), 400);
                break;
            case R.id.drawer_exit:
                dataManager.postFavorites();
                preferencesHelper.setPreference("user_id", "0");
                preferencesHelper.setPreference("user_login", "0");
                preferencesHelper.setPreference("user_email", "0");
                preferencesHelper.setPreference("user_admin", "0");
                preferencesHelper.setPreference("login", "0");
                dataManager.clearAllFavorites();
                dataManager.clearAllParameters();
                dataManager.clearAllProperties();
                startActivity(new Intent(this, UserActivity_.class));
                finish();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1: fragmentManager.beginTransaction().replace(R.id.main_frame, new SaleFlatsFragment_()).commit();
                    break;
                case 2: fragmentManager.beginTransaction().replace(R.id.main_frame, new SaleRoomsFragment_()).commit();
                    break;
                case 3: fragmentManager.beginTransaction().replace(R.id.main_frame, new SaleHousesFragment_()).commit();
                    break;
                case 4: fragmentManager.beginTransaction().replace(R.id.main_frame, new SaleAreasFragment_()).commit();
                    break;
                case 5: fragmentManager.beginTransaction().replace(R.id.main_frame, new RentFlatsFragment_()).commit();
                    break;
                case 6: fragmentManager.beginTransaction().replace(R.id.main_frame, new RentRoomsFragment_()).commit();
                    break;
                case 7: fragmentManager.beginTransaction().replace(R.id.main_frame, new RentHousesFragment_()).commit();
                    break;
            }
        }
    }

    @Override
    public void showPropertyInfo(String quantity, String median) {
        toolbar.setTitle(String.format("%s элементов", quantity));
        toolbar.setSubtitle(String.format("Медиана: %s $", median));
        new Handler().postDelayed(() -> toolbar.setSubtitle(null), 5000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dataManager.clearAllProperties();
    }
}