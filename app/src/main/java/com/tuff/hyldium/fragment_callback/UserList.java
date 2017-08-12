package com.tuff.hyldium.fragment_callback;

import com.tuff.hyldium.model.UserModel;

/**
 * Created by tuffery on 12/08/17.
 */

public interface UserList {
    void deleteUser(UserModel userModel);

    void editUser(UserModel userModel);

    void addUser();
}
