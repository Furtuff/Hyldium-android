package com.tuff.hyldium.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.tuff.hyldium.R;
import com.tuff.hyldium.contract.ICacheCallBack;
import com.tuff.hyldium.factory.Factory;
import com.tuff.hyldium.fragment.ItemDetailFragment;
import com.tuff.hyldium.fragment.ItemListFragment;
import com.tuff.hyldium.fragment.OrderFragment;
import com.tuff.hyldium.fragment.PriorFragment;
import com.tuff.hyldium.fragment.UserProfileFragment;
import com.tuff.hyldium.fragment_callback.ItemDetails;
import com.tuff.hyldium.fragment_callback.ItemList;
import com.tuff.hyldium.fragment_callback.UserOrder;
import com.tuff.hyldium.fragment_callback.UserProfile;
import com.tuff.hyldium.model.ItemModel;
import com.tuff.hyldium.model.UserItemOrderModel;
import com.tuff.hyldium.model.UserModel;
import com.tuff.hyldium.utils.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SUActivity extends MenuActivity implements ItemList, ItemDetails, UserOrder, UserProfile, ICacheCallBack {
    private static OrderFragment orderFragment;
    private static String lastStackedFragmentType;
    private ProgressBar progressBar;
    private Spinner userSpinner;
    private TextView infoMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        userSpinner = (Spinner) findViewById(R.id.userName);
        infoMessage = (TextView) findViewById(R.id.infoMessage);

        ArrayAdapter<UserModel> userAdapter = new ArrayAdapter<UserModel>(this, android.R.layout.simple_spinner_item, Factory.build.getICache().getCurrentUsers());
        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userSpinner.setAdapter(userAdapter);
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                PriorFragment priorFragment = (PriorFragment) getSupportFragmentManager().findFragmentById(R.id.firstContainer);
                if (priorFragment != null) {
                    lastStackedFragmentType = priorFragment.getClass().getName();
                }
            }
        });

        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            findViewById(R.id.secondContainer).setVisibility(View.VISIBLE);
            movingOnTwoPanel();
        } else {
            findViewById(R.id.secondContainer).setVisibility(View.GONE);
            //movingOnOnePanel();
        }
        stopProgressBar(null);
    }


    @Override
    public int setContentLayout() {
        return R.layout.activity_su;
    }

    @Override
    protected void userPofileSelected() {
        UserProfileFragment upf = new UserProfileFragment();
        upf.setArguments(UserProfileFragment.extraItem((UserModel) userSpinner.getSelectedItem()));
        dispatchFragment(upf, Constant.USER_PROFILE_FRAGMENT);

    }


    @Override
    protected void orderSelected() {
        Factory.build.getICache().getItemsList(this, null);
        startProgressBar();
    }

    @Override
    protected void deliverySelected() {

    }


    @Override
    public void itemSearch(String typedText) {

    }

    @Override
    public void itemSelected(ItemModel selectedItem) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        itemDetailFragment.setArguments(ItemDetailFragment.extraItem(selectedItem));
        dispatchFragment(itemDetailFragment, Constant.ITEM_DETAIL_FRAGMENT);
    }

    @Override
    public void itemOrdered(ItemModel orderedItem) {

    }

    @Override
    public void onePanelUserOrder() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.firstContainer, getOrderFragment(), Constant.USER_ORDER_FRAGMENT);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void editItem(ItemModel editedItem) {

    }

    @Override
    public void orderUserItem(UserItemOrderModel orderedItem) {
        if (orderedItem.bundlePart != 0) {

        }
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }

    @Override
    public void editOrdered(ItemModel itemOrdered) {

    }

    @Override
    public void deleteOrdered(ItemModel itemKilled) {

    }

    @Override
    public void disconnect() {
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void changePassword(String pswdHash) {

    }

    void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        infoMessage.setText(null);
    }

    void stopProgressBar(String errorMessage) {
        progressBar.setVisibility(View.GONE);
        if (errorMessage != null) {
            infoMessage.setText(errorMessage);
        }
    }

    void dispatchFragment(PriorFragment fragment, String ref) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            dualPanelManageSecond();
            if (fragment.getPriority() == Constant.SECONDCONTAINER_PRIORITY) {
                ft.replace(R.id.secondContainer, fragment);
            } else {
                ft.replace(R.id.firstContainer, fragment);
            }
        } else {
            ft.replace(R.id.firstContainer, fragment);
        }
        if (fm.getBackStackEntryCount() > 0) {

            if (!lastStackedFragmentType.equals(fragment.getClass().getName())) {
                ft.addToBackStack(null);
            }
        } else {
            ft.addToBackStack(null);
        }
        ft.commit();
    }

    void movingOnTwoPanel() {
        FragmentManager fm = getSupportFragmentManager();
        PriorFragment currentFragment = (PriorFragment) fm.findFragmentById(R.id.firstContainer);
        if (currentFragment != null) {
            if (currentFragment.getPriority() == Constant.SECONDCONTAINER_PRIORITY) {
                FragmentManager.BackStackEntry firstPriority = fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 1);
                fm.popBackStackImmediate(firstPriority.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.secondContainer, currentFragment);
                ft.addToBackStack(null);
                ft.commit();
            } else {
                dualPanelManageSecond();

            }
        }

    }

    private void dualPanelManageSecond() {
        if (selectedItem == R.id.nav_order) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.secondContainer, getOrderFragment());
            ft.commit();
        }
    }

    private OrderFragment getOrderFragment() {
        if (orderFragment == null) {
            orderFragment = new OrderFragment();
            List<ItemModel> items = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                ItemModel test = new ItemModel();
                test.name = "zregzergzregfdsdfvsdfvssfvsfv  zrgzrg" + i;
                test.reference = "testetets" + i;
                test.price = 4894.22;
                test.byBundle = 12;
                test.ordered = new Random().nextInt() + 1;
                items.add(test);
            }
            orderFragment.setArguments(OrderFragment.extraOrderedItemList(items));
        }
        return orderFragment;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
            }

        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void askedItemList(List<ItemModel> itemList) {
        stopProgressBar(null);
        ItemListFragment itemListFragment = new ItemListFragment();
        itemListFragment.setArguments(ItemListFragment.extraItemList(itemList));
        dispatchFragment(itemListFragment, Constant.ITEM_LIST_FRAGMENT);
    }
}
