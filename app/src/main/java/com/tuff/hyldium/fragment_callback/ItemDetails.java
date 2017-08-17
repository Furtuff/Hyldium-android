package com.tuff.hyldium.fragment_callback;

import com.tuff.hyldium.model.ItemModel;
import com.tuff.hyldium.model.UserItemOrderModel;

/**
 * Created by tuffery on 17/08/17.
 */

public interface ItemDetails {
    void editItem(ItemModel editedItem);

    void orderUserItem(UserItemOrderModel orderedItem);
}
