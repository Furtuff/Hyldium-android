package com.tuff.hyldium.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.tuff.hyldium.R;
import com.tuff.hyldium.fragment.UserListFragment;
import com.tuff.hyldium.fragment_callback.UserList;
import com.tuff.hyldium.model.UserModel;
import com.tuff.hyldium.utils.Constant;

import java.util.ArrayList;
import java.util.List;

public class SUActivity extends MenuActivity implements UserList {

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
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.firstContainer)).commit();

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
}
