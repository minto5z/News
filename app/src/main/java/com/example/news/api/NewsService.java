package com.example.news.api;


import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsService {

    String API_KEY = "5ed27d8f56434bd4887ec3ea6855c4ee";

    @GET("top-headlines?country=in&category=business&apiKey=" + API_KEY)
    Call<ApiResult> getBusinessNews();

    @GET("top-headlines?country=in&category=health&apiKey=" + API_KEY)
    Call<ApiResult> getHealthNews();

    @GET("top-headlines?country=in&category=general&apiKey=" + API_KEY)
    Call<ApiResult> getHomeNews();

    @GET("top-headlines?country=in&category=sports&apiKey=" + API_KEY)
    Call<ApiResult> getSportsNews();

    @GET("top-headlines?country=in&category=technology&apiKey=" + API_KEY)
    Call<ApiResult> getTechnologyNews();

    @GET("top-headlines?country=in&category=entertainment&apiKey=" + API_KEY)
    Call<ApiResult> getEntertainmentNews();

}
