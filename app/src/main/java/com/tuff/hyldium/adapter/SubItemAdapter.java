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

public class SubItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    public List<String> subList;

    public SubItemAdapter(List<String> subList) {
        this.subList = subList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nav_bar_item, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        if (subList.get(position) != null) {
            if (position != subList.size() - 1) {
                holder.itemText.setText(subList.get(position + 1));
            }

        }
    }

    @Override
    public int getItemCount() {
        if (subList != null) {
            return subList.size() - 1;
        } else {
            return 0;
        }
    }


}
