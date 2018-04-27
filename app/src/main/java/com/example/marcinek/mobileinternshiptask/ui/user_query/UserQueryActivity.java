package com.example.marcinek.mobileinternshiptask.ui.user_query;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.marcinek.mobileinternshiptask.App;
import com.example.marcinek.mobileinternshiptask.R;
import com.example.marcinek.mobileinternshiptask.ui.repos_list.ReposListActivity;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserQueryActivity extends AppCompatActivity implements UserQueryView {

    @BindView(R.id.typeUserName)
    EditText typeUserName;

    @BindView(R.id.query_progress_bar)
    ProgressBar progressBar;

    @OnClick(R.id.buttonDownloadRepository)
    public void onClick() {
        userQueryPresenter.setUserName(String.valueOf(typeUserName.getText()));
        showProgress();
    }

    @Inject
    UserQueryPresenter userQueryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_query);
        ((App) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);
        userQueryPresenter.attachUserQueryView(this);
    }

    @Override
    protected void onDestroy() {
        Log.e("STATE", "OnDestroy");
        userQueryPresenter.detachUserQueryView();
        super.onDestroy();
    }

    @Override
    public void showErrorMessage(int resId) {
        Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorInternetConnection(int resId) {
        Toast.makeText(getApplicationContext(), resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToRepositoryList() {
        Intent intent = new Intent(this, ReposListActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("ListOfRepos", (Serializable) userQueryPresenter.getRepositoryList());
        intent.putExtra("Bundle", args);
        startActivity(intent);
        finish();
    }

    void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }
}
