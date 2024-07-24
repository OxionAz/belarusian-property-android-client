package com.oxionaz.belarussian_property.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    @Inject
    ImageDownloader imageDownloader;

    private List<String> items;
    private ImageViewHolder.ClickListener clickListener;

    public ImageAdapter(List<String> items, ImageViewHolder.ClickListener clickListener){
        App.getAppComponent().inject(this);
        this.items = items;
        this.clickListener = clickListener;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(itemView, clickListener);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        String item = items.get(position);
        imageDownloader.loadWithGlide(item, holder.photo);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ClickListener clickListener;
        ImageView photo;

        public ImageViewHolder(View itemView, ClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;
            itemView.setOnClickListener(this);
            photo = (ImageView) itemView.findViewById(R.id.photo);
        }

        @Override
        public void onClick(View v) {
            clickListener.OnItemClicked(getAdapterPosition());
        }

        public interface ClickListener{
            void OnItemClicked(int position);
        }
    }
}
