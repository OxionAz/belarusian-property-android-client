package com.oxionaz.belarussian_property.view.activities;

public interface PropertyActivityView {

    <P> void showPropertyInfo(P item);

    void showLoading();
    void hideViews();
    void showNotFoundMessage(String message);
    void showErrorMessage(String message);
}
