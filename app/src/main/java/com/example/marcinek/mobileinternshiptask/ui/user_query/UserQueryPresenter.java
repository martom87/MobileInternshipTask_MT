package com.example.marcinek.mobileinternshiptask.ui.user_query;

import com.example.marcinek.mobileinternshiptask.R;
import com.example.marcinek.mobileinternshiptask.model.Repository;
import com.example.marcinek.mobileinternshiptask.network.GitHubClient;


import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserQueryPresenter {

    private GitHubClient gitHubClient;
    private UserQueryView userQueryView;

    List<Repository> repositories;

    public List<Repository> getRepositoryList() {
        return repositories;
    }

    @Inject
    public UserQueryPresenter(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;

    }

    public void attachUserQueryView(UserQueryView userQueryView) {
        this.userQueryView = userQueryView;
    }

    public void detachUserQueryView() {
        this.userQueryView = null;
    }

    public void setUserName(String userName) {
        getRepositoryList(userName);
    }

    public void getRepositoryList(String userName) {
        Call<List<Repository>> call = gitHubClient.getService().getRepositoryData(userName);
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if (response.isSuccessful()) {
                    repositories = response.body();
                    userQueryView.goToRepositoryList();
                } else {
                    userQueryView.showErrorMessage(R.string.errorMessage);
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                userQueryView.showErrorInternetConnection(R.string.internetConnectionFailure);
            }
        });
    }

}
