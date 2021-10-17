package com.example.news.repository;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.news.MainActivity;
import com.example.news.api.ApiResult;
import com.example.news.api.NewsService;
import com.example.news.data.News;
import com.example.news.data.NewsDataBase;
import com.example.news.notification.NewsNotification;
import com.example.news.response.Response;
import com.example.news.utilities.NetworkUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class NewsRepository {

    private static final MutableLiveData<Response<List<News>>> mutableLiveDataSports = new MutableLiveData<>();
    private static final MutableLiveData<Response<List<News>>> mutableLiveDataEntertainment = new MutableLiveData<>();
    private static final MutableLiveData<Response<List<News>>> mutableLiveDataBusiness = new MutableLiveData<>();
    private static final MutableLiveData<Response<List<News>>> mutableLiveDataTechnology = new MutableLiveData<>();
    private static final MutableLiveData<Response<List<News>>> mutableLiveDataHealth = new MutableLiveData<>();
    private static final MutableLiveData<Response<List<News>>> mutableLiveDataHome = new MutableLiveData<>();
    private static final String HOME_NEWS = "General";
    private static final String SPORTS_NEWS = "Sports";
    private static final String TECHNOLOGY_NEWS = "Technology";
    private static final String HEALTH_NEWS = "Health";
    private static final String ENTERTAINMENT_NEWS = "Entertainment";
    private static final String BUSINESS_NEWS = "Business";
    private final NewsService service;
    private final NewsDataBase dataBase;
    private final Context context;
    public LiveData<Response<List<News>>> sportsLiveData = mutableLiveDataSports;
    public LiveData<Response<List<News>>> entertainmentLiveData = mutableLiveDataEntertainment;
    public LiveData<Response<List<News>>> businessLiveData = mutableLiveDataBusiness;
    public LiveData<Response<List<News>>> technologyLiveData = mutableLiveDataTechnology;
    public LiveData<Response<List<News>>> healthLiveData = mutableLiveDataHealth;
    public LiveData<Response<List<News>>> homeLiveData = mutableLiveDataHome;

    public NewsRepository(NewsService service, NewsDataBase dataBase, Context context) {
        this.service = service;
        this.dataBase = dataBase;
        this.context = context;
    }


    public void getHomeNews() {
        if (NetworkUtils.isNetworkConnected(context)) {
            mutableLiveDataHome.postValue(Response.loading(null));
            Call<ApiResult> resultCall = service.getHomeNews();
            resultCall.enqueue(new Callback<ApiResult>() {
                @Override
                public void onResponse(Call<ApiResult> call, retrofit2.Response<ApiResult> response) {
                    List<News> news = response.body().getArticles();
                    for (int i = 0; i < news.size(); i++) {
                        news.get(i).setCategory(NewsRepository.HOME_NEWS);
                    }
                    mutableLiveDataHome.postValue(Response.success(news));
                    new InsertAsync(dataBase, news, HOME_NEWS).execute();

                }

                @Override
                public void onFailure(Call<ApiResult> call, Throwable t) {

                    mutableLiveDataHome.postValue(Response.error(t.getMessage(), null));
                }
            });
        } else {
            new GetAsync(dataBase, HOME_NEWS).execute();
        }

    }

    public void getSportsNews() {
        if (NetworkUtils.isNetworkConnected(context)) {
            mutableLiveDataSports.postValue(Response.loading(null));
            Call<ApiResult> result = service.getSportsNews();
            result.enqueue(new Callback<ApiResult>() {
                @Override
                public void onResponse(Call<ApiResult> call, retrofit2.Response<ApiResult> response) {
                    List<News> news = response.body().getArticles();
                    for (int i = 0; i < news.size(); i++) {
                        news.get(i).setCategory(SPORTS_NEWS);
                    }
                    mutableLiveDataSports.postValue(Response.success(news));
                    new InsertAsync(dataBase, news, SPORTS_NEWS).execute();
                }

                @Override
                public void onFailure(Call<ApiResult> call, Throwable t) {
                    mutableLiveDataSports.postValue(Response.error(t.getMessage(), null));
                }
            });
        } else {
            new GetAsync(dataBase, SPORTS_NEWS).execute();
        }

    }

    public void getEntertainmentNews() {
        if (NetworkUtils.isNetworkConnected(context)) {
            mutableLiveDataEntertainment.postValue(Response.loading(null));
            Call<ApiResult> result = service.getEntertainmentNews();
            result.enqueue(new Callback<ApiResult>() {
                @Override
                public void onResponse(Call<ApiResult> call, retrofit2.Response<ApiResult> response) {
                    List<News> news = response.body().getArticles();
                    for (int i = 0; i < news.size(); i++) {
                        news.get(i).setCategory(ENTERTAINMENT_NEWS);
                    }
                    mutableLiveDataEntertainment.postValue(Response.success(news));
                    new InsertAsync(dataBase, news, ENTERTAINMENT_NEWS).execute();
                }

                @Override
                public void onFailure(Call<ApiResult> call, Throwable t) {
                    mutableLiveDataEntertainment.postValue(Response.error(t.getMessage(), null));
                }
            });
        } else {
            new GetAsync(dataBase, ENTERTAINMENT_NEWS).execute();
        }

    }

    public void getHealthNews() {
        if (NetworkUtils.isNetworkConnected(context)) {
            mutableLiveDataHealth.postValue(Response.loading(null));
            Call<ApiResult> result = service.getHealthNews();
            result.enqueue(new Callback<ApiResult>() {
                @Override
                public void onResponse(Call<ApiResult> call, retrofit2.Response<ApiResult> response) {
                    List<News> news = response.body().getArticles();
                    for (int i = 0; i < news.size(); i++) {
                        news.get(i).setCategory(HEALTH_NEWS);
                    }
                    mutableLiveDataHealth.postValue(Response.success(news));
                    new InsertAsync(dataBase, news, HEALTH_NEWS).execute();
                }

                @Override
                public void onFailure(Call<ApiResult> call, Throwable t) {
                    mutableLiveDataHealth.postValue(Response.error(t.getMessage(), null));
                }
            });
        } else {
            new GetAsync(dataBase, HEALTH_NEWS).execute();
        }


    }

    public void getBusinessNews() {
        if (NetworkUtils.isNetworkConnected(context)) {
            mutableLiveDataBusiness.postValue(Response.loading(null));
            Call<ApiResult> result = service.getBusinessNews();
            result.enqueue(new Callback<ApiResult>() {
                @Override
                public void onResponse(Call<ApiResult> call, retrofit2.Response<ApiResult> response) {
                    if (response != null && response.body() != null) {
                        List<News> news = response.body().getArticles();
                        for (int i = 0; i < news.size(); i++) {
                            news.get(i).setCategory(BUSINESS_NEWS);
                        }
                        mutableLiveDataBusiness.postValue(Response.success(news));
                        new InsertAsync(dataBase, news, BUSINESS_NEWS).execute();
                    }
                }

                @Override
                public void onFailure(Call<ApiResult> call, Throwable t) {
                    mutableLiveDataBusiness.postValue(Response.error(t.getMessage(), null));
                }
            });
        } else {
            new GetAsync(dataBase, BUSINESS_NEWS).execute();
        }

    }

    public void getTechnologyNews() {
        if (NetworkUtils.isNetworkConnected(context)) {
            mutableLiveDataTechnology.postValue(Response.loading(null));
            Call<ApiResult> result = service.getTechnologyNews();
            result.enqueue(new Callback<ApiResult>() {
                @Override
                public void onResponse(Call<ApiResult> call, retrofit2.Response<ApiResult> response) {
                    List<News> news = response.body().getArticles();
                    for (int i = 0; i < news.size(); i++) {
                        news.get(i).setCategory(TECHNOLOGY_NEWS);
                    }
                    mutableLiveDataTechnology.postValue(Response.success(news));
                    new InsertAsync(dataBase, news, TECHNOLOGY_NEWS).execute();
                }

                @Override
                public void onFailure(Call<ApiResult> call, Throwable t) {
                    mutableLiveDataTechnology.postValue(Response.error(t.getMessage(), null));
                }
            });

        } else {
            new GetAsync(dataBase, TECHNOLOGY_NEWS).execute();
        }

    }

    public void updateHomeNewsInBackground() {
        Call<ApiResult> resultCall = service.getHomeNews();
        resultCall.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, retrofit2.Response<ApiResult> response) {
                List<News> news = response.body().getArticles();
                for (int i = 0; i < news.size(); i++) {
                    news.get(i).setCategory(NewsRepository.HOME_NEWS);
                }
                Intent intent = new Intent(context, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                NewsNotification.createNotification(context, news, pendingIntent);
                new InsertAsync(dataBase, news, HOME_NEWS).execute();
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {

            }
        });
    }


    private static class InsertAsync extends AsyncTask<Void, Void, Void> {
        NewsDataBase dataBase;
        List<News> news;
        String newsCategory;

        public InsertAsync(NewsDataBase dataBase, List<News> news, String newsCategory) {
            this.dataBase = dataBase;
            this.news = news;
            this.newsCategory = newsCategory;

        }

        @Override
        protected Void doInBackground(Void... voids) {
            switch (newsCategory) {
                case HOME_NEWS:
                    List<News> listG = dataBase.newsDao().getAllHome();
                    if (listG.size() != 0) {
                        dataBase.newsDao().deleteAllHome();
                    }
                    dataBase.newsDao().insertAllNews(news);
                    break;
                case SPORTS_NEWS:
                    List<News> lists = dataBase.newsDao().getAllSports();
                    if (lists.size() != 0) {
                        dataBase.newsDao().deleteAllSports();
                    }
                    dataBase.newsDao().insertAllNews(news);
                    break;
                case HEALTH_NEWS:
                    List<News> listH = dataBase.newsDao().getAllSports();
                    if (listH.size() != 0) {
                        dataBase.newsDao().deleteAllHealth();
                    }
                    dataBase.newsDao().insertAllNews(news);
                    break;
                case BUSINESS_NEWS:
                    List<News> listB = dataBase.newsDao().getAllSports();
                    if (listB.size() != 0) {
                        dataBase.newsDao().deleteAllBusiness();
                    }
                    dataBase.newsDao().insertAllNews(news);
                    break;
                case TECHNOLOGY_NEWS:
                    List<News> listT = dataBase.newsDao().getAllSports();
                    if (listT.size() != 0) {
                        dataBase.newsDao().deleteAllTechnology();
                    }
                    dataBase.newsDao().insertAllNews(news);
                    break;
                case ENTERTAINMENT_NEWS:
                    List<News> listE = dataBase.newsDao().getAllSports();
                    if (listE.size() != 0) {
                        dataBase.newsDao().deleteAllEntertainment();
                    }
                    dataBase.newsDao().insertAllNews(news);
                    break;
                default:
                    return null;
            }

            return null;
        }
    }


    private static class GetAsync extends AsyncTask<Void, Void, Void> {
        private NewsDataBase dataBase;
        private String newsCategory;

        public GetAsync(NewsDataBase dataBase, String newsCategory) {
            this.dataBase = dataBase;
            this.newsCategory = newsCategory;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            switch (newsCategory) {
                case HOME_NEWS:
                    mutableLiveDataHome.postValue(Response.success(dataBase.newsDao().getAllHome()));
                    break;
                case SPORTS_NEWS:
                    mutableLiveDataSports.postValue(Response.success(dataBase.newsDao().getAllSports()));
                    break;
                case HEALTH_NEWS:
                    mutableLiveDataHealth.postValue(Response.success(dataBase.newsDao().getAllHealth()));
                    break;
                case BUSINESS_NEWS:
                    mutableLiveDataBusiness.postValue(Response.success(dataBase.newsDao().getAllBusiness()));
                    break;
                case TECHNOLOGY_NEWS:
                    mutableLiveDataTechnology.postValue(Response.success(dataBase.newsDao().getAllTechnology()));
                    break;
                case ENTERTAINMENT_NEWS:
                    mutableLiveDataEntertainment.postValue(Response.success(dataBase.newsDao().getAllEntertainment()));
                    break;
                default:
                    return null;
            }

            return null;
        }
    }


}
