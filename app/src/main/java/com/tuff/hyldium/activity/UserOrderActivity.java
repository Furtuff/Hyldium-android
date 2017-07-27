package com.tuff.hyldium.activity;

import android.os.Bundle;

import com.tuff.hyldium.R;

public class UserOrderActivity extends MenuActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int setContentLayout() {
        return R.layout.activity_user_order;
    }
}
