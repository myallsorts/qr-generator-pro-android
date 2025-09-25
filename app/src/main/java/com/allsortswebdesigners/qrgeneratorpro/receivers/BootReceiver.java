package com.allsortswebdesigners.qrgeneratorpro.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.allsortswebdesigners.qrgeneratorpro.services.NotificationPollingService;

public class BootReceiver extends BroadcastReceiver {

    private static final String TAG = "BootReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()) ||
            "android.intent.action.QUICKBOOT_POWERON".equals(intent.getAction())) {
            
            Log.d(TAG, "Device booted, starting notification service");
            
            // Start the notification service
            Intent serviceIntent = new Intent(context, NotificationPollingService.class);
            context.startService(serviceIntent);
        }
    }
}
