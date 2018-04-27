package com.example.marcinek.mobileinternshiptask.network;

import com.example.marcinek.mobileinternshiptask.BuildConfig;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubClient {


    private static final String BASE_URL = "https://api.github.com";
    private GitHubService service;

    public GitHubClient() {
        createRetrofit();
    }

    public GitHubService getService() {
        return service;
    }

    private void createRetrofit() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        Interceptor tokenInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
                        //.addHeader("Authorization", "Bearer " + prefsManager.getToken())
                        .build();
                return chain.proceed(request);
            }
        };

        clientBuilder.addInterceptor(loggingInterceptor);
        clientBuilder.addInterceptor(tokenInterceptor);
        clientBuilder.connectTimeout(1, TimeUnit.MINUTES);
        clientBuilder.writeTimeout(1, TimeUnit.MINUTES);
        clientBuilder.readTimeout(1, TimeUnit.MINUTES);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();

        service = retrofit.create(GitHubService.class);
    }
}
