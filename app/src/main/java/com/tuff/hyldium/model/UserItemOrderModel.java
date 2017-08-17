package com.tuff.hyldium.model;

/**
 * Created by tuffery on 22/07/17.
 */

public class UserItemOrderModel extends UserItemModel {
    public long orderId;

    public UserItemOrderModel(long orderId, long userId, long itemId, float bundlePart) {
        this.orderId = orderId;
        this.userId = userId;
        this.itemId = itemId;
        this.bundlePart = bundlePart;
    }
}
