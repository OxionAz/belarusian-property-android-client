package com.oxionaz.belarussian_property.view.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oxionaz.belarussian_property.R;
import com.oxionaz.belarussian_property.model.source.db.models.cache.FavoriteTable;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.other.util.PreferencesHelper;
import com.oxionaz.belarussian_property.presenter.FavoritePresenter;
import com.oxionaz.belarussian_property.presenter.mapping.PropertyItem;
import com.oxionaz.belarussian_property.view.activities.RentFlatActivity_;
import com.oxionaz.belarussian_property.view.activities.RentHouseActivity_;
import com.oxionaz.belarussian_property.view.activities.RentRoomActivity_;
import com.oxionaz.belarussian_property.view.activities.SaleAreaActivity_;
import com.oxionaz.belarussian_property.view.activities.SaleFlatActivity_;
import com.oxionaz.belarussian_property.view.activities.SaleHouseActivity_;
import com.oxionaz.belarussian_property.view.activities.SaleRoomActivity_;
import com.oxionaz.belarussian_property.view.adapters.FavoriteAdapter;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.OptionsMenuItem;
import org.androidannotations.annotations.ViewById;
import java.util.List;
import javax.inject.Inject;

@EFragment(R.layout.fragment_favorites)
@OptionsMenu(R.menu.favorite_menu)
public class FavoritesFragment extends Fragment implements FavoritesFragmentView {

    private FavoriteAdapter saleFlatAdapter;
    private FavoriteAdapter saleRoomAdapter;
    private FavoriteAdapter saleHouseAdapter;
    private FavoriteAdapter saleAreaAdapter;
    private FavoriteAdapter rentRoomAdapter;
    private FavoriteAdapter rentHouseAdapter;
    private FavoriteAdapter rentFlatAdapter;

    @Inject
    FavoritePresenter favoritePresenter;

    @Inject
    PreferencesHelper preferencesHelper;

    @ViewById
    TextView message_text;

    @ViewById
    FloatingActionButton clear_button;

    @ViewById
    LinearLayout sale_flats_container, sale_rooms_container, sale_houses_container,
            sale_areas_container, rent_flats_container, rent_rooms_container, rent_houses_container;

    @ViewById
    RecyclerView sale_flats_recycler_view, sale_rooms_recycler_view, sale_houses_recycler_view,
            sale_areas_recycler_view, rent_flats_recycler_view, rent_rooms_recycler_view,
            rent_houses_recycler_view;

    @OptionsMenuItem
    MenuItem option_rate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        favoritePresenter.onCreate(this);
    }

    @AfterViews
    void ready(){
        getActivity().setTitle("Закладки");

        sale_flats_recycler_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        saleFlatAdapter = new FavoriteAdapter(new FavoriteAdapter.FavoriteViewHolder.ClickListener() {
            @Override
            public void OnItemClicked(boolean favorite, int position) {
                showPropertyInfo("sale_flats", saleFlatAdapter.getItemByPosition(position), favorite);
            }

            @Override
            public void OnRemoveButtonClicked(int position) {
                deleteAlertDialog("sale_flats", saleFlatAdapter, position);
            }
        });
        sale_flats_recycler_view.setAdapter(saleFlatAdapter);

        sale_rooms_recycler_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        saleRoomAdapter = new FavoriteAdapter(new FavoriteAdapter.FavoriteViewHolder.ClickListener() {
            @Override
            public void OnItemClicked(boolean favorite, int position) {
                showPropertyInfo("sale_rooms", saleRoomAdapter.getItemByPosition(position), favorite);
            }

            @Override
            public void OnRemoveButtonClicked(int position) {
                deleteAlertDialog("sale_rooms", saleRoomAdapter, position);
            }
        });
        sale_rooms_recycler_view.setAdapter(saleRoomAdapter);

        sale_houses_recycler_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        saleHouseAdapter = new FavoriteAdapter(new FavoriteAdapter.FavoriteViewHolder.ClickListener() {
            @Override
            public void OnItemClicked(boolean favorite, int position) {
                showPropertyInfo("sale_houses", saleHouseAdapter.getItemByPosition(position), favorite);
            }

            @Override
            public void OnRemoveButtonClicked(int position) {
                deleteAlertDialog("sale_houses", saleHouseAdapter, position);
            }
        });
        sale_houses_recycler_view.setAdapter(saleHouseAdapter);

        sale_areas_recycler_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        saleAreaAdapter = new FavoriteAdapter(new FavoriteAdapter.FavoriteViewHolder.ClickListener() {
            @Override
            public void OnItemClicked(boolean favorite, int position) {
                showPropertyInfo("sale_areas", saleAreaAdapter.getItemByPosition(position), favorite);
            }

            @Override
            public void OnRemoveButtonClicked(int position) {
                deleteAlertDialog("sale_areas", saleAreaAdapter, position);
            }
        });
        sale_areas_recycler_view.setAdapter(saleAreaAdapter);

        rent_flats_recycler_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rentFlatAdapter = new FavoriteAdapter(new FavoriteAdapter.FavoriteViewHolder.ClickListener() {
            @Override
            public void OnItemClicked(boolean favorite, int position) {
                showPropertyInfo("rent_flats", rentFlatAdapter.getItemByPosition(position), favorite);
            }

            @Override
            public void OnRemoveButtonClicked(int position) {
                deleteAlertDialog("rent_flats", rentFlatAdapter, position);
            }
        });
        rent_flats_recycler_view.setAdapter(rentFlatAdapter);

        rent_rooms_recycler_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rentRoomAdapter = new FavoriteAdapter(new FavoriteAdapter.FavoriteViewHolder.ClickListener() {
            @Override
            public void OnItemClicked(boolean favorite, int position) {
                showPropertyInfo("rent_rooms", rentRoomAdapter.getItemByPosition(position), favorite);
            }

            @Override
            public void OnRemoveButtonClicked(int position) {
                deleteAlertDialog("rent_rooms", rentRoomAdapter, position);
            }
        });
        rent_rooms_recycler_view.setAdapter(rentRoomAdapter);

        rent_houses_recycler_view.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rentHouseAdapter = new FavoriteAdapter(new FavoriteAdapter.FavoriteViewHolder.ClickListener() {
            @Override
            public void OnItemClicked(boolean favorite, int position) {
                showPropertyInfo("rent_houses", rentHouseAdapter.getItemByPosition(position), favorite);
            }

            @Override
            public void OnRemoveButtonClicked(int position) {
                deleteAlertDialog("rent_houses", rentHouseAdapter, position);
            }
        });
        rent_houses_recycler_view.setAdapter(rentHouseAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (preferencesHelper.getRate().equals("USD")){
            option_rate.setTitle("USD");
        } else {
            option_rate.setTitle("BYN");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        favoritePresenter.getFavorites();
    }

    @Override
    public void onStop() {
        super.onStop();
        favoritePresenter.onStop();
    }

    @OptionsItem(R.id.option_rate)
    void rate(MenuItem item) {
        if (preferencesHelper.getRate().equals("USD")) {
            preferencesHelper.setRate("BYN");
            item.setTitle("BYN");
            saleFlatAdapter.updateRate();
            saleRoomAdapter.updateRate();
            saleHouseAdapter.updateRate();
            saleAreaAdapter.updateRate();
            rentFlatAdapter.updateRate();
            rentRoomAdapter.updateRate();
            rentHouseAdapter.updateRate();
        } else if (preferencesHelper.getRate().equals("BYN")) {
            preferencesHelper.setRate("USD");
            item.setTitle("USD");
            saleFlatAdapter.updateRate();
            saleRoomAdapter.updateRate();
            saleHouseAdapter.updateRate();
            saleAreaAdapter.updateRate();
            rentFlatAdapter.updateRate();
            rentRoomAdapter.updateRate();
            rentHouseAdapter.updateRate();
        }
    }

    @Click(R.id.clear_button)
    void clearAll() {
        AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
        dialog.setTitle("Подтверждение удаления");
        dialog.setMessage("Вы хотите удалить все объявления из закладок?");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Да", (dialog1, which) -> {
            favoritePresenter.clearAll();
            showErrorMessage(null);
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Нет", (dialog12, which) -> dialog12.dismiss());
        dialog.show();
    }

    @Override
    public void showErrorMessage(String error) {
        sale_flats_container.setVisibility(View.GONE);
        sale_rooms_container.setVisibility(View.GONE);
        sale_houses_container.setVisibility(View.GONE);
        sale_areas_container.setVisibility(View.GONE);
        rent_flats_container.setVisibility(View.GONE);
        rent_rooms_container.setVisibility(View.GONE);
        rent_houses_container.setVisibility(View.GONE);
        clear_button.setVisibility(View.GONE);
        message_text.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFavoritesList(String property, List<FavoriteTable> favorites) {
        if (!favorites.isEmpty()) {
            message_text.setVisibility(View.GONE);
            clear_button.setVisibility(View.VISIBLE);
        }
        switch (property){
            case "sale_flats":
                saleFlatAdapter.updateItems(favorites);
                sale_flats_container.setVisibility(favorites.isEmpty() ? View.GONE : View.VISIBLE);
                break;
            case "sale_rooms":
                saleRoomAdapter.updateItems(favorites);
                sale_rooms_container.setVisibility(favorites.isEmpty() ? View.GONE : View.VISIBLE);
                break;
            case "sale_houses":
                saleHouseAdapter.updateItems(favorites);
                sale_houses_container.setVisibility(favorites.isEmpty() ? View.GONE : View.VISIBLE);
            case "sale_areas":
                saleAreaAdapter.updateItems(favorites);
                sale_areas_container.setVisibility(favorites.isEmpty() ? View.GONE : View.VISIBLE);
                break;
            case "rent_flats":
                rentFlatAdapter.updateItems(favorites);
                rent_flats_container.setVisibility(favorites.isEmpty() ? View.GONE : View.VISIBLE);
                break;
            case "rent_rooms":
                rentRoomAdapter.updateItems(favorites);
                rent_rooms_container.setVisibility(favorites.isEmpty() ? View.GONE : View.VISIBLE);
                break;
            case "rent_houses":
                rentHouseAdapter.updateItems(favorites);
                rent_houses_container.setVisibility(favorites.isEmpty() ? View.GONE : View.VISIBLE);
                break;
        }
    }

    @Override
    public void showPropertyInfo(String property, FavoriteTable item, boolean favorite) {
        PropertyItem propertyItem = new PropertyItem();       
        propertyItem.setId(item.getId());
        propertyItem.setSection(item.getSection());
        propertyItem.setPrice(item.getPrice());
        propertyItem.setModifiedDate(item.getModifiedDate());
        propertyItem.setDate(item.getDate());
        propertyItem.setCost(item.getCost());
        propertyItem.setInfo(item.getInfo());
        propertyItem.setAddress(item.getAddress());
        propertyItem.setMetro(item.getMetro());
        propertyItem.setBranch(item.getBranch());
        propertyItem.setThumb(item.getThumb());

        Intent intent = null;
        switch (property) {
            case "sale_flats": intent = new Intent(getContext(), SaleFlatActivity_.class); break;
            case "sale_rooms": intent = new Intent(getContext(), SaleRoomActivity_.class); break;
            case "sale_houses": intent = new Intent(getContext(), SaleHouseActivity_.class); break;
            case "sale_areas": intent = new Intent(getContext(), SaleAreaActivity_.class); break;
            case "rent_flats": intent = new Intent(getContext(), RentFlatActivity_.class); break;
            case "rent_rooms": intent = new Intent(getContext(), RentRoomActivity_.class); break;
            case "rent_houses": intent = new Intent(getContext(), RentHouseActivity_.class); break;
        }

        assert intent != null;
        intent.putExtra("property", propertyItem);
        intent.putExtra("favorite", favorite);
        getActivity().startActivity(intent);
    }

    private void deleteAlertDialog(String property, FavoriteAdapter adapter, int position) {
        AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
        dialog.setTitle("Подтверждение удаления");
        dialog.setMessage("Удалить текущее объявление из закладок?");
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "Да", (dialog1, which) -> {
            favoritePresenter.deleteFavorite(property, adapter.getItemByPosition(position));
            adapter.deleteItem(position);
            if (adapter.getItemCount() == 0) {
                switch (property){
                    case "sale_flats": sale_flats_container.setVisibility(View.GONE); break;
                    case "sale_rooms": sale_rooms_container.setVisibility(View.GONE); break;
                    case "sale_houses": sale_houses_container.setVisibility(View.GONE); break;
                    case "sale_areas": sale_areas_container.setVisibility(View.GONE); break;
                    case "rent_flats": rent_flats_container.setVisibility(View.GONE); break;
                    case "rent_rooms": rent_rooms_container.setVisibility(View.GONE); break;
                    case "rent_houses": rent_houses_container.setVisibility(View.GONE); break;
                }
                checkDataExistence();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Нет", (dialog12, which) -> dialog12.dismiss());
        dialog.show();
    }
    private void checkDataExistence() {
        if (saleFlatAdapter.getItemCount() == 0 && saleRoomAdapter.getItemCount() == 0
                && saleHouseAdapter.getItemCount() == 0 && saleAreaAdapter.getItemCount() == 0
                && rentFlatAdapter.getItemCount() == 0 && rentRoomAdapter.getItemCount() == 0
                && rentHouseAdapter.getItemCount() == 0) {
            showErrorMessage(null);
        }
    }
}
