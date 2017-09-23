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
import java.util.ListIterator;

/**
 * Created by tuffery on 20/07/17.
 */

public enum CacheManager implements ICache, IComCallback {
    instance;


    private List<UserModel> currentUsers;
    private List<ItemModel> currentOrder;

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
        }, 100);

    }

    @Override
    public void searchItems(Context context, String search) {
        Factory.build.getIComm().searchItem(context, search);
    }

    public List<UserModel> getCurrentUsers() {
        ArrayList<UserModel> list = new ArrayList<>();
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
        try {
            ((ICacheCallBack) Utils.getActivity()).askedItemList(items);

        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ItemModel> getCurrentOrder() {
        return currentOrder;
    }

    @Override
    public void updateOrder(ItemModel itemModel) {
        if (currentOrder != null) {
            ListIterator<ItemModel> i = currentOrder.listIterator();
            while (i.hasNext()) {
                ItemModel itemFromOrder = i.next();
                if (itemFromOrder.equals(itemModel)) {
                    if (itemModel.ordered <= 0.0) {
                        i.remove();
                    } else {
                        i.set(itemModel);
                    }
                } else if (itemModel.ordered != 0.0) {

                    currentOrder.add(itemModel);
                }
            }
        } else {
            currentOrder = new ArrayList<>();
            if (itemModel.ordered != 0) {
                currentOrder.add(itemModel);
            }
        }

    }

}
