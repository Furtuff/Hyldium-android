package com.tuff.hyldium.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuff.hyldium.R;

import java.util.List;

/**
 * Created by tuffery on 25/07/17.
 */

public class NavBarAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    public List<List<String>> menuList;

    public NavBarAdapter(List<List<String>> menuList) {
        this.menuList = menuList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nav_bar_item, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        if (menuList.get(position) != null) {
            holder.itemText.setText(menuList.get(position).get(0));
        }
        if (menuList.get(position).size() > 1) {
            holder.subRecycler.setAdapter(new SubItemAdapter(menuList.get(position)));
        }
    }

    @Override
    public int getItemCount() {
        if (menuList == null) {
            return 0;
        } else {
            return menuList.size();
        }
    }

}
