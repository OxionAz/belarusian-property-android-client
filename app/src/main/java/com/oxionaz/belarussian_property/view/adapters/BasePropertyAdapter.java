package com.oxionaz.belarussian_property.view.adapters;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.TextView;
import com.oxionaz.belarussian_property.R;
import com.oxionaz.belarussian_property.presenter.mapping.PropertyItem;
import java.util.List;
import java.util.Objects;

public abstract class BasePropertyAdapter <VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    void checkMetro(String name, String branch, TextView metro){
        if (name != null && branch.equals("RED")) {
            metro.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_metro_red_15dp, 0, 0, 0);
            metro.setText(name);
            metro.setVisibility(android.view.View.VISIBLE);
        } else if (name != null && branch.equals("BLUE")) {
            metro.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_metro_blue_15dp, 0, 0, 0);
            metro.setText(name);
            metro.setVisibility(android.view.View.VISIBLE);
        } else {
            metro.setVisibility(android.view.View.GONE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    Integer checkFavorite(PropertyItem item, List<FavoriteData> data, ImageButton button) {
        Integer fav_pos = null;

        if (!data.isEmpty()) {
            for (int i = 0 ; i < data.size() ; i++) {
                FavoriteData f = data.get(i);
                if (Objects.equals(f.id, item.getId()) && Objects.equals(f.section, item.getSection()) && Objects.equals(f.price, item.getPrice())) {
                    fav_pos = i;
                    break;
                }
            }
        }

        if (fav_pos != null) {
            button.setImageResource(R.drawable.ic_bookmark_black_24dp);
            return fav_pos;
        } else {
            button.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
            return null;
        }
    }
}
