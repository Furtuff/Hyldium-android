package com.tuff.hyldium.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuff.hyldium.R;
import com.tuff.hyldium.adapter.UserOrderAdapter;
import com.tuff.hyldium.model.ItemModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tuffery on 22/07/17.
 */

public class OrderFragment extends android.support.v4.app.Fragment {
    private final static String USER_ORDER = "USER_ORDER";
    private RecyclerView recycleOrder;

    public static Bundle extraOrderedItemList(List<ItemModel> itemList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_ORDER, (Serializable) itemList);
        return bundle;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_user, container, false);
        recycleOrder = (RecyclerView) view.findViewById(R.id.recyclerOrder);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<ItemModel> itemsOrdered = (List<ItemModel>) getArguments().getSerializable(USER_ORDER);
        if (itemsOrdered != null) {
            recycleOrder.setAdapter(new UserOrderAdapter(itemsOrdered));

        }
    }
}
