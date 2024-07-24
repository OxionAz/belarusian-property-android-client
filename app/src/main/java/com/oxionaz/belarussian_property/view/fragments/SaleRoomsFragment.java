package com.oxionaz.belarussian_property.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import com.oxionaz.belarussian_property.R;
import com.oxionaz.belarussian_property.model.source.db.models.InfoTable;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.other.util.PreferencesHelper;
import com.oxionaz.belarussian_property.presenter.BasePropertyPresenter;
import com.oxionaz.belarussian_property.presenter.SaleRoomsPresenter;
import com.oxionaz.belarussian_property.presenter.mapping.PropertyItem;
import com.oxionaz.belarussian_property.view.activities.MainActivityView;
import com.oxionaz.belarussian_property.view.activities.SaleFlatActivity_;
import com.oxionaz.belarussian_property.view.activities.SaleFlatsSearchActivity_;
import com.oxionaz.belarussian_property.view.activities.SaleRoomActivity;
import com.oxionaz.belarussian_property.view.activities.SaleRoomActivity_;
import com.oxionaz.belarussian_property.view.activities.SaleRoomsSearchActivity_;
import com.oxionaz.belarussian_property.view.adapters.FavoriteData;
import com.oxionaz.belarussian_property.view.adapters.PropertyAdapter;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;
import java.util.List;
import javax.inject.Inject;

@EFragment(R.layout.fragment_property)
@OptionsMenu(R.menu.property_menu)
public class SaleRoomsFragment extends BasePropertyFragment {

    private boolean loadable = true;
    private LinearLayoutManager linearLayoutManager;
    private PropertyAdapter propertyAdapter;
    private MainActivityView mainActivityView;

    @Inject
    SaleRoomsPresenter saleRoomsPresenter;

    @Inject
    PreferencesHelper preferencesHelper;

    @ViewById
    SwipeRefreshLayout swipe_refresh;

    @ViewById
    RecyclerView property_recycler_view;

    @ViewById
    ProgressBar progress_bar;

    @OptionsMenuItem
    MenuItem option_rate, option_sort_default, option_sort_date_asc, option_sort_price_asc,
            option_sort_date_desc, option_sort_price_desc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        saleRoomsPresenter.onCreate(this);
        mainActivityView = (MainActivityView) getActivity();
    }

    @AfterViews
    void ready(){
        getActivity().setTitle("Комнаты");

        property_recycler_view.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        property_recycler_view.setLayoutManager(linearLayoutManager);
        propertyAdapter = new PropertyAdapter(new PropertyAdapter.PropertyViewHolder.ClickListener() {
            @Override
            public void OnItemClicked(boolean favorite, int position) {
                showPropertyInfo(propertyAdapter.getItemByPosition(position), favorite);
            }

            @Override
            public void OnFavoriteButtonClicked(Integer favorite, int position) {
                if (favorite != null) {
                    saleRoomsPresenter.deleteFavorite(propertyAdapter.getFavoriteByPosition(favorite));
                    propertyAdapter.deleteFavorite(favorite);
                } else {
                    saleRoomsPresenter.saveFavorite(propertyAdapter.getItemByPosition(position));
                    propertyAdapter.addFavorite(position);
                }
            }
        });
        property_recycler_view.setAdapter(propertyAdapter);
        property_recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 || dy < 0)
                {
                    int pastVisiblesItems, visibleItemCount, totalItemCount;
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (loadable)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loadable = false;
                            propertyAdapter.addLoadingFooter();
                            saleRoomsPresenter.nextLoad(true);
                        }
                    }
                }
            }
        });

        swipe_refresh.setOnRefreshListener(() -> saleRoomsPresenter.updateList(false));
        saleRoomsPresenter.firstLoad(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (preferencesHelper.getRate().equals("USD")){
            option_rate.setTitle("USD");
        } else {
            option_rate.setTitle("BYN");
        }
        if (preferencesHelper.getFragmentPreference("sale_rooms", "date_asc")) {
            option_sort_date_asc.setChecked(true);
        } else if (preferencesHelper.getFragmentPreference("sale_rooms", "date_desc")) {
            option_sort_date_desc.setChecked(true);
        } else if (preferencesHelper.getFragmentPreference("sale_rooms", "price_asc")) {
            option_sort_price_asc.setChecked(true);
        } else if (preferencesHelper.getFragmentPreference("sale_rooms", "price_desc")) {
            option_sort_price_desc.setChecked(true);
        } else {
            option_sort_default.setChecked(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        saleRoomsPresenter.getFavoritesFromCache();
    }

    @OptionsItem(R.id.option_rate)
    void rate(MenuItem item) {
        if (preferencesHelper.getRate().equals("USD")) {
            preferencesHelper.setRate("BYN");
            item.setTitle("BYN");
            propertyAdapter.updateRate();
        } else if (preferencesHelper.getRate().equals("BYN")) {
            preferencesHelper.setRate("USD");
            item.setTitle("USD");
            propertyAdapter.updateRate();
        }
    }

    @OptionsItem(R.id.option_sort_default)
    void sort_def() {
        option_sort_default.setChecked(true);
        preferencesHelper.setFragmentPreference("sale_rooms", "date_asc", false);
        preferencesHelper.setFragmentPreference("sale_rooms", "date_desc", false);
        preferencesHelper.setFragmentPreference("sale_rooms", "price_asc", false);
        preferencesHelper.setFragmentPreference("sale_rooms", "price_desc", false);
        saleRoomsPresenter.updateList(true);
    }

    @OptionsItem(R.id.option_sort_date_asc)
    void sort_date_asc() {
        option_sort_date_asc.setChecked(true);
        preferencesHelper.setFragmentPreference("sale_rooms", "date_asc", true);
        preferencesHelper.setFragmentPreference("sale_rooms", "date_desc", false);
        preferencesHelper.setFragmentPreference("sale_rooms", "price_asc", false);
        preferencesHelper.setFragmentPreference("sale_rooms", "price_desc", false);
        saleRoomsPresenter.updateList(true);
    }

    @OptionsItem(R.id.option_sort_date_desc)
    void sort_date_desc() {
        option_sort_date_desc.setChecked(true);
        preferencesHelper.setFragmentPreference("sale_rooms", "date_asc", false);
        preferencesHelper.setFragmentPreference("sale_rooms", "date_desc", true);
        preferencesHelper.setFragmentPreference("sale_rooms", "price_asc", false);
        preferencesHelper.setFragmentPreference("sale_rooms", "price_desc", false);
        saleRoomsPresenter.updateList(true);
    }

    @OptionsItem(R.id.option_sort_price_asc)
    void sort_price_asc() {
        option_sort_price_asc.setChecked(true);
        preferencesHelper.setFragmentPreference("sale_rooms", "date_asc", false);
        preferencesHelper.setFragmentPreference("sale_rooms", "date_desc", false);
        preferencesHelper.setFragmentPreference("sale_rooms", "price_asc", true);
        preferencesHelper.setFragmentPreference("sale_rooms", "price_desc", false);
        saleRoomsPresenter.updateList(true);
    }

    @OptionsItem(R.id.option_sort_price_desc)
    void sort_price_desc() {
        option_sort_price_desc.setChecked(true);
        preferencesHelper.setFragmentPreference("sale_rooms", "date_asc", false);
        preferencesHelper.setFragmentPreference("sale_rooms", "date_desc", false);
        preferencesHelper.setFragmentPreference("sale_rooms", "price_asc", false);
        preferencesHelper.setFragmentPreference("sale_rooms", "price_desc", true);
        saleRoomsPresenter.updateList(true);
    }

    @Click
    void fab(){
        startPropertySearch();
    }

    @Override
    public void showErrorMessage(String error) {
        Snackbar.make(property_recycler_view, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void disableFooter() {
        propertyAdapter.removeLoadingFooter();
    }

    @Override
    public void showPropertyList(List<PropertyItem> itemList, InfoTable info) {
        propertyAdapter.updateItems(itemList);
        mainActivityView.showPropertyInfo(info.getQuantity().toString(), info.getPriceMedian().toString());
    }

    @Override
    public void updatePropertyList(List<PropertyItem> itemList, boolean loading) {
        propertyAdapter.removeLoadingFooter();
        if (itemList != null) propertyAdapter.addItems(itemList);
        loadable = loading;
    }

    @Override
    public void updateFavoriteList(List<FavoriteData> favorites) {
        propertyAdapter.setFavorites(favorites);
    }

    @Override
    public void showPropertyInfo(PropertyItem item, boolean favorite) {
        Intent intent = new Intent(getContext(), SaleRoomActivity_.class);
        intent.putExtra("property", item);
        intent.putExtra("favorite", favorite);
        getActivity().startActivity(intent);
    }

    @Override
    public void startPropertySearch() {
        Intent intent = new Intent(getContext(), SaleRoomsSearchActivity_.class);
        getActivity().startActivityForResult(intent, 2);
    }

    @Override
    public void showLoading(boolean value) {
        progress_bar.setVisibility(value ? View.VISIBLE : View.GONE);
        property_recycler_view.setVisibility(!value ? View.VISIBLE : View.GONE);
        if (!value) swipe_refresh.setRefreshing(false);
    }

    @Override
    protected BasePropertyPresenter getPresenter() {
        return saleRoomsPresenter;
    }
}
