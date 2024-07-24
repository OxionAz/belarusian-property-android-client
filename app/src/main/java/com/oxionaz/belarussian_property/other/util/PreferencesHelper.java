package com.oxionaz.belarussian_property.other.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.oxionaz.belarussian_property.other.App;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class PreferencesHelper {

    @Inject
    Context context;

    public PreferencesHelper(){
        App.getAppComponent().inject(this);
    }

    public void setFragmentPreference(String fragment, String preference, boolean value){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(String.format("%s_%s", fragment, preference), value);
        editor.apply();
    }

    public boolean getFragmentPreference(String fragment, String preference){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getBoolean(String.format("%s_%s", fragment, preference), false);
    }

    public void setExchangeRate(String name, float value){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat(name, value);
        editor.apply();
    }

    public float getExchangeRate(String name){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getFloat(name, (float) 1.87);
    }

    public void setRate(String value){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("rate", value);
        editor.apply();
    }

    public String getRate(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString("rate", "USD");
    }

    public void setPreference(String name, String value){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(name, value);
        editor.apply();
    }

    public String getPreference(String name){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString(name, "0");
    }

}
