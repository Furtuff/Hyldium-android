package com.tuff.hyldium.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;

import com.tuff.hyldium.R;
import com.tuff.hyldium.contract.ICache;
import com.tuff.hyldium.contract.ICacheCallBack;
import com.tuff.hyldium.contract.IComCallback;
import com.tuff.hyldium.factory.Factory;
import com.tuff.hyldium.model.ItemModel;
import com.tuff.hyldium.model.UserModel;
import com.tuff.hyldium.utils.Utils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuffery on 20/07/17.
 */

public enum CacheManager implements ICache, IComCallback {
    instance;


    private static List<UserModel> currentUsers;

    public void getItemsList(final Context context, String search) {
        final List<ItemModel> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ItemModel test = new ItemModel();
            test.name = "zregzergzregfdsdfvsdfvssfvsfv  zrgzrg" + i;
            test.reference = "testetets" + i;
            test.price = 4894.22;
            test.byBundle = 12;
            Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher_round);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            test.photo = byteArray;
            items.add(test);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((ICacheCallBack) context).askedItemList(items);
            }
        }, 2000);

    }

    @Override
    public void searchItems(Context context, String search) {
        Factory.build.getIComm().searchItem(context, search);
    }

    public List<UserModel> getCurrentUsers() {
        ArrayList<UserModel> list = new ArrayList<>();
        list.add(new UserModel());
        list.add(new UserModel());
        UserModel caca = new UserModel();
        caca.firstName = "yamina";
        caca.lastName = "caca";
        list.add(caca);
        currentUsers = list;
        return currentUsers;
    }

    public void setCurrentUsers(List<UserModel> users) {
        this.currentUsers = users;
    }

    public void searchResult(List<ItemModel> items) {
        ((ICacheCallBack) Utils.getActivity()).askedItemList(items);
        ItemModel popo = items.get(0);
    }

}
