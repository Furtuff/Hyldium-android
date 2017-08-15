package com.tuff.hyldium.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.tuff.hyldium.R;
import com.tuff.hyldium.fragment.ItemListFragment;
import com.tuff.hyldium.fragment.UserListFragment;
import com.tuff.hyldium.fragment_callback.ItemList;
import com.tuff.hyldium.fragment_callback.UserList;
import com.tuff.hyldium.model.ItemModel;
import com.tuff.hyldium.model.UserModel;
import com.tuff.hyldium.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class SUActivity extends MenuActivity implements UserList, ItemList {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.twoPaneMode)) {
            findViewById(R.id.secondContainer).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.secondContainer).setVisibility(View.GONE);

        }
    }


    @Override
    protected void orderSelected() {
        ItemListFragment itemListFragment = new ItemListFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        List<ItemModel> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ItemModel prout = new ItemModel();
            prout.name = "zregzergzregfdsdfvsdfvssfvsfv  zrgzrg" + i;
            prout.reference = "zrfz65454sf" + i;
            prout.price = 4894.22;
            prout.byBundle = 12;
            items.add(prout);
        }
        itemListFragment.setArguments(ItemListFragment.extraItemList(items));
        if (getSupportFragmentManager().getFragments() != null) {
            ft.replace(R.id.firstContainer, itemListFragment, Constant.ITEM_LIST_FRAGMENT);
        } else {
            ft.add(R.id.firstContainer, itemListFragment, Constant.ITEM_LIST_FRAGMENT);
        }
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    protected void deliverySelected() {

    }

    @Override
    protected void manageSelected(int itemId) {
        switch (itemId) {
            case R.id.nav_manage_users:
                if (getSupportFragmentManager().findFragmentByTag(Constant.USER_LIST_FRAGMENT) == null) {
                    List<UserModel> list = new ArrayList<UserModel>();
                    for (int i = 0; i < 10; i++) {
                        UserModel prout = new UserModel();
                        prout.name = "prout" + String.valueOf(i);
                        prout.createdDate = Long.valueOf(i);
                        list.add(prout);
                    }
                    UserListFragment userListFragment = new UserListFragment();
                    userListFragment.setArguments(UserListFragment.extraUserList(list));
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.add(R.id.firstContainer, userListFragment, Constant.USER_LIST_FRAGMENT);
                    ft.addToBackStack(null);
                    ft.commit();
                }
                break;
        }
    }

    @Override
    public int setContentLayout() {
        return R.layout.activity_su;
    }

    @Override
    public void deleteUser(UserModel userModel) {
    }

    @Override
    public void editUser(UserModel userModel) {

    }

    @Override
    public void addUser() {

    }


    @Override
    public void itemSearch(String typedText) {

    }

    @Override
    public void itemSelected(ItemModel selectedItem) {

    }

    @Override
    public void itemOrdered(ItemModel orderedItem) {

    }

    @Override
    public void onePanelUserOrder() {

    }
}
