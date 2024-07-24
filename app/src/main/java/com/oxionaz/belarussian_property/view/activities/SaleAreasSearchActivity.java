package com.oxionaz.belarussian_property.view.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Switch;
import com.google.gson.JsonObject;
import com.oxionaz.belarussian_property.R;
import com.oxionaz.belarussian_property.model.DataManager;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleAreas;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.other.Const;
import com.oxionaz.belarussian_property.view.adapters.AutoCompleteAdapter;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;

@EActivity(R.layout.activity_sale_area_search)
public class SaleAreasSearchActivity extends AppCompatActivity {

    @Inject
    DataManager dataManager;

    @ViewById
    Toolbar toolbar;

    @ViewById
    AutoCompleteTextView region, city, district, street;

    @ViewById
    Switch electricity, gas, water, agent, thumb;

    @ViewById
    EditText land_area_from, land_area_to, price_from, price_to,
            modified_date_from, modified_date_to;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
    }

    @AfterViews
    void ready(){
        setActionBar();
        adjustViews();
    }

    private void setActionBar(){
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Параметры поиска");
    }

    private void adjustViews(){
        AutoCompleteAdapter region_adapter = new AutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line);
        region.setAdapter(region_adapter);
        region.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (region.isPerformingCompletion()) {
                    return;
                }
                if (s.length() < 2) {
                    return;
                }
                region_adapter.clear();

                JsonObject request = new JsonObject();
                request.addProperty("table", "sale_cottage");
                request.addProperty("column", "region");
                request.addProperty("name", s.toString());

                dataManager.downloadPropertyValues(Const.API_KEY, request)
                        .subscribe(new Subscriber<List<String>>() {
                            @Override
                            public void onCompleted() {}

                            @Override
                            public void onError(Throwable e) { unsubscribe(); }

                            @Override
                            public void onNext(List<String> strings) {
                                unsubscribe();
                                if (!strings.isEmpty()) {
                                    region_adapter.addAll(strings);
                                }
                            }
                        });
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        AutoCompleteAdapter city_adapter = new AutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line);
        city.setAdapter(city_adapter);
        city.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (city.isPerformingCompletion()) {
                    return;
                }
                if (s.length() < 2) {
                    return;
                }
                city_adapter.clear();

                JsonObject request = new JsonObject();
                request.addProperty("table", "sale_cottage");
                request.addProperty("column", "city");
                request.addProperty("name", s.toString());

                dataManager.downloadPropertyValues(Const.API_KEY, request)
                        .subscribe(new Subscriber<List<String>>() {
                            @Override
                            public void onCompleted() {}

                            @Override
                            public void onError(Throwable e) { unsubscribe(); }

                            @Override
                            public void onNext(List<String> strings) {
                                unsubscribe();
                                if (!strings.isEmpty()) {
                                    city_adapter.addAll(strings);
                                }
                            }
                        });
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        AutoCompleteAdapter district_adapter = new AutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line);
        district.setAdapter(district_adapter);
        district.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (district.isPerformingCompletion()) {
                    return;
                }
                if (s.length() < 2) {
                    return;
                }
                district_adapter.clear();

                JsonObject request = new JsonObject();
                request.addProperty("table", "sale_cottage");
                request.addProperty("column", "district");
                request.addProperty("name", s.toString());

                dataManager.downloadPropertyValues(Const.API_KEY, request)
                        .subscribe(new Subscriber<List<String>>() {
                            @Override
                            public void onCompleted() {}

                            @Override
                            public void onError(Throwable e) { unsubscribe(); }

                            @Override
                            public void onNext(List<String> strings) {
                                unsubscribe();
                                if (!strings.isEmpty()) {
                                    district_adapter.addAll(strings);
                                }
                            }
                        });
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        AutoCompleteAdapter street_adapter = new AutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line);
        street.setAdapter(street_adapter);
        street.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (street.isPerformingCompletion()) {
                    return;
                }
                if (s.length() < 2) {
                    return;
                }
                street_adapter.clear();

                JsonObject request = new JsonObject();
                request.addProperty("table", "sale_cottage");
                request.addProperty("column", "street");
                request.addProperty("name", s.toString());

                dataManager.downloadPropertyValues(Const.API_KEY, request)
                        .subscribe(new Subscriber<List<String>>() {
                            @Override
                            public void onCompleted() {}

                            @Override
                            public void onError(Throwable e) { unsubscribe(); }

                            @Override
                            public void onNext(List<String> strings) {
                                unsubscribe();
                                if (!strings.isEmpty()) {
                                    street_adapter.addAll(strings);
                                }
                            }
                        });
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Click(R.id.modified_date_from)
    void setDateFrom(EditText dateFrom) {
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, dayOfMonth) ->
                dateFrom.setText(String.format("%d-%02d-%02d", year, month + 1, dayOfMonth)),
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Click(R.id.modified_date_to)
    void setDateTo(EditText dateTo) {
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, dayOfMonth) ->
                dateTo.setText(String.format("%d-%02d-%02d", year, month + 1, dayOfMonth)),
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @Click(R.id.search_button)
    void search(){
        JsonObject parameters = new JsonObject();
        parameters.addProperty("region", String.valueOf(region.getText()));
        parameters.addProperty("city", String.valueOf(city.getText()));
        parameters.addProperty("district", String.valueOf(district.getText()));
        parameters.addProperty("street", String.valueOf(street.getText()));
        parameters.addProperty("land_area_from", String.valueOf(land_area_from.getText()));
        parameters.addProperty("land_area_to", String.valueOf(land_area_to.getText()));
        parameters.addProperty("electricity", electricity.isChecked() ? true : null);
        parameters.addProperty("gas", gas.isChecked() ? true : null);
        parameters.addProperty("water", water.isChecked() ? true : null);
        parameters.addProperty("price_from", String.valueOf(price_from.getText()));
        parameters.addProperty("price_to", String.valueOf(price_to.getText()));
        parameters.addProperty("agent", agent.isChecked() ? false : null);
        parameters.addProperty("thumb", thumb.isChecked() ? true : null);
        parameters.addProperty("modified_date_from", String.valueOf(modified_date_from.getText()));
        parameters.addProperty("modified_date_to", String.valueOf(modified_date_to.getText()));

        dataManager.clearPropertyTable("sale_areas", SaleAreas.class);
        dataManager.saveParameters("sale_areas", parameters);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @OptionsItem(android.R.id.home)
    void back(){
        onBackPressed();
    }
}
