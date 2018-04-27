package com.example.marcinek.mobileinternshiptask.network;

import com.example.marcinek.mobileinternshiptask.model.Repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {

    @GET("/users/{user}/repos")
    Call<List<Repository>> getRepositoryData(@Path("user") String userName);
}
