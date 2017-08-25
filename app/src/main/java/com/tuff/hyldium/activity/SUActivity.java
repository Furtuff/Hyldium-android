package com.tuff.hyldium.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.tuff.hyldium.R;
import com.tuff.hyldium.fragment.ItemDetailFragment;
import com.tuff.hyldium.fragment.ItemListFragment;
import com.tuff.hyldium.fragment_callback.ItemDetails;
import com.tuff.hyldium.fragment_callback.ItemList;
import com.tuff.hyldium.fragment_callback.UserList;
import com.tuff.hyldium.fragment_callback.UserOrder;
import com.tuff.hyldium.model.ItemModel;
import com.tuff.hyldium.model.UserItemOrderModel;
import com.tuff.hyldium.model.UserModel;
import com.tuff.hyldium.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class SUActivity extends MenuActivity implements UserList, ItemList, ItemDetails, UserOrder {

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
    protected void userPofileSelected() {

    }


    @Override
    protected void orderSelected() {
        ItemListFragment itemListFragment = new ItemListFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        List<ItemModel> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ItemModel test = new ItemModel();
            test.name = "zregzergzregfdsdfvsdfvssfvsfv  zrgzrg" + i;
            test.reference = "testetets" + i;
            test.price = 4894.22;
            test.byBundle = 12;
            items.add(test);
        }
        itemListFragment.setArguments(ItemListFragment.extraItemList(items));
        ft.replace(R.id.firstContainer, itemListFragment, Constant.ITEM_LIST_FRAGMENT);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    protected void deliverySelected() {

    }

    /*@Override
    protected void manageSelected(int itemId) {
        switch (itemId) {
            case R.id.nav_manage_users:
                if (getSupportFragmentManager().findFragmentByTag(Constant.USER_LIST_FRAGMENT) == null) {
                    List<UserModel> list = new ArrayList<UserModel>();
                    for (int i = 0; i < 10; i++) {
                        UserModel prout = new UserModel();
                        prout.firstName = "blob" + String.valueOf(i);
                        prout.createdDate = Long.valueOf(i);
                        list.add(prout);
                    }
                    UserListFragment userListFragment = new UserListFragment();
                    userListFragment.setArguments(UserListFragment.extraUserList(list));
                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.firstContainer, userListFragment, Constant.USER_LIST_FRAGMENT);
                    ft.addToBackStack(null);
                    ft.commit();
                }
                break;
        }
    }*/

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
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        itemDetailFragment.setArguments(ItemDetailFragment.extraItem(selectedItem));
        ft.replace(R.id.firstContainer, itemDetailFragment, Constant.ITEM_DETAIL_FRAGMENT);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void itemOrdered(ItemModel orderedItem) {

    }

    @Override
    public void onePanelUserOrder() {

    }

    @Override
    public void editItem(ItemModel editedItem) {

    }

    @Override
    public void orderUserItem(UserItemOrderModel orderedItem) {

    }

    @Override
    public void editOrdered(ItemModel itemOrdered) {

    }

    @Override
    public void deleteOrdered(ItemModel itemKilled) {

    }
}
