package com.oxionaz.belarussian_property.view.adapters;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.oxionaz.belarussian_property.R;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.other.util.ImageDownloader;
import com.oxionaz.belarussian_property.presenter.mapping.MapperDTO;
import com.oxionaz.belarussian_property.presenter.mapping.PropertyItem;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

public class PropertyAdapter extends BasePropertyAdapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;
    private static final int LOADING = 1;

    @Inject
    ImageDownloader imageDownloader;

    @Inject
    MapperDTO mapperDTO;

    private List<PropertyItem> items;
    private List<FavoriteData> favorites;
    private PropertyViewHolder.ClickListener clickListener;
    private boolean isLoadingAdded = false;

    public PropertyAdapter(PropertyViewHolder.ClickListener clickListener){
        App.getAppComponent().inject(this);
        this.clickListener = clickListener;
        this.items = new ArrayList<>();
        this.favorites = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ITEM:
                View v1 = inflater.inflate(R.layout.property_item, parent, false);
                viewHolder = new PropertyViewHolder(v1, clickListener);
                break;
            case LOADING:
                View v2 = inflater.inflate(R.layout.loading_item, parent, false);
                viewHolder = new LoadingViewHolder(v2);
                break;
        }
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PropertyItem item = items.get(position);

        switch (getItemViewType(position)) {
            case ITEM:
                PropertyViewHolder pHolder = (PropertyViewHolder) holder;
                imageDownloader.loadWithGlide(item.getThumb(), pHolder.photo);
                pHolder.date.setText(item.getDate());
                pHolder.price.setText(item.getCost());
                pHolder.info.setText(item.getInfo());
                pHolder.address.setText(item.getAddress());
                checkMetro(item.getMetro(), item.getBranch(), pHolder.metro);
                pHolder.favorite = checkFavorite(item, favorites, pHolder.bookmark);
                break;
            case LOADING:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return (position == items.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // Helpers

    public void updateRate(){
       if (!items.isEmpty()) {
           Observable.from(items).map(item -> {
               item.setCost(mapperDTO.transformPrice(item.getPrice()));
               return item;
           }).toList().subscribe(new Subscriber<List<PropertyItem>>() {
               @Override
               public void onCompleted() {

               }

               @Override
               public void onError(Throwable e) {
                   unsubscribe();
                   Log.e("ADAPTER", e.getMessage());
               }

               @Override
               public void onNext(List<PropertyItem> items) {
                   unsubscribe();
                   updateItems(items);
               }
           });
       }
    }

    public PropertyItem getItemByPosition(int position){
        return items.get(position);
    }

    public void addItems(List<PropertyItem> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public synchronized void updateItems(List<PropertyItem> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public FavoriteData getFavoriteByPosition(int position){
        return favorites.get(position);
    }

    public void setFavorites(List<FavoriteData> favorites) {
        this.favorites = favorites;
        notifyDataSetChanged();
    }

    public void addFavorite(int position) {
        PropertyItem item = items.get(position);
        this.favorites.add(new FavoriteData(item.getId(), item.getSection(), item.getPrice()));
        notifyDataSetChanged();
    }

    public void deleteFavorite(int favorite) {
        this.favorites.remove(favorite);
        notifyDataSetChanged();
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        items.add(new PropertyItem());
        notifyItemInserted(getItemCount() - 1);
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;
        items.remove(getItemCount() - 1);
        notifyItemRemoved(getItemCount());
    }

    // View Holders

    public static class PropertyViewHolder extends RecyclerView.ViewHolder {

        ClickListener clickListener;
        ImageView photo;
        TextView date, price, info, address, metro;
        ImageButton bookmark;
        Integer favorite;

        public PropertyViewHolder(View itemView, ClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;
            itemView.setOnClickListener(v ->
                    clickListener.OnItemClicked(favorite != null, getAdapterPosition()));

            date = (TextView) itemView.findViewById(R.id.date);
            photo = (ImageView) itemView.findViewById(R.id.photo);
            price = (TextView) itemView.findViewById(R.id.price);
            info = (TextView) itemView.findViewById(R.id.info);
            address = (TextView) itemView.findViewById(R.id.address);
            metro = (TextView) itemView.findViewById(R.id.metro);
            bookmark = (ImageButton) itemView.findViewById(R.id.bookmark);
            bookmark.setOnClickListener(v ->
                    clickListener.OnFavoriteButtonClicked(favorite, getAdapterPosition()));
        }

        public interface ClickListener{
            void OnItemClicked(boolean favorite, int position);
            void OnFavoriteButtonClicked(Integer favorite, int position);
        }
    }

    private static class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
