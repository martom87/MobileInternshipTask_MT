package com.example.marcinek.mobileinternshiptask.ui.repos_info;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.marcinek.mobileinternshiptask.R;
import com.example.marcinek.mobileinternshiptask.model.Repository;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReposDetails extends AppCompatActivity {


    @BindView(R.id.repositoryName)
    TextView name;

    @BindView(R.id.repositoryId)
    TextView id;

    @BindView(R.id.repositoryFullName)
    TextView fullName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_details);
        ButterKnife.bind(this);

        name.setText(fetchRepository().getName());
        id.setText(fetchRepository().getId());
        fullName.setText(fetchRepository().getFullName());
    }

    Repository fetchRepository() {
        Repository repository;
        Intent intent = getIntent();
        repository = (Repository) intent.getSerializableExtra(getString(R.string.extra_repo));
        return repository;
    }


}
