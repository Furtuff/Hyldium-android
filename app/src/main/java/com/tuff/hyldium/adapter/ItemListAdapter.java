package com.tuff.hyldium.adapter;

import android.graphics.BitmapFactory;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuff.hyldium.R;
import com.tuff.hyldium.fragment_callback.ItemList;
import com.tuff.hyldium.model.ItemModel;

import java.util.List;

/**
 * Created by tuffery on 15/08/17.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemVH> {
    private List<ItemModel> items;

    public ItemListAdapter(List<ItemModel> items) {
        this.items = items;
    }

    @Override
    public ItemVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item_list_item, parent, false);
        final ItemVH result = new ItemVH(view);
        result.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((ItemList) view.getContext()).itemSelected(items.get(result.getAdapterPosition()));
            }
        });
        result.photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (items.get(result.getAdapterPosition()).photo == null) {
                    //TODO take photo
                } else {

                }
            }
        });
        return result;
    }

    @Override
    public void onBindViewHolder(ItemVH holder, int position) {
        ItemModel item = items.get(position);
        if (item.name != null) {
            holder.name.setText(item.name);
        }
        if (item.reference != null) {
            holder.ref.setText(item.reference);
        }
        if (item.byBundle != 0) {
            holder.byBundle.setText(String.valueOf(item.byBundle));
        }
        if (item.price != 0) {
            holder.price.setText(String.valueOf(item.price));
        }
        if (item.photo != null) {
            holder.photo.setImageBitmap(BitmapFactory.decodeByteArray(item.photo, 0, item.photo.length));
        }
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        } else {
            return items.size();
        }
    }

    public static class ItemVH extends RecyclerView.ViewHolder {
        public ConstraintLayout root;
        public TextView price;
        public TextView ref;
        public TextView name;
        public TextView byBundle;
        public ImageView photo;

        public ItemVH(View itemView) {
            super(itemView);
            root = (ConstraintLayout) itemView.findViewById(R.id.itemListRoot);
            price = (TextView) itemView.findViewById(R.id.priceHTtxt);
            ref = (TextView) itemView.findViewById(R.id.referencetxt);
            name = (TextView) itemView.findViewById(R.id.itemName);
            byBundle = (TextView) itemView.findViewById(R.id.byBundle);
            photo = (ImageView) itemView.findViewById(R.id.itemImage);
        }
    }
}
