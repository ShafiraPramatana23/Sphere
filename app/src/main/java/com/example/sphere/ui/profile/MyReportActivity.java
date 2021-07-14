package com.example.sphere.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sphere.R;
import com.example.sphere.ui.home.WaterLevelActivity;
import com.example.sphere.ui.profile.adapter.MyReportAdapter;
import com.example.sphere.ui.profile.model.MyReportList;

import java.util.ArrayList;

public class MyReportActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyReportAdapter adapter;
    private ArrayList<MyReportList> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_report);

        getDataList();

        ImageView ivBack = findViewById(R.id.ivBack);
        recyclerView = findViewById(R.id.rv);

        adapter = new MyReportAdapter(list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyReportActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyReportActivity.super.onBackPressed();
            }
        });
    }

    private void getDataList() {
    }
}