package com.tuff.hyldium.fragment_callback;

/**
 * Created by tuffery on 25/08/17.
 */

public interface UserProfile {
    void disconnect();

    void changePassword(String pswdHash);
}
