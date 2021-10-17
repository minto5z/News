package com.example.news.application;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;

import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.news.api.NewsApi;
import com.example.news.api.NewsService;
import com.example.news.data.NewsDataBase;
import com.example.news.repository.NewsRepository;
import com.example.news.worker.NewsWorker;

import java.util.concurrent.TimeUnit;

public class NewsApplication extends Application {
    public static final String BACKGROUND_UPDATE = "BACKGROUND";
    public static final String CHANNEL_NAME = "Updated News";
    public static NewsRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
        setWorker();
        createNotificationChannel();

    }

    private void createNotificationChannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(BACKGROUND_UPDATE, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

            NotificationManager manager = (NotificationManager) getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }

    }

    private void setWorker() {
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED).build();

        PeriodicWorkRequest workRequest = new PeriodicWorkRequest
                .Builder(NewsWorker.class, 2, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance(this).enqueueUniquePeriodicWork("updatingDB", ExistingPeriodicWorkPolicy.KEEP, workRequest);

    }


    private void initialize() {
        NewsService service = NewsApi.getInstance().create(NewsService.class);
        NewsDataBase dataBase = NewsDataBase.getInstance(this);
        repository = new NewsRepository(service, dataBase, this);


    }


}
