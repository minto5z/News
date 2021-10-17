package com.example.news.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.news.data.News;
import com.example.news.repository.NewsRepository;
import com.example.news.response.Response;

import java.util.List;

public class EntertainmentViewModel extends ViewModel {
    public LiveData<Response<List<News>>> entertainmentLiveData;

    public EntertainmentViewModel(NewsRepository repository) {
        repository.getEntertainmentNews();
        entertainmentLiveData = repository.entertainmentLiveData;
    }
}
