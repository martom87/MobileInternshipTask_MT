package com.example.marcinek.mobileinternshiptask.ui.repos_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.marcinek.mobileinternshiptask.App;
import com.example.marcinek.mobileinternshiptask.R;
import com.example.marcinek.mobileinternshiptask.model.Repository;
import com.example.marcinek.mobileinternshiptask.ui.repos_info.ReposDetails;
import com.example.marcinek.mobileinternshiptask.ui.repos_list.adapter.ReposAdapter;
import com.example.marcinek.mobileinternshiptask.ui.user_query.UserQueryActivity;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ReposListActivity extends AppCompatActivity implements ReposAdapter.ReposClickListener {

    @BindView(R.id.repositories_recycler)
    RecyclerView reposRecycler;

    private ReposAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_list);
        ButterKnife.bind(this);
        init();
    }

    private void init() {

        adapter = new ReposAdapter(this);
        adapter.setData(fetchRepositories());
        reposRecycler.setLayoutManager(new LinearLayoutManager(this));
        reposRecycler.setAdapter(adapter);

    }

    ArrayList<Repository> fetchRepositories() {
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("Bundle");
        ArrayList<Repository> repositories = (ArrayList<Repository>) args.getSerializable("ListOfRepos");
        return repositories;
    }


    @Override
    public void onRepositoryClick(Repository repository) {
        Toast.makeText(this, repository.getName(), Toast.LENGTH_SHORT).show();
        openDetailView(repository);
    }


    private void openDetailView(@Nullable Repository repository) {
        Intent intent = new Intent(this, ReposDetails.class);
        intent.putExtra(getString(R.string.extra_repo), repository);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, UserQueryActivity.class);
        startActivity(intent);
        finish();

    }
}

