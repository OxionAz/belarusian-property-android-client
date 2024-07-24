package com.oxionaz.belarussian_property.view.fragments;

import android.support.v4.app.Fragment;
import com.oxionaz.belarussian_property.presenter.BasePropertyPresenter;

public abstract class BasePropertyFragment extends Fragment implements PropertyFragmentView {

    // Clear all subscriptions if fragment stop
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
