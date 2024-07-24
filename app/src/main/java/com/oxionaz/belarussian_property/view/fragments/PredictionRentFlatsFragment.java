package com.oxionaz.belarussian_property.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.oxionaz.belarussian_property.R;
import com.oxionaz.belarussian_property.model.source.rest.Rest;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.other.Const;
import com.oxionaz.belarussian_property.presenter.BasePropertyPresenter;
import com.oxionaz.belarussian_property.presenter.PredictionRentFlatsPresenter;
import com.oxionaz.belarussian_property.presenter.PredictionSaleFlatsPresenter;
import com.oxionaz.belarussian_property.view.adapters.AutoCompleteAdapter;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

@EFragment(R.layout.fragment_rent_flat_prediction)
public class PredictionRentFlatsFragment extends BasePredictionFragment {

    @Inject
    Rest restService;

    @Inject
    PredictionRentFlatsPresenter predictionRentFlatsPresenter;

    @ViewById
    CoordinatorLayout main_layout;

    @ViewById
    CardView content;

    @ViewById
    ProgressBar progress_bar;

    @ViewById
    TextView message_text;

    @ViewById
    Button new_button;

    @ViewById
    AutoCompleteTextView city, district;

    @ViewById
    Spinner type;

    @ViewById
    Switch furniture;

    @ViewById
    EditText floor, total_floors, rooms, square;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        predictionRentFlatsPresenter.onCreate(this);
    }

    @AfterViews
    void ready(){
        adjustViews();
        getActivity().setTitle("Аренда квартир");
    }

    private void adjustViews(){
        AutoCompleteAdapter city_adapter = new AutoCompleteAdapter(getContext(), android.R.layout.simple_dropdown_item_1line);
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
                request.addProperty("table", "rent_flat_long");
                request.addProperty("column", "city");
                request.addProperty("name", s.toString());

                restService.downloadModelValues(Const.API_KEY, request)
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

        AutoCompleteAdapter district_adapter = new AutoCompleteAdapter(getContext(), android.R.layout.simple_dropdown_item_1line);
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
                request.addProperty("table", "rent_flat_long");
                request.addProperty("column", "district");
                request.addProperty("name", s.toString());

                restService.downloadModelValues(Const.API_KEY, request)
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

        ArrayAdapter<String> type_adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_dropdown_item,
                new String[]{"", "блок-комнаты", "каркасно-блочный", "кирпичный", "монолитно-каркасный",
                        "монолитный", "панельный"});
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(type_adapter);
    }

    @Click(R.id.search_button)
    void getPrediction(){
        JsonObject parameters = new JsonObject();
        parameters.addProperty("city", String.valueOf(city.getText()));
        parameters.addProperty("district", String.valueOf(district.getText()));
        parameters.addProperty("rooms", String.valueOf(rooms.getText()));
        parameters.addProperty("floor", String.valueOf(floor.getText()));
        parameters.addProperty("total_floors", String.valueOf(total_floors.getText()));
        parameters.addProperty("square", String.valueOf(square.getText()));
        parameters.addProperty("furniture", furniture.isChecked() ? 1 : 0);
        parameters.addProperty("type", String.valueOf(type.getSelectedItem()));
        predictionRentFlatsPresenter.gePrediction(parameters);
    }

    @Click(R.id.new_button)
    void newPrediction(){
        message_text.setVisibility(View.GONE);
        new_button.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPrediction(Float prediction) {
        progress_bar.setVisibility(View.GONE);
        content.setVisibility(View.GONE);
        new_button.setVisibility(View.VISIBLE);
        message_text.setVisibility(View.VISIBLE);
        message_text.setText(String.format("Результат: %s $", prediction));
    }

    @Override
    public void showLoading() {
        content.setVisibility(View.GONE);
        progress_bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String message) {
        progress_bar.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
        Snackbar.make(main_layout, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    protected BasePropertyPresenter getPresenter() {
        return predictionRentFlatsPresenter;
    }
}
