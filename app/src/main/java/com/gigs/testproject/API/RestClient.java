package com.gigs.testproject.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {

    private static API service;

    public static API getService() {

        if (service == null) {
            String BASE_URL = "http://18.139.50.74:8080/";

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.interceptors().add(interceptor);

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson));

            Retrofit retrofit = builder.client(httpClient.build()).build();

            service = retrofit.create(API.class);
        }
        return service;
    }
}
