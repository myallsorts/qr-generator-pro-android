package com.allsortswebdesigners.qrgeneratorpro.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.allsortswebdesigners.qrgeneratorpro.MainActivity;
import com.allsortswebdesigners.qrgeneratorpro.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NotificationPollingService extends Service {

    private static final String TAG = "NotificationService";
    private static final String CHANNEL_ID = "QRGeneratorProChannel";
    private static final int NOTIFICATION_ID = 1;
    private static final int POLLING_INTERVAL = 30000; // 30 seconds

    private Handler handler;
    private Runnable pollingRunnable;
    private boolean isRunning = false;

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(NOTIFICATION_ID, createNotification("Monitoring QR Generator Pro..."));
        startPolling();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        stopPolling();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "QR Generator Pro Notifications",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Notifications for QR Generator Pro website monitoring");
            
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private Notification createNotification(String content) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("QR Generator Pro")
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .build();
    }

    private void startPolling() {
        if (isRunning) return;
        
        isRunning = true;
        pollingRunnable = new Runnable() {
            @Override
            public void run() {
                checkServerStatus();
                if (isRunning) {
                    handler.postDelayed(this, POLLING_INTERVAL);
                }
            }
        };
        handler.post(pollingRunnable);
    }

    private void stopPolling() {
        isRunning = false;
        if (pollingRunnable != null) {
            handler.removeCallbacks(pollingRunnable);
        }
    }

    private void checkServerStatus() {
        new Thread(() -> {
            try {
                // Check main site
                boolean siteUp = checkUrl("https://qrcodegen.allsortswebdesigners.co.za/");
                
                // Check API endpoints
                boolean apiUp = checkUrl("https://qrcodegen.allsortswebdesigners.co.za/api/status");
                
                // Check for new notifications
                String notifications = getNotifications();
                
                // Update notification based on status
                String status = siteUp && apiUp ? "✅ All systems operational" : "❌ Issues detected";
                updateNotification(status);
                
                // Show notification if there are new items
                if (!notifications.isEmpty()) {
                    showNotification("New Activity", notifications);
                }
                
                Log.d(TAG, "Server check completed - Site: " + siteUp + ", API: " + apiUp);
                
            } catch (Exception e) {
                Log.e(TAG, "Error checking server status", e);
                updateNotification("❌ Connection error");
            }
        }).start();
    }

    private boolean checkUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            
            return responseCode == 200;
        } catch (Exception e) {
            Log.e(TAG, "Error checking URL: " + urlString, e);
            return false;
        }
    }

    private String getNotifications() {
        try {
            URL url = new URL("https://qrcodegen.allsortswebdesigners.co.za/api/notifications");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            
            if (connection.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                connection.disconnect();
                
                return response.toString();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting notifications", e);
        }
        return "";
    }

    private void updateNotification(String content) {
        Notification notification = createNotification(content);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, notification);
    }

    private void showNotification(String title, String content) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build();

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify((int) System.currentTimeMillis(), notification);
    }
}
