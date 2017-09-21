package com.tuff.hyldium.utils;

import android.app.Activity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by tuffery on 08/08/17.
 */

public class Utils {
    public static String dateMillisToString(long date) {
        return new SimpleDateFormat(Constant.DATE_FORMAT).format(new Date(date));
    }

    public static Activity getActivity() {
        Class activityThreadClass = null;
        Field activitiesField = null;
        Map<Object, Object> activities = null;

        try {
            activityThreadClass = Class.forName("android.app.ActivityThread");

            Object activityThread = null;

            activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);


            activitiesField = activityThreadClass.getDeclaredField("mActivities");

            activitiesField.setAccessible(true);

            activities = (Map<Object, Object>) activitiesField.get(activityThread);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchFieldException | ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (activities == null)
            return null;
        try {
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
