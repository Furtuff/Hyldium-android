package com.tuff.hyldium.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.tuff.hyldium.R;
import com.tuff.hyldium.adapter.ItemListAdapter;
import com.tuff.hyldium.fragment_callback.ItemList;
import com.tuff.hyldium.model.ItemModel;
import com.tuff.hyldium.utils.Constant;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tuffery on 22/07/17.
 */

public class ItemListFragment extends PriorFragment {
    private static final String ITEMS = "ITEMS";
    private RecyclerView itemRecycler;
    private BoomMenuButton bmb;

    public static Bundle extraItemList(List<ItemModel> itemList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ITEMS, (Serializable) itemList);
        return bundle;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        itemRecycler = (RecyclerView) view.findViewById(R.id.itemList);
        bmb = (BoomMenuButton) view.findViewById(R.id.boomItemMenu);
        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            bmb.setVisibility(View.GONE);
        } else {
            bmb.setVisibility(View.VISIBLE);
        }

        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HamButton.Builder userOrder = new HamButton.Builder()
                .normalImageRes(R.mipmap.ic_launcher_round)
                .normalColor(Color.DKGRAY)
                .normalText(getString(R.string.my_order))
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        ((ItemList) view.getContext()).onePanelUserOrder();
                    }
                });
        bmb.addBuilder(userOrder);

        Bundle bundle = getArguments();
        List<ItemModel> items = (List<ItemModel>) bundle.getSerializable(ITEMS);
        if (items != null) {
            itemRecycler.setAdapter(new ItemListAdapter(items));
        }

    }

    @Override
    public int getPriority() {
        return Constant.FIRSTCONTAINER_PRIORITY;
    }

}
