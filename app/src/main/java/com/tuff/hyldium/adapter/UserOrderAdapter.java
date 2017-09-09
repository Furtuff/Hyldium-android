package com.tuff.hyldium.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuff.hyldium.R;
import com.tuff.hyldium.model.ItemModel;

import java.util.List;

/**
 * Created by tuffery on 25/08/17.
 */

public class UserOrderAdapter extends RecyclerView.Adapter<UserOrderAdapter.OrderedItemVH> implements View.OnClickListener {

    public List<ItemModel> orderedItems;

    public UserOrderAdapter(List<ItemModel> orderedItems) {
        this.orderedItems = orderedItems;
    }

    @Override
    public OrderedItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_user_order_item, parent, false);
        OrderedItemVH vh = new OrderedItemVH(view);
        vh.delete.setOnClickListener(this);
        vh.edit.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(OrderedItemVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (orderedItems == null) {
            return 0;
        } else {
            return orderedItems.size();
        }

    }

    @Override
    public void onClick(View view) {

    }

    public static class OrderedItemVH extends RecyclerView.ViewHolder {
        public TextView itemName, ref, price, ordered;
        public ImageView photo;
        public ImageButton edit, delete;

        public OrderedItemVH(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            ref = (TextView) itemView.findViewById(R.id.reference);
            price = (TextView) itemView.findViewById(R.id.price);
            ordered = (TextView) itemView.findViewById(R.id.ordered);
            photo = (ImageView) itemView.findViewById(R.id.itemImage);
            edit = (ImageButton) itemView.findViewById(R.id.editOrdered);
            delete = (ImageButton) itemView.findViewById(R.id.deleteOrdered);
        }
    }
}
