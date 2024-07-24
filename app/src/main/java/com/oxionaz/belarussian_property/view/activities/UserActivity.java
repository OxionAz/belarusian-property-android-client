package com.oxionaz.belarussian_property.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.oxionaz.belarussian_property.R;
import com.oxionaz.belarussian_property.view.fragments.LoginFragment_;
import com.oxionaz.belarussian_property.view.fragments.RegistrationFragment_;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_user)
public class UserActivity extends AppCompatActivity implements UserActivityView {

    private static final String TAG = "REGISTRATION";
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) replaceFragment(new LoginFragment_(), false);
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this, MainActivity_.class));
        finish();
    }

    @Override
    public void startRegistrationFragment() {
        replaceFragment(new RegistrationFragment_(), true);
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_frame, fragment, TAG);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }
}