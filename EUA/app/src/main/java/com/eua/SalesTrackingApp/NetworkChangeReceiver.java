package com.eua.SalesTrackingApp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

/**
 * Created by unobtainium on 21/02/16.
 */
public class NetworkChangeReceiver extends BroadcastReceiver{
    public boolean connected;
    @Override
    public void onReceive(Context context, Intent intent) {
        final ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connMgr.getActiveNetworkInfo() != null){
            connected = true;
            Log.e("Network", "Connected");
        }else{
            connected = false;
            Log.e("Network", "Not Connected");
        }
    }
}
