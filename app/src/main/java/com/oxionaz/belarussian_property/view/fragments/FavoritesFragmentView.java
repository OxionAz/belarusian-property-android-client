package com.oxionaz.belarussian_property.view.fragments;

import com.oxionaz.belarussian_property.model.source.db.models.cache.FavoriteTable;
import com.oxionaz.belarussian_property.presenter.mapping.PropertyItem;
import com.oxionaz.belarussian_property.view.View;
import java.util.List;

public interface FavoritesFragmentView extends View {

   void showFavoritesList(String property, List<FavoriteTable> favorites);
   void showPropertyInfo(String property, FavoriteTable item, boolean favorite);
}
