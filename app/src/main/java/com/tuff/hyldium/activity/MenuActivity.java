package com.tuff.hyldium.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.tuff.hyldium.R;
import com.tuff.hyldium.adapter.NavBarAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuffery on 22/07/17.
 */

public abstract class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentLayout());
        getLayoutInflater().inflate(R.layout.nav_bar_main, (ViewGroup) findViewById(android.R.id.content));
        RecyclerView menu = (RecyclerView) findViewById(R.id.my_drawer);
        List<List<String>> menuList = new ArrayList<List<String>>();
        List<String> subList = new ArrayList<>();
        subList.add("Commander");
        menuList.add(subList);
        subList.clear();
        subList.add("Membres");
        menuList.add(subList);
        subList.clear();
        subList.add("Gerer");
        subList.add("Membres");
        subList.add("Commandes");
        subList.add("Livraison");
        menuList.add(subList);
        menu.setAdapter(new NavBarAdapter(menuList));
    }

    public abstract int setContentLayout();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
      /*  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }
}
