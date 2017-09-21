package com.tuff.hyldium.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * Created by tuffery on 17/09/17.
 */

public class APIResultReceive extends ResultReceiver {

    public final static int API_START = 1;
    public final static int API_END = 2;
    public final static int API_SUCCESS = 3;
    public final static int API_FAIL = 4;
    private final static String TAG = "APIResultReceive";
    private Receive receive = null;

    public APIResultReceive(Handler handler) {
        super(handler);
    }

    public void setReceive(Receive receive) {
        this.receive = receive;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);
        if (receive != null) {
            receive.onReceiveResult(resultCode, resultData);
        }
    }

    public interface Receive {

        public void onReceiveResult(int resultCode, Bundle resultData);
    }
}