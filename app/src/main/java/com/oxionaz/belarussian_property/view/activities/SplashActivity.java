package com.oxionaz.belarussian_property.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.oxionaz.belarussian_property.R;
import com.oxionaz.belarussian_property.model.DataManager;
import com.oxionaz.belarussian_property.model.source.rest.dto.ExchangeRatesDTO;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.other.util.PreferencesHelper;
import org.androidannotations.annotations.EActivity;
import javax.inject.Inject;

import rx.Subscriber;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends Activity {

    @Inject
    PreferencesHelper preferencesHelper;

    @Inject
    DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);

        dataManager.downloadExchangeRate("USD")
                .subscribe(new Subscriber<ExchangeRatesDTO>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        unsubscribe();
                    }

                    @Override
                    public void onNext(ExchangeRatesDTO exchangeRatesDTO) {
                        unsubscribe();
                        preferencesHelper.setExchangeRate("USD", exchangeRatesDTO.getCurOfficialRate());
                        Log.e("RATE ", exchangeRatesDTO.getCurOfficialRate().toString());
                    }
                });

        new Handler().postDelayed(() -> {
            if (preferencesHelper.getPreference("login").equals("1")){
                startActivity(new Intent(this, MainActivity_.class));
                finish();
            } else {
                startActivity(new Intent(this, UserActivity_.class));
                finish();
            }
        }, 2000);
    }
}
