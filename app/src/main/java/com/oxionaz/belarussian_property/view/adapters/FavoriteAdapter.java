package com.oxionaz.belarussian_property.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.oxionaz.belarussian_property.R;
import com.oxionaz.belarussian_property.model.source.db.models.cache.FavoriteTable;
import com.oxionaz.belarussian_property.other.App;
import com.oxionaz.belarussian_property.other.util.ImageDownloader;
import com.oxionaz.belarussian_property.presenter.mapping.MapperDTO;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

public class FavoriteAdapter extends BasePropertyAdapter<FavoriteAdapter.FavoriteViewHolder> {

    @Inject
    ImageDownloader imageDownloader;

    @Inject
    MapperDTO mapperDTO;

    private List<FavoriteTable> items;
    private FavoriteViewHolder.ClickListener clickListener;

    public FavoriteAdapter(FavoriteViewHolder.ClickListener clickListener){
        App.getAppComponent().inject(this);
        this.clickListener = clickListener;
        this.items = new ArrayList<>();
    }

    @Override
    public FavoriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_item, parent, false);
        return new FavoriteViewHolder(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(FavoriteViewHolder holder, int position) {
        FavoriteTable item = items.get(position);
        imageDownloader.loadWithGlide(item.getThumb(), holder.photo);
        holder.date.setText(item.getDate());
        holder.price.setText(item.getCost());
        holder.info.setText(item.getInfo());
        holder.address.setText(item.getAddress());
        checkMetro(item.getMetro(), item.getBranch(), holder.metro);
        holder.bookmark.setImageResource(R.drawable.ic_clear_black_24dp);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {

        ClickListener clickListener;
        ImageView photo;
        TextView date, price, info, address, metro;
        ImageButton bookmark;

        public FavoriteViewHolder(View itemView, ClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;
            itemView.setOnClickListener(v ->
                    clickListener.OnItemClicked(true, getAdapterPosition()));

            date = (TextView) itemView.findViewById(R.id.date);
            photo = (ImageView) itemView.findViewById(R.id.photo);
            price = (TextView) itemView.findViewById(R.id.price);
            info = (TextView) itemView.findViewById(R.id.info);
            address = (TextView) itemView.findViewById(R.id.address);
            metro = (TextView) itemView.findViewById(R.id.metro);
            bookmark = (ImageButton) itemView.findViewById(R.id.bookmark);
            bookmark.setOnClickListener(v ->
                    clickListener.OnRemoveButtonClicked(getAdapterPosition()));
        }

        public interface ClickListener{
            void OnItemClicked(boolean favorite, int position);
            void OnRemoveButtonClicked(int position);
        }
    }

    public void updateRate(){
        if (!items.isEmpty()) {
            Observable.from(items).map(item -> {
                item.setCost(mapperDTO.transformPrice(item.getPrice()));
                return item;
            }).toList().subscribe(new Subscriber<List<FavoriteTable>>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    unsubscribe();
                    Log.e("ADAPTER", e.getMessage());
                }

                @Override
                public void onNext(List<FavoriteTable> items) {
                    unsubscribe();
                    updateItems(items);
                }
            });
        }
    }

    public FavoriteTable getItemByPosition(int position){
        return items.get(position);
    }

    public synchronized void updateItems(List<FavoriteTable> items){
        this.items = items;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.items.remove(position);
        notifyItemRemoved(position);
    }
}
