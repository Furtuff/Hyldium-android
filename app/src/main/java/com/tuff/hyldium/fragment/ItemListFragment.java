package com.tuff.hyldium.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.tuff.hyldium.R;
import com.tuff.hyldium.adapter.ItemListAdapter;
import com.tuff.hyldium.factory.Factory;
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
    private final long minKeyStrokeDelay = 500;
    Runnable searchQuery;
    private RecyclerView itemRecycler;
    private BoomMenuButton bmb;
    private SearchView searchView;
    private Handler strokeDelay;
    private String query = "";

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
        searchView = (SearchView) view.findViewById(R.id.searchItem);
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
        strokeDelay = new Handler();
        searchQuery = new Runnable() {
            @Override
            public void run() {
                Factory.build.getIComm().searchItem(getContext(), query);
            }
        };
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
        List<ItemModel> items = null;
        if (bundle != null) {
            items = (List<ItemModel>) bundle.getSerializable(ITEMS);
        }
        if (items != null) {
            itemRecycler.setAdapter(new ItemListAdapter(items));
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                strokeDelay.removeCallbacksAndMessages(null);
                query = newText;
                strokeDelay.postDelayed(searchQuery, minKeyStrokeDelay);
                return false;
            }
        });
    }

    public void updateList(List<ItemModel> items) {
        ItemListAdapter adapter = (ItemListAdapter) itemRecycler.getAdapter();
        if (adapter == null && items != null) {
            adapter = new ItemListAdapter(items);
            itemRecycler.setAdapter(adapter);
        } else {
            adapter.items = items;
            itemRecycler.getAdapter().notifyDataSetChanged();
        }

    }
    @Override
    public int getPriority() {
        return Constant.FIRSTCONTAINER_PRIORITY;
    }

}
