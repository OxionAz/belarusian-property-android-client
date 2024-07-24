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
import com.oxionaz.belarussian_property.model.source.db.models.cache.rent.RentFlats;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleFlats;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.other.Const;
import com.oxionaz.belarussian_property.view.View;
import com.oxionaz.belarussian_property.view.adapters.AutoCompleteAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

@EActivity(R.layout.activity_rent_flats_search)
public class RentFlatsSearchActivity extends AppCompatActivity {

    @Inject
    DataManager dataManager;

    @ViewById
    Toolbar toolbar;

    @ViewById
    AutoCompleteTextView region, city, district, street, metro;

    @ViewById
    Switch metro_near, furniture, fridge, tv, washer, internet, floor_first, floor_last, prepayment, agent, thumb;

    @ViewById
    Spinner metro_branch, plan, repair, balcony, wc, beds, cooker, type;

    @ViewById
    ToggleButton rooms_1, rooms_2, rooms_3, rooms_4, term_1, term_2;

    @ViewById
    EditText ceiling_height_from, ceiling_height_to, square_from, square_to, square_useful_from,
            square_useful_to, kitchen_from, kitchen_to, floor_from, floor_to, total_floors_from,
            total_floors_to, year_of_construction_from, year_of_construction_to,
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
        metro_branch.setEnabled(false);

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
                request.addProperty("table_1", "rent_flat_day");
                request.addProperty("table_2", "rent_flat_long");
                request.addProperty("column", "region");
                request.addProperty("name", s.toString());

                dataManager.downloadPropertyMultiValues(Const.API_KEY, request)
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
                request.addProperty("table_1", "rent_flat_day");
                request.addProperty("table_2", "rent_flat_long");
                request.addProperty("column", "city");
                request.addProperty("name", s.toString());

                dataManager.downloadPropertyMultiValues(Const.API_KEY, request)
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
                request.addProperty("table_1", "rent_flat_day");
                request.addProperty("table_2", "rent_flat_long");
                request.addProperty("column", "district");
                request.addProperty("name", s.toString());

                dataManager.downloadPropertyMultiValues(Const.API_KEY, request)
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
                request.addProperty("table_1", "rent_flat_day");
                request.addProperty("table_2", "rent_flat_long");
                request.addProperty("column", "street");
                request.addProperty("name", s.toString());

                dataManager.downloadPropertyMultiValues(Const.API_KEY, request)
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

        AutoCompleteAdapter metro_adapter = new AutoCompleteAdapter(this, android.R.layout.simple_dropdown_item_1line);
        metro.setAdapter(metro_adapter);
        metro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (metro.isPerformingCompletion()) {
                    return;
                }
                if (s.length() < 2) {
                    return;
                }
                metro_adapter.clear();

                JsonObject request = new JsonObject();
                request.addProperty("table_1", "rent_flat_day");
                request.addProperty("table_2", "rent_flat_long");
                request.addProperty("column", "metro");
                request.addProperty("name", s.toString());

                dataManager.downloadPropertyMultiValues(Const.API_KEY, request)
                        .subscribe(new Subscriber<List<String>>() {
                            @Override
                            public void onCompleted() {}

                            @Override
                            public void onError(Throwable e) { unsubscribe(); }

                            @Override
                            public void onNext(List<String> strings) {
                                unsubscribe();
                                if (!strings.isEmpty()) {
                                    metro_adapter.addAll(strings);
                                }
                            }
                        });
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        ArrayAdapter<String> metro_branch_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Любая линия метро", "Заводская линия", "Московская линия"});
        metro_branch_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        metro_branch.setAdapter(metro_branch_adapter);

        ArrayAdapter<String> plan_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Любая планировка", "брежневка", "малосемейка", "новостройка",
                        "сталинка", "стандартный проект", "улучшеный проект", "хрущевка",
                        "чешский проект"});
        plan_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        plan.setAdapter(plan_adapter);

        ArrayAdapter<String> repair_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Любой ремонт", "без отделки", "строительная отделка", "евроремонт",
                        "отличный ремонт", "хороший ремонт", "нормальный ремонт",
                        "удовлетворительный ремонт", "плохое состояние", "аварийное состояние"});
        repair_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repair.setAdapter(repair_adapter);

        ArrayAdapter<String> balcony_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Любой балкон", "балкон", "лоджия", "терраса", "эркер", "нет балкона"});
        balcony_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        balcony.setAdapter(balcony_adapter);

        ArrayAdapter<String> wc_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Любой сан/узел", "раздельный", "совмещенный", "2 сан.узла",
                        "3 сан.узла", "4 сан.узла"});
        wc_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        wc.setAdapter(wc_adapter);

        ArrayAdapter<String> beds_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Любое количество спальных мест", "1 место", "2 местная кровать", "1+1",
                        "1+1+1", "1+1+2", "1+2", "1+2+2", "2+2", "2+2+2"});
        beds_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        beds.setAdapter(beds_adapter);

        ArrayAdapter<String> cooker_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Любая плита", "газовая", "электрическая"});
        cooker_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cooker.setAdapter(cooker_adapter);

        ArrayAdapter<String> type_adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Любой тип дома", "панельный", "кирпичный", "монолитный",
                        "блок-комнаты", "каркасно-блочный", "силикатные блоки", "бревенчатый"});
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(type_adapter);
    }

    @Click(R.id.metro_near)
    void metro(Switch view){
        metro_branch.setEnabled(view.isChecked());
        metro.setEnabled(view.isChecked());
    }

    @Click(R.id.term_1)
    void longTerm() {
        term_2.setChecked(false);
    }

    @Click(R.id.term_2)
    void shortTerm() {
        term_1.setChecked(false);
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
        parameters.addProperty("metro_near", metro_near.isChecked() ? true : null);
        if (metro_near.isChecked()) {
            parameters.addProperty("metro_branch", metro_branch.getSelectedItemId() != 0 ? metro_branch.getSelectedItem().toString() : null);
            parameters.addProperty("metro", String.valueOf(metro.getText()));
        }
        if (rooms_1.isChecked() || rooms_2.isChecked() || rooms_3.isChecked() || rooms_4.isChecked()) {
            List<String> rooms = new ArrayList<>();
            if (rooms_1.isChecked()) rooms.add("1");
            if (rooms_2.isChecked()) rooms.add("2");
            if (rooms_3.isChecked()) rooms.add("3");
            if (rooms_4.isChecked()) rooms.addAll(Arrays.asList("4","5","6","7","8","9","10"));
            String first = rooms.get(0);
            StringBuilder builder = new StringBuilder();
            for (String room:rooms) {
                if (!room.equals(first)){
                    builder.append("\",\"");
                }
                builder.append(room);
            }
            parameters.addProperty("rooms", builder.toString());
        }
        parameters.addProperty("ceiling_height_from", String.valueOf(ceiling_height_from.getText()));
        parameters.addProperty("ceiling_height_to", String.valueOf(ceiling_height_to.getText()));
        parameters.addProperty("plan", plan.getSelectedItemId() != 0 ? plan.getSelectedItem().toString() : null);
        parameters.addProperty("repair", repair.getSelectedItemId() != 0 ? repair.getSelectedItem().toString() : null);
        parameters.addProperty("balcony", balcony.getSelectedItemId() != 0 ? balcony.getSelectedItem().toString() : null);
        parameters.addProperty("wc", wc.getSelectedItemId() != 0 ? wc.getSelectedItem().toString() : null);
        parameters.addProperty("furniture", furniture.isChecked() ? true : null);
        parameters.addProperty("beds", beds.getSelectedItemId() != 0 ? beds.getSelectedItem().toString() : null);
        parameters.addProperty("cooker", cooker.getSelectedItemId() != 0 ? cooker.getSelectedItem().toString() : null);
        parameters.addProperty("fridge", fridge.isChecked() ? true : null);
        parameters.addProperty("tv", tv.isChecked() ? true : null);
        parameters.addProperty("washer", washer.isChecked() ? true : null);
        parameters.addProperty("internet", internet.isChecked() ? true : null);
        parameters.addProperty("square_from", String.valueOf(square_from.getText()));
        parameters.addProperty("square_to", String.valueOf(square_to.getText()));
        parameters.addProperty("square_useful_from", String.valueOf(square_useful_from.getText()));
        parameters.addProperty("square_useful_to", String.valueOf(square_useful_to.getText()));
        parameters.addProperty("kitchen_from", String.valueOf(kitchen_from.getText()));
        parameters.addProperty("kitchen_to", String.valueOf(kitchen_to.getText()));
        parameters.addProperty("floor_from", String.valueOf(floor_from.getText()));
        parameters.addProperty("floor_to", String.valueOf(floor_to.getText()));
        parameters.addProperty("total_floors_from", String.valueOf(total_floors_from.getText()));
        parameters.addProperty("total_floors_to", String.valueOf(total_floors_to.getText()));
        parameters.addProperty("floor_first", floor_first.isChecked() ? false : null);
        parameters.addProperty("floor_last", floor_last.isChecked() ? false : null);
        parameters.addProperty("year_of_construction_from", String.valueOf(year_of_construction_from.getText()));
        parameters.addProperty("year_of_construction_to", String.valueOf(year_of_construction_to.getText()));
        parameters.addProperty("type", type.getSelectedItemId() != 0 ? type.getSelectedItem().toString() : null);
        if (term_1.isChecked()) parameters.addProperty("term", true); else if (term_2.isChecked()) parameters.addProperty("term", false);
        parameters.addProperty("prepayment", prepayment.isChecked() ? true : null);
        parameters.addProperty("price_from", String.valueOf(price_from.getText()));
        parameters.addProperty("price_to", String.valueOf(price_to.getText()));
        parameters.addProperty("agent", agent.isChecked() ? false : null);
        parameters.addProperty("thumb", thumb.isChecked() ? true : null);
        parameters.addProperty("modified_date_from", String.valueOf(modified_date_from.getText()));
        parameters.addProperty("modified_date_to", String.valueOf(modified_date_to.getText()));

        dataManager.clearPropertyTable("rent_flats", RentFlats.class);
        dataManager.saveParameters("rent_flats", parameters);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @OptionsItem(android.R.id.home)
    void back(){
        onBackPressed();
    }
}
