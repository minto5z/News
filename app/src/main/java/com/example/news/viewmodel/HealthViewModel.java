package com.example.news.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.news.data.News;
import com.example.news.repository.NewsRepository;
import com.example.news.response.Response;

import java.util.List;

public class HealthViewModel extends ViewModel {
    public LiveData<Response<List<News>>> healthLiveData;

    public HealthViewModel(NewsRepository repository) {
        repository.getHealthNews();
        healthLiveData = repository.healthLiveData;
    }

}
