package com.example.news.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.news.data.News;
import com.example.news.repository.NewsRepository;
import com.example.news.response.Response;

import java.util.List;

public class BusinessViewModel extends ViewModel {

    public LiveData<Response<List<News>>> businessLiveData;

    public BusinessViewModel(NewsRepository repository) {
        repository.getBusinessNews();
        businessLiveData = repository.businessLiveData;
    }
}
