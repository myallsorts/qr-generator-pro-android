package com.allsortswebdesigners.qrgeneratorpro;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {

    private ListView notificationListView;
    private TextView emptyView;
    private List<NotificationItem> notificationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        initViews();
        setupNotificationList();
        loadNotifications();
    }

    private void initViews() {
        notificationListView = findViewById(R.id.notificationListView);
        emptyView = findViewById(R.id.emptyView);
    }

    private void setupNotificationList() {
        notificationList = new ArrayList<>();
        
        // Add some sample notifications for demonstration
        notificationList.add(new NotificationItem(
            "Site Status Update",
            "Your QR Generator Pro website is running smoothly",
            "2 minutes ago",
            "success"
        ));
        
        notificationList.add(new NotificationItem(
            "New User Registration",
            "A new user has registered on your website",
            "15 minutes ago",
            "info"
        ));
        
        notificationList.add(new NotificationItem(
            "High Activity Alert",
            "QR code generation activity is above normal",
            "1 hour ago",
            "warning"
        ));

        NotificationAdapter adapter = new NotificationAdapter(this, notificationList);
        notificationListView.setAdapter(adapter);
        
        if (notificationList.isEmpty()) {
            notificationListView.setEmptyView(emptyView);
        }
    }

    private void loadNotifications() {
        // In a real implementation, this would load notifications from the server
        Toast.makeText(this, "Loading notifications from server...", Toast.LENGTH_SHORT).show();
    }

    public static class NotificationItem {
        private String title;
        private String message;
        private String timestamp;
        private String type;

        public NotificationItem(String title, String message, String timestamp, String type) {
            this.title = title;
            this.message = message;
            this.timestamp = timestamp;
            this.type = type;
        }

        public String getTitle() { return title; }
        public String getMessage() { return message; }
        public String getTimestamp() { return timestamp; }
        public String getType() { return type; }
    }
}
