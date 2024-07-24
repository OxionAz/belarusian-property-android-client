package com.oxionaz.belarussian_property.view.fragments;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.oxionaz.belarussian_property.R;
import com.oxionaz.belarussian_property.model.source.db.Database;
import com.oxionaz.belarussian_property.model.source.rest.Rest;
import com.oxionaz.belarussian_property.model.source.rest.RestService;
import com.oxionaz.belarussian_property.model.source.db.models.cache.sale.SaleFlats;
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.SaleFlatDTO;
import com.oxionaz.belarussian_property.model.source.rest.dto.flats.SaleFlatsContainerDTO;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.other.Const;
import com.oxionaz.belarussian_property.other.util.NetworkConnectionUtil;
import com.oxionaz.belarussian_property.other.util.PreferencesHelper;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import retrofit2.HttpException;
import rx.Subscriber;
import rx.functions.Action1;

@EFragment(R.layout.fragment_flask)
public class QueryTestFragment extends Fragment {

  /*  @Inject
    Rest restService;

    @Inject
    Database database;

    @Inject
    PreferencesHelper preferencesHelper;

    @ViewById
    NestedScrollView main_layout;

    @ViewById
    TextView response;

    @AfterViews
    void ready(){
        App.getAppComponent().inject(this);
        getActivity().setTitle("Flask");
//        restService.downloadExchangeRate("USD").subscribe(exchangeRatesDTO ->
//                preferencesHelper.setExchangeRate("USD", exchangeRatesDTO.getCurOfficialRate()));
        Log.d("asd", preferencesHelper.getPreference("M") +
                preferencesHelper.getExchangeRate("USD") +
                preferencesHelper.getFragmentPreference("sale_flat", "sort"));

        database.getParameters("sale_flats").subscribe(new Subscriber<JsonObject>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("JSON ERROR ", e.getMessage());
            }

            @Override
            public void onNext(JsonObject jsonObject) {
               if (jsonObject == null)
                   Log.e("JSON NULL", "NULL");
                else
                   Log.e("JSON ", jsonObject.toString());
            }
        });
        database.getSaleFlats().subscribe(saleFlatsContainerDTO -> {
            if (saleFlatsContainerDTO.getSaleFlatses().size() == 0)
                Log.e("SaleFlats", "NULL");
            else
                Log.e("SaleFlats", "NOT NULL");
            if (saleFlatsContainerDTO.getInfoTable() == null)
                Log.e("SaleFlats INFO", "NULL");
            else
                Log.e("SaleFlats INFO", "NOT NULL");
        });
    }

    @Click
    void fab() {
        tryGetData();
    }

    private void tryGetData() {
        if (NetworkConnectionUtil.isNetworkConnected(getContext())){
            List<String> rooms = Arrays.asList("1", "2", "3");
            String first = rooms.get(0);
            StringBuilder builder = new StringBuilder();
            for (String room:rooms) {
                if (!room.equals(first)){
                    builder.append("\",\"");
                }
                builder.append(room);
            }
            List<String> type = Arrays.asList("монолит", "панельный");
            first = type.get(0);
            StringBuilder b = new StringBuilder();
            for (String t:type) {
                if (!t.equals(first)){
                    b.append("\",\"");
                }
                b.append(t);
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("city", "минск");
            jsonObject.addProperty("metro_near", true);
//            jsonObject.addProperty("rooms", builder.toString());
//            jsonObject.addProperty("view", b.toString());
            jsonObject.addProperty("gas", true);
            jsonObject.addProperty("thumb", true);
            restService.downloadFlat(Const.API_KEY, 331568, "124300")
                    .subscribe(new Subscriber<SaleFlatDTO>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    if (e instanceof HttpException) {
                        try {
                            response.setText(String.format("%s\n%s\n%s\n%s\n%s",
                                    e.getMessage(),
                                    ((HttpException) e).response().body(),
                                    ((HttpException) e).response().errorBody().source().readUtf8(),
                                    ((HttpException) e).code(),
                                    e.getClass()));
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        response.setText(e.getMessage());
                    }
                }

                @Override
                public void onNext(SaleFlatDTO data) {
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    response.setText(gson.toJson(data));
                    Snackbar.make(main_layout, "Данные загружены", Snackbar.LENGTH_SHORT).show();
                    database.getParameters("sale_flats").subscribe(object ->
                            Log.e("JSON ", gson.toJson(object)));
                }
            });
        } else {
            Snackbar.make(main_layout, "Отсутствует интернет подключение", Snackbar.LENGTH_SHORT).show();
        }
    }*/
}
