package com.example.news.notification;

import static com.example.news.application.NewsApplication.BACKGROUND_UPDATE;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.news.R;
import com.example.news.data.News;

import java.util.List;

public class NewsNotification {

    public static void createNotification(Context context, List<News> news, PendingIntent intent) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        NotificationCompat.Builder notification1 = new NotificationCompat.Builder(context, BACKGROUND_UPDATE)
                .setAutoCancel(true)
                .setContentText(news.get(0).getTitle())
                .setColor(Color.BLUE)
                .setContentIntent(intent)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .setBigContentTitle(news.get(0).getTitle())
                        .bigText(news.get(0).getDescription())
                        .setSummaryText("Home"))
                .setContentTitle("Home")
                .setSmallIcon(R.drawable.ic_notification);

        NotificationCompat.Builder notification2 = new NotificationCompat.Builder(context, BACKGROUND_UPDATE)
                .setAutoCancel(true)
                .setContentText(news.get(1).getTitle())
                .setColor(Color.BLUE)
                .setContentIntent(intent)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .setBigContentTitle(news.get(1).getTitle())
                        .bigText(news.get(1).getDescription())
                        .setSummaryText("Home"))
                .setContentTitle("Home")
                .setSmallIcon(R.drawable.ic_notification);

        NotificationCompat.Builder notification3 = new NotificationCompat.Builder(context, BACKGROUND_UPDATE)
                .setAutoCancel(true)
                .setContentText(news.get(2).getTitle())
                .setColor(Color.BLUE)
                .setContentIntent(intent)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .setBigContentTitle(news.get(2).getTitle())
                        .bigText(news.get(2).getDescription())
                        .setSummaryText("Home"))
                .setContentTitle("Home")
                .setSmallIcon(R.drawable.ic_notification);

        notificationManager.notify(11, notification1.build());
        notificationManager.notify(22, notification2.build());
        notificationManager.notify(33, notification3.build());

    }


}


