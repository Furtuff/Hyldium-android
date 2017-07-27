package com.tuff.hyldium.adapter;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuff.hyldium.R;

/**
 * Created by tuffery on 25/07/17.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {
    public ImageView icon;
    public TextView itemText;
    public ConstraintLayout rootView;
    public RecyclerView subRecycler;

    public ItemViewHolder(View view) {
        super(view);
        this.icon = (ImageView) view.findViewById(R.id.navItemImage);
        this.itemText = (TextView) view.findViewById(R.id.navItemText);
        this.rootView = (ConstraintLayout) view.findViewById(R.id.itemRoot);
        this.subRecycler = (RecyclerView) view.findViewById(R.id.subItemRecycler);

    }

}
