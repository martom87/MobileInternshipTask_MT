package com.example.marcinek.mobileinternshiptask;

import android.app.Application;

import com.example.marcinek.mobileinternshiptask.di.AppComponent;
import com.example.marcinek.mobileinternshiptask.di.AppModule;
import com.example.marcinek.mobileinternshiptask.di.DaggerAppComponent;

import dagger.Module;

@Module
public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    private void initDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }


}
