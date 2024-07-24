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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.ToggleButton;
import com.google.gson.JsonObject;
import com.oxionaz.belarussian_property.R;
import com.oxionaz.belarussian_property.model.DataManager;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleHouses;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.other.Const;
import com.oxionaz.belarussian_property.view.adapters.AutoCompleteAdapter;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;

@EActivity(R.layout.activity_sale_houses_search)
public class SaleHousesSearchActivity extends AppCompatActivity {

    @Inject
    DataManager dataManager;

    @ViewById
    Toolbar toolbar;

    @ViewById
    AutoCompleteTextView region, city, district, street;

    @ViewById
    Switch heating, electricity, gas, water, sewer, agent, thumb;

    @ViewById
    Spinner wall, roof;

    @ViewById
    ToggleButton view_1, view_2, view_3;

    @ViewById
    EditText levels_from, levels_to, square_from, square_to, square_useful_from,
            square_useful_to, kitchen_from, kitchen_to, land_area_from, land_area_to,
            year_of_construction_from, year_of_construction_to,
            price_from, price_to, modified_date_from, modified_date_to;

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

        ArrayAdapter<String> wall_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Любой материал стен", "белый кирпич", "блочный", "блок газосиликатный",
                        "брус клееный", "брус оцилиндрованный", "брус профилированный"});
        wall_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wall.setAdapter(wall_adapter);

        ArrayAdapter<String> roof_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Любой материал крыши", "битумная черепица", "гидроизоляция", "доски",
                        "евро-шифер", "жесть", "металл", "рубероид", "черепица", "шифер",
                        "металло-черепица", "мягкая черепица", "природные материалы"});
        roof_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roof.setAdapter(roof_adapter);
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
        if (view_1.isChecked() || view_2.isChecked() || view_3.isChecked()) {
            List<String> views = new ArrayList<>();
            if (view_1.isChecked()) views.add("дача");
            if (view_2.isChecked()) views.add("коттедж");
            if (view_3.isChecked()) views.add("таунхаус");
            String first = views.get(0);
            StringBuilder builder = new StringBuilder();
            for (String view:views) {
                if (!view.equals(first)){
                    builder.append("\",\"");
                }
                builder.append(view);
            }
            parameters.addProperty("view", builder.toString());
        }
        parameters.addProperty("levels_from", String.valueOf(levels_from.getText()));
        parameters.addProperty("levels_to", String.valueOf(levels_to.getText()));
        parameters.addProperty("year_of_construction_from", String.valueOf(year_of_construction_from.getText()));
        parameters.addProperty("year_of_construction_to", String.valueOf(year_of_construction_to.getText()));
        parameters.addProperty("wall", wall.getSelectedItemId() != 0 ? wall.getSelectedItem().toString() : null);
        parameters.addProperty("roof", roof.getSelectedItemId() != 0 ? roof.getSelectedItem().toString() : null);
        parameters.addProperty("land_area_from", String.valueOf(land_area_from.getText()));
        parameters.addProperty("land_area_to", String.valueOf(land_area_to.getText()));
        parameters.addProperty("square_from", String.valueOf(square_from.getText()));
        parameters.addProperty("square_to", String.valueOf(square_to.getText()));
        parameters.addProperty("square_useful_from", String.valueOf(square_useful_from.getText()));
        parameters.addProperty("square_useful_to", String.valueOf(square_useful_to.getText()));
        parameters.addProperty("kitchen_from", String.valueOf(kitchen_from.getText()));
        parameters.addProperty("kitchen_to", String.valueOf(kitchen_to.getText()));
        parameters.addProperty("heating", heating.isChecked() ? true : null);
        parameters.addProperty("electricity", electricity.isChecked() ? true : null);
        parameters.addProperty("gas", gas.isChecked() ? true : null);
        parameters.addProperty("water", water.isChecked() ? true : null);
        parameters.addProperty("sewer", sewer.isChecked() ? true : null);
        parameters.addProperty("price_from", String.valueOf(price_from.getText()));
        parameters.addProperty("price_to", String.valueOf(price_to.getText()));
        parameters.addProperty("agent", agent.isChecked() ? false : null);
        parameters.addProperty("thumb", thumb.isChecked() ? true : null);
        parameters.addProperty("modified_date_from", String.valueOf(modified_date_from.getText()));
        parameters.addProperty("modified_date_to", String.valueOf(modified_date_to.getText()));

        dataManager.clearPropertyTable("sale_houses", SaleHouses.class);
        dataManager.saveParameters("sale_houses", parameters);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @OptionsItem(android.R.id.home)
    void back(){
        onBackPressed();
    }
}
