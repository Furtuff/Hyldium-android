package com.tuff.hyldium.fragment_callback;

import com.tuff.hyldium.model.ItemModel;

/**
 * Created by tuffery on 15/08/17.
 */

public interface ItemList {
    void itemSearch(String typedText);

    void itemSelected(ItemModel selectedItem);

    void itemOrdered(ItemModel orderedItem);

    void onePanelUserOrder();
}
