package com.oxionaz.belarussian_property.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.google.gson.JsonObject;
import com.oxionaz.belarussian_property.R;
import com.oxionaz.belarussian_property.model.source.rest.dto.UserDTO;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.other.util.PreferencesHelper;
import com.oxionaz.belarussian_property.other.util.TextInputCheck;
import com.oxionaz.belarussian_property.presenter.BasePropertyPresenter;
import com.oxionaz.belarussian_property.presenter.UserPresenter;
import com.oxionaz.belarussian_property.view.activities.UserActivityView;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import javax.inject.Inject;

@EFragment(R.layout.fragment_registration)
public class RegistrationFragment extends BaseUserFragment {

    private UserActivityView userActivityView;

    @Inject
    PreferencesHelper preferencesHelper;

    @Inject
    UserPresenter userPresenter;

    @ViewById
    LinearLayout container;

    @ViewById
    EditText login, email, password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        userPresenter.onCreate(this);
        userActivityView = (UserActivityView) getActivity();
    }

    @Click(R.id.reg_button)
    void logUp(){
        hideKeyboard();
        if (TextInputCheck.checkFields(login, email, password)){
            JsonObject parameters = new JsonObject();
            parameters.addProperty("login", String.valueOf(login.getText()).toLowerCase());
            parameters.addProperty("email", String.valueOf(email.getText()));
            parameters.addProperty("password", String.valueOf(password.getText()));
            userPresenter.logUp(parameters, login, email);
        }
    }

    @Override
    public void showErrorMessage(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showMainActivity(UserDTO data) {
        preferencesHelper.setPreference("user_id", String.valueOf(data.getId()));
        preferencesHelper.setPreference("user_login", data.getLogin());
        preferencesHelper.setPreference("user_email", data.getEmail());
        preferencesHelper.setPreference("user_admin", String.valueOf(data.getAdmin()));
        preferencesHelper.setPreference("login", "1");
        userActivityView.startMainActivity();
    }

    @Override
    protected BasePropertyPresenter getPresenter() {
        return userPresenter;
    }
}
