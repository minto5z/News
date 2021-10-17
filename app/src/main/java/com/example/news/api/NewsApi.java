package com.example.news.api;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsApi {
    private static final String BASE_URL = "http://newsapi.org/v2/";

    private static Retrofit retrofit;

    public static Retrofit getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
