package com.tuff.hyldium.model;

import java.io.Serializable;

/**
 * Created by tuffery on 08/08/17.
 */

public class UserModel implements Serializable {

    public long id;
    public String name;
    public byte[] password;
    public byte[] photo;
    public long createdDate;

}
