package com.allsortswebdesigners.qrgeneratorpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NotificationAdapter extends BaseAdapter {

    private Context context;
    private List<NotificationActivity.NotificationItem> notifications;
    private LayoutInflater inflater;

    public NotificationAdapter(Context context, List<NotificationActivity.NotificationItem> notifications) {
        this.context = context;
        this.notifications = notifications;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int position) {
        return notifications.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_notification, parent, false);
            holder = new ViewHolder();
            holder.titleText = convertView.findViewById(R.id.titleText);
            holder.messageText = convertView.findViewById(R.id.messageText);
            holder.timestampText = convertView.findViewById(R.id.timestampText);
            holder.typeIndicator = convertView.findViewById(R.id.typeIndicator);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        NotificationActivity.NotificationItem notification = notifications.get(position);
        
        holder.titleText.setText(notification.getTitle());
        holder.messageText.setText(notification.getMessage());
        holder.timestampText.setText(notification.getTimestamp());
        
        // Set type indicator color
        int color = getTypeColor(notification.getType());
        holder.typeIndicator.setBackgroundColor(color);

        return convertView;
    }

    private int getTypeColor(String type) {
        switch (type) {
            case "success":
                return context.getResources().getColor(android.R.color.holo_green_light);
            case "warning":
                return context.getResources().getColor(android.R.color.holo_orange_light);
            case "error":
                return context.getResources().getColor(android.R.color.holo_red_light);
            default:
                return context.getResources().getColor(android.R.color.holo_blue_light);
        }
    }

    static class ViewHolder {
        TextView titleText;
        TextView messageText;
        TextView timestampText;
        View typeIndicator;
    }
}
