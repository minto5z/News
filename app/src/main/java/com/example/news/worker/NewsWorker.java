package com.example.news.worker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.news.application.NewsApplication;
import com.example.news.repository.NewsRepository;

public class NewsWorker extends Worker {
    private NewsRepository repository = NewsApplication.repository;

    public NewsWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        repository.updateHomeNewsInBackground();
        return Result.success();
    }


}
