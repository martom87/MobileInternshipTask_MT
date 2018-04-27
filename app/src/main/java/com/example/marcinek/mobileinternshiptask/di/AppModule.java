package com.example.marcinek.mobileinternshiptask.di;

import android.content.Context;

import com.example.marcinek.mobileinternshiptask.network.GitHubClient;

import javax.inject.Singleton;

import dagger.Provides;

@dagger.Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context providesContext() {
        return context;
    }

    @Provides
    @Singleton
    GitHubClient providesGitHubClient() {
        return new GitHubClient();
    }


}
