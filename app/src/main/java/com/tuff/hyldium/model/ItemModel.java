package com.tuff.hyldium.model;

import java.io.Serializable;

/**
 * Created by tuffery on 22/07/17.
 */

public class ItemModel implements Serializable {
    public long id;
    public long date;
    public String reference;
    public String name;
    public double price;
    public double priceHT;
    public float byBundle;
    public float TVA;
    public String label;
    public byte[] photo;
    public float ordered;
    public String barCode;
}
