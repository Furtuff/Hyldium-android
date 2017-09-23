package com.tuff.hyldium.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuff.hyldium.R;
import com.tuff.hyldium.adapter.UserOrderAdapter;
import com.tuff.hyldium.factory.Factory;
import com.tuff.hyldium.model.ItemModel;
import com.tuff.hyldium.utils.Constant;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tuffery on 22/07/17.
 */

public class OrderFragment extends PriorFragment {
    public final static String USER_ORDER = "USER_ORDER";
    private RecyclerView recyclerOrder;
    private TextView header;

    public static Bundle extraOrderedItemList(List<ItemModel> itemList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(USER_ORDER, (Serializable) itemList);
        return bundle;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_user, container, false);
        recyclerOrder = (RecyclerView) view.findViewById(R.id.recyclerOrder);
        header = (TextView) view.findViewById(R.id.myOrderTxt);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<ItemModel> itemsOrdered = null;
        itemsOrdered = Factory.build.getICache().getCurrentOrder();
        UserOrderAdapter adapter = new UserOrderAdapter(itemsOrdered);
        recyclerOrder.setAdapter(adapter);
        StringBuilder sb = new StringBuilder();
        sb.append("MA COMMANDE : ").append(String.valueOf(adapter.countTotalPrice())).append(" €");
        String headerTxt = sb.toString();
        header.setText(headerTxt);

    }

    public void updateOrder(List<ItemModel> items) {
        UserOrderAdapter adapter = null;
        if (recyclerOrder != null && recyclerOrder.getAdapter() != null) {
            adapter = (UserOrderAdapter) recyclerOrder.getAdapter();


            if (adapter == null && items != null) {
                adapter = new UserOrderAdapter(items);
                recyclerOrder.setAdapter(adapter);
            } else {
                adapter.orderedItems = items;
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public int getPriority() {
        return Constant.SECONDCONTAINER_PRIORITY;
    }
}
