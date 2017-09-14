package com.tuff.hyldium.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by tuffery on 22/07/17.
 */

public class ItemModel implements Serializable {
    @SerializedName("barCode")
    @Expose
    public byte[] barCode;
    @SerializedName("byBundle")
    @Expose
    public float byBundle;
    @SerializedName("date")
    @Expose
    public long date;
    @SerializedName("id")
    @Expose
    public long id;
    @SerializedName("label")
    @Expose
    public String label;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("photo")
    @Expose
    public byte[] photo;
    @SerializedName("price")
    @Expose
    public double price;
    @SerializedName("priceHT")
    @Expose
    public double priceHT;
    @SerializedName("reference")
    @Expose
    public String reference;
    @SerializedName("TVA")
    @Expose
    public float TVA;

    public float ordered;

}
