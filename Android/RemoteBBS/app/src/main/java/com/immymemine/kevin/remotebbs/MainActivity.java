package com.immymemine.kevin.remotebbs;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.immymemine.kevin.remotebbs.model.Result;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button post;
    ProgressBar progressBar;
    Intent postIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postIntent = new Intent(this, PostActivity.class);
        load();
    }
    private int page = 1;
    private void load() {
        new AsyncTask<Void, Void, Result>() {
            @Override
            protected void onPreExecute() {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected Result doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String result = Remote.getData("http://000.000.0.000:8090/bbs?type=all&page="+page);
                Gson gson = new Gson();
                Result data = gson.fromJson(result, Result.class);
                return data;
            }

            @Override
            protected void onPostExecute(Result result) {
                progressBar.setVisibility(View.GONE);
                if(result.isSuccess()) {
                    if(page == 1)
                        setList(result);
                    else if(page > 1)
                        addList(result);

                    page++;
                }
            }
        }.execute();
    }

    RecyclerViewAdapter adapter;
    LinearLayoutManager lmManager;
    private void setList(Result result) {
        adapter = new RecyclerViewAdapter(result.getData());
        recyclerView.setAdapter(adapter);
        lmManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lmManager);
    }

    private void addList(Result result) {
        adapter.addDataAndRefresh(result.getData());
    }

    private void initatiteView() {
        progressBar = findViewById(R.id.progressBar);
        post = findViewById(R.id.post_btn);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });
    }
}
