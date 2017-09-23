package com.tuff.hyldium.contract;

import android.content.Context;

import com.tuff.hyldium.model.ItemModel;
import com.tuff.hyldium.model.UserModel;

import java.util.List;

/**
 * Created by tuffery on 20/07/17.
 */

public interface ICache {
    List<UserModel> getCurrentUsers();

    void setCurrentUsers(List<UserModel> currentUsers);

    void getItemsList(Context context, String search);

    void searchItems(Context context, String search);

    List<ItemModel> getCurrentOrder();

    void updateOrder(ItemModel itemModel);

}
