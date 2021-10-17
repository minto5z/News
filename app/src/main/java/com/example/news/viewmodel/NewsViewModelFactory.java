package com.example.news.viewmodel;


import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.news.repository.NewsRepository;


public class NewsViewModelFactory implements ViewModelProvider.Factory {

    private NewsRepository repository;

    public NewsViewModelFactory(NewsRepository repository) {
        this.repository = repository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {


        try {

            if (modelClass.isAssignableFrom(HomeViewModel.class)) {
                return (T) new HomeViewModel(repository);
            } else if (modelClass.isAssignableFrom(BusinessViewModel.class)) {
                return (T) new BusinessViewModel(repository);
            } else if (modelClass.isAssignableFrom(EntertainmentViewModel.class)) {
                return (T) new EntertainmentViewModel(repository);
            } else if (modelClass.isAssignableFrom(HealthViewModel.class)) {
                return (T) new HealthViewModel(repository);
            } else if (modelClass.isAssignableFrom(SportsViewModel.class)) {
                return (T) new SportsViewModel(repository);
            } else if (modelClass.isAssignableFrom(TechnologyViewModel.class)) {
                return (T) new TechnologyViewModel(repository);
            }

            return null;
        } catch (Exception e) {
            throw new RuntimeException("Cannot create an instance of $modelClass", e);
        }


    }
}
