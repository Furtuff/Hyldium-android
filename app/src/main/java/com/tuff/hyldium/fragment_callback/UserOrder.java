package com.tuff.hyldium.fragment_callback;

import com.tuff.hyldium.model.ItemModel;

/**
 * Created by tuffery on 25/08/17.
 */

public interface UserOrder {
    void editOrdered(ItemModel itemOrdered);

    void deleteOrdered(ItemModel itemKilled);
}
