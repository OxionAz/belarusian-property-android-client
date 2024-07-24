package com.oxionaz.belarussian_property.view.fragments;

import com.oxionaz.belarussian_property.model.source.db.models.InfoTable;
import com.oxionaz.belarussian_property.presenter.mapping.PropertyItem;
import com.oxionaz.belarussian_property.view.View;
import com.oxionaz.belarussian_property.view.adapters.FavoriteData;
import java.util.List;

public interface PropertyFragmentView extends View {

    void showPropertyList(List<PropertyItem> itemList, InfoTable info);
    void updatePropertyList(List<PropertyItem> itemList, boolean loading);
    void updateFavoriteList(List<FavoriteData> favorites);
    void showPropertyInfo(PropertyItem item, boolean favorite);
    void startPropertySearch();

    void showLoading(boolean value);
    void showErrorMessage(String error);
    void disableFooter();
}
