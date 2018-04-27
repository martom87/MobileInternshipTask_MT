package com.example.marcinek.mobileinternshiptask.ui.repos_list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marcinek.mobileinternshiptask.R;
import com.example.marcinek.mobileinternshiptask.model.Repository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ReposHolder> {

    private List<Repository> repositories = new ArrayList<>();
    private ReposClickListener listener;

    public ReposAdapter(ReposClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<Repository> repositoryList) {
        repositories.clear();
        repositories.addAll(repositoryList);
        notifyDataSetChanged();
    }

    @Override
    public ReposHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_adapter, parent, false);
        return new ReposHolder(view);
    }

    @Override
    public void onBindViewHolder(ReposHolder holder, int position) {
        holder.setRepository(repositories.get(position));
    }

    @Override
    public int getItemCount() {
        return repositories.size();
    }

    class ReposHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        private Repository repository;

        public ReposHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onRepositoryClick(repository);
                }
            });
        }

        public void setRepository(Repository repository) {
            this.repository = repository;
            name.setText(repository.getName());
        }
    }

    public interface ReposClickListener {
        void onRepositoryClick(Repository repository);
    }
}
