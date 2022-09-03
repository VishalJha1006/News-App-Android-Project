package com.example.newsapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.Models.NewsApiResponse;
import com.example.newsapp.Models.NewsHeadlines;
import com.google.android.material.chip.Chip;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener {
    Chip b1,b2,b3,b4,b5,b6,b7;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    dialog =new ProgressDialog(this);
    dialog.setTitle("FETCHING NEWS ARTICLES");
    dialog.show();

    b1=findViewById(R.id.btn_1);
    b2=findViewById(R.id.btn_2);
    b3=findViewById(R.id.btn_3);
        b4=findViewById(R.id.btn_4);
        b5=findViewById(R.id.btn_5);
        b6=findViewById(R.id.btn_6);
        b7=findViewById(R.id.btn_7);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);

        RequestManager manager=new RequestManager(this);
        manager.getNewsHeadlines(listener,"general",null);
    }
    private final OnFetchDataListener<NewsApiResponse> listener =new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {

            showNews(list);
            dialog.dismiss();
        }

        @Override
        public void onError(String message) {

        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView=findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter=new CustomAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class).putExtra("data", headlines);
        startActivity(intent);

    }

    @Override
    public void onClick(View view) {
        Chip chip=(Chip) view;
        String category=chip.getText().toString();
        dialog.setTitle("Fetching news for "+category);
    dialog.show();
        RequestManager manager=new RequestManager(this);
        manager.getNewsHeadlines(listener,category,null);

    }
}