package com.oxionaz.belarussian_property.other.util;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.oxionaz.belarussian_property.other.App;
import javax.inject.Inject;

public class ImageDownloader{

    @Inject
    Context context;

    public ImageDownloader(){
        App.getAppComponent().inject(this);
    }

    public void loadWithGlide(String imgUrl, ImageView target){
        Glide.with(context)
                .load(imgUrl)
                .crossFade()
                .into(target);
    }

    public void clearGlideCache(){
        Glide.get(context).clearDiskCache();
        Log.d("Glide", "clearGlideCache: The cache was cleaned");
    }
}
