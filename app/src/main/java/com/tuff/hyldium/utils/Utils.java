package com.tuff.hyldium.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tuffery on 08/08/17.
 */

public class Utils {
    public static String dateMillisToString(long date) {
        return new SimpleDateFormat(Constant.DATE_FORMAT).format(new Date(date));
    }
}
