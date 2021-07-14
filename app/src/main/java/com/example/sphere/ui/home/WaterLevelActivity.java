package com.example.sphere.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.sphere.R;
import com.example.sphere.ui.lapor.adapter.SpinnerAdapter;
import com.scwang.wave.MultiWaveHeader;

public class WaterLevelActivity extends AppCompatActivity {

    String[] ctg = { "Sungai A", "Sungai B"};

    private Spinner spin;
    private MultiWaveHeader waveHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_level);

        spin = findViewById(R.id.spinner);
        ImageView ivBack = findViewById(R.id.ivBack);
        waveHeader = findViewById(R.id.waveHeader);
        waveHeader.start();

        SpinnerAdapter aa = new SpinnerAdapter(this, R.layout.item_spinner, ctg);
        spin.setAdapter(aa);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WaterLevelActivity.super.onBackPressed();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        waveHeader.stop();
    }
}