package com.oxionaz.belarussian_property.view.fragments;

public interface PredictionFragmentView {

    void showPrediction(Float prediction);

    void showLoading();
    void showErrorMessage(String message);
}
