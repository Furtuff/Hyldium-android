package com.tuff.hyldium.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tuffery on 14/09/17.
 */

public class ItemListDataModel implements Serializable {
    @SerializedName(" ")
    public List<ItemModel> userModelList;
}
