package com.tuff.hyldium.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.tuff.hyldium.R;
import com.tuff.hyldium.utils.Constant;

/**
 * Created by tuffery on 22/07/17.
 */

public abstract class MenuActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    protected NavigationView navigationView;
    protected DrawerLayout drawerLayout;
    protected ActionBarDrawerToggle mActionBarDrawerToggle;
    protected int selectedItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_bar_main);
        getLayoutInflater().inflate(setContentLayout(), (ViewGroup) findViewById(R.id.globalContainer));
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            mActionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0) {

                public void onDrawerClosed(View view) {
                    supportInvalidateOptionsMenu();
                    //drawerOpened = false;
                }

                public void onDrawerOpened(View drawerView) {
                    supportInvalidateOptionsMenu();
                    //drawerOpened = true;
                }
            };
            mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
            drawerLayout.addDrawerListener(mActionBarDrawerToggle);
            mActionBarDrawerToggle.syncState();
        }
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        hideManageSubMenu();
        menuListener(savedInstanceState);
    }

    protected void menuListener(final Bundle savedInstancestate) {
        if (savedInstancestate != null) {
            if (savedInstancestate.getInt(Constant.SELECTED_ITEM) != 0) {
                navigationView.setCheckedItem(savedInstancestate.getInt(Constant.SELECTED_ITEM));
            }
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                navigationView.setCheckedItem(menuItem.getItemId());
                if (menuItem.getItemId() == R.id.nav_manage) {
                    showManageSubMenu();
                    for (int i = 0; i < navigationView.getMenu().size(); i++) {
                        navigationView.getMenu().getItem(i).setChecked(false);
                    }
                } else if (menuItem.getItemId() == R.id.nav_order || menuItem.getItemId() == R.id.nav_delivery) {
                    switch (menuItem.getItemId()) {
                        case R.id.nav_order:
                            orderSelected();
                            break;
                        case R.id.nav_delivery:
                            deliverySelected();
                            break;
                    }
                    hideManageSubMenu();
                    drawerLayout.closeDrawers();

                } else {
                    manageSelected(menuItem.getItemId());
                    drawerLayout.closeDrawers();
                }
                selectedItem = menuItem.getItemId();
                return false;
            }
        });
    }

    protected abstract void orderSelected();

    protected abstract void deliverySelected();

    protected abstract void manageSelected(int itemId);
    private void hideManageSubMenu() {
        Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            if (menu.getItem(i).hasSubMenu()) {
                menu.getItem(i).getSubMenu().setGroupVisible(R.id.nav_manage_group, false);
            }
        }
    }

    private void showManageSubMenu() {
        Menu menu = navigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            if (menu.getItem(i).hasSubMenu()) {
                menu.getItem(i).getSubMenu().setGroupVisible(R.id.nav_manage_group, true);
            }
        }
    }
    public abstract int setContentLayout();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(Constant.SELECTED_ITEM, selectedItem);
        super.onSaveInstanceState(outState);
    }

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
