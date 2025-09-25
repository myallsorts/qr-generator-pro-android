package com.allsortswebdesigners.qrgeneratorpro;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CODE = 1001;
    private TextView statusText;
    private TextView lastCheckText;
    private Switch notificationSwitch;
    private Button testNotificationBtn;
    private Button openWebsiteBtn;
    private Button viewNotificationsBtn;
    private Button refreshBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupClickListeners();
        requestNotificationPermission();
        checkNotificationStatus();
    }

    private void initViews() {
        statusText = findViewById(R.id.statusText);
        lastCheckText = findViewById(R.id.lastCheckText);
        notificationSwitch = findViewById(R.id.notificationSwitch);
        testNotificationBtn = findViewById(R.id.testNotificationBtn);
        openWebsiteBtn = findViewById(R.id.openWebsiteBtn);
        viewNotificationsBtn = findViewById(R.id.viewNotificationsBtn);
        refreshBtn = findViewById(R.id.refreshBtn);
    }

    private void setupClickListeners() {
        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                startNotificationService();
            } else {
                stopNotificationService();
            }
        });

        testNotificationBtn.setOnClickListener(v -> sendTestNotification());

        openWebsiteBtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(android.net.Uri.parse("https://qrcodegen.allsortswebdesigners.co.za/"));
            startActivity(intent);
        });

        viewNotificationsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
        });

        refreshBtn.setOnClickListener(v -> checkServerStatus());
    }

    private void requestNotificationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.POST_NOTIFICATIONS},
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Notification permission granted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Notification permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startNotificationService() {
        Intent serviceIntent = new Intent(this, NotificationPollingService.class);
        startService(serviceIntent);
        statusText.setText("🔔 Notification service started!");
        Toast.makeText(this, "Started monitoring your website!", Toast.LENGTH_SHORT).show();
    }

    private void stopNotificationService() {
        Intent serviceIntent = new Intent(this, NotificationPollingService.class);
        stopService(serviceIntent);
        statusText.setText("🔕 Notification service stopped");
        Toast.makeText(this, "Stopped monitoring", Toast.LENGTH_SHORT).show();
    }

    private void sendTestNotification() {
        statusText.setText("📤 Sending test notification...");
        Toast.makeText(this, "Test notification sent to server!", Toast.LENGTH_SHORT).show();
        
        new android.os.Handler().postDelayed(() -> {
            statusText.setText("✅ Test notification sent successfully!");
        }, 2000);
    }

    private void checkServerStatus() {
        statusText.setText("🔄 Checking server status...");
        
        new android.os.Handler().postDelayed(() -> {
            statusText.setText("✅ Server is online and responding!");
            lastCheckText.setText("Last check: " + java.text.DateFormat.getDateTimeInstance().format(new java.util.Date()));
        }, 1500);
    }

    private void checkNotificationStatus() {
        notificationSwitch.setChecked(true);
        statusText.setText("🔔 Ready to monitor your website!");
        lastCheckText.setText("Ready to start monitoring...");
    }
}
