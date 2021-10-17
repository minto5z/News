package com.example.news.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.adapter.NewsAdapter;
import com.example.news.application.NewsApplication;
import com.example.news.data.News;
import com.example.news.repository.NewsRepository;
import com.example.news.response.Response;
import com.example.news.viewmodel.EntertainmentViewModel;
import com.example.news.viewmodel.NewsViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class Entertainment extends Fragment implements NewsAdapter.ClickListener {

    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private EntertainmentViewModel viewModel;
    private List<News> entertainmentData;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.entertainment_layout, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        entertainmentData = new ArrayList<>();
        recyclerView = view.findViewById(R.id.entertainment_recycleView);
        progressBar = view.findViewById(R.id.entertainment_progressBar);
        NewsRepository repository = NewsApplication.repository;
        viewModel = new ViewModelProvider(this, new NewsViewModelFactory(repository)).get(EntertainmentViewModel.class);
        RecyclerView.LayoutManager managers = new LinearLayoutManager(getActivity());
        adapter = new NewsAdapter(this);
        recyclerView.setLayoutManager(managers);
        recyclerView.setAdapter(adapter);
        viewModel.entertainmentLiveData.observe(getViewLifecycleOwner(), new Observer<Response<List<News>>>() {
            @Override
            public void onChanged(Response<List<News>> listResponse) {
                switch (listResponse.status) {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        entertainmentData = listResponse.data;
                        adapter.setAdapter(listResponse.data);
                        progressBar.setVisibility(View.INVISIBLE);
                        break;
                    case ERROR:
                        Toast.makeText(getActivity(), listResponse.message, Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });

    }


    @Override
    public void onItemClick(int position) {
        Uri uri = Uri.parse(entertainmentData.get(position).getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
