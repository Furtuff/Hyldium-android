package com.tuff.hyldium.model;

import java.io.Serializable;

/**
 * Created by tuffery on 08/08/17.
 */

public class UserModel implements Serializable {

    public long id;
    public String firstName;
    public String lastName;
    public byte[] password;
    public byte[] photo;
    public long createdDate;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (firstName != null) {
            sb.append(firstName);
        }
        sb.append(" ");
        if (lastName != null) {
            sb.append(lastName);
        }
        return sb.toString();
    }
}
