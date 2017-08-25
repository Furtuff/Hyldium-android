package com.tuff.hyldium.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuff.hyldium.R;
import com.tuff.hyldium.fragment_callback.UserList;
import com.tuff.hyldium.model.UserModel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tuffery on 08/08/17.
 */

public class UserListFragment extends android.support.v4.app.Fragment {
    private static final String USERS = "USERS";
    public RecyclerView usersRecycler;
    public FloatingActionButton addUser;

    public static Bundle extraUserList(List<UserModel> userList) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(USERS, (Serializable) userList);
        return bundle;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        usersRecycler = (RecyclerView) view.findViewById(R.id.userList);
        addUser = (FloatingActionButton) view.findViewById(R.id.addUser);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        List<UserModel> users = (List<UserModel>) bundle.getSerializable(USERS);
        if (users != null) {
            UserListAdapter userListAdapter = new UserListAdapter(users);
            usersRecycler.setAdapter(userListAdapter);

        }
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((UserList) view.getContext()).addUser();
            }
        });
    }

    public void refreshList(List<UserModel> users) {
        if (usersRecycler.getAdapter() != null) {
            ((UserListAdapter) usersRecycler.getAdapter()).setUserList(users);
        }
    }
}
