package com.tuff.hyldium.contract;

import com.tuff.hyldium.model.ItemModel;

import java.util.List;

/**
 * Created by tuffery on 14/09/17.
 */

public interface IComCallback {
    void searchResult(List<ItemModel> items);
}
