package com.oxionaz.belarussian_property.view.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import com.oxionaz.belarussian_property.presenter.BasePropertyPresenter;

public abstract class BaseUserFragment extends Fragment implements UserFragmentView {

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

    protected void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
