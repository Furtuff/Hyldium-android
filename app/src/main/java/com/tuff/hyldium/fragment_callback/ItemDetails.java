package com.tuff.hyldium.fragment_callback;

import com.tuff.hyldium.model.ItemModel;

/**
 * Created by tuffery on 17/08/17.
 */

public interface ItemDetails {
    void editItem(ItemModel editedItem);

    void orderUserItem(ItemModel orderedItem);
}
