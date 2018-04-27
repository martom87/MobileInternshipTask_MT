package com.example.marcinek.mobileinternshiptask.di;


import com.example.marcinek.mobileinternshiptask.ui.user_query.UserQueryActivity;

import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = {AppModule.class})

public interface AppComponent {

    void inject(UserQueryActivity userQueryActivity);

}
