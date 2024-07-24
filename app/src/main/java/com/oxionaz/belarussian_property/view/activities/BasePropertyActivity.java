package com.oxionaz.belarussian_property.view.activities;

import android.support.v7.app.AppCompatActivity;
import com.oxionaz.belarussian_property.presenter.BasePropertyPresenter;

public abstract class BasePropertyActivity extends AppCompatActivity implements PropertyActivityView {

    // Clear all subscriptions
    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

    // Get BasePropertyPresenter for call method
    protected abstract BasePropertyPresenter getPresenter();

}
