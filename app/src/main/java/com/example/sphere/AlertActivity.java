package com.example.sphere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sphere.ui.auth.LoginActivity;

public class AlertActivity extends AppCompatActivity {
    TextView title, description, back;
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);

        title = (TextView) findViewById(R.id.MessageTitle);
        description = (TextView) findViewById(R.id.MessageDesc);
        btnFinish = (Button) findViewById(R.id.btnFinish);
        back = (TextView) findViewById(R.id.txtBack);

        Intent intent = getIntent();
        String menu = intent.getStringExtra("menu");

        if (menu.equals("password")) {
            title.setText("Berhasil Mengubah Kata Sandi Anda !");
            description.setVisibility(View.GONE);
            btnFinish.setText("Selesai");
            back.setVisibility(View.GONE);

            btnFinish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //kembali ke
                }
            });
        } else if (menu.equals("profile")) {
            title.setText("Berhasil Mengubah Profil Anda !");
            description.setVisibility(View.GONE);
            btnFinish.setText("Selesai");
            back.setVisibility(View.GONE);

            btnFinish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //kembali ke
                }
            });
        } else if (menu.equals("register")) {
            title.setText("Berhasil Daftar Akun !");
            description.setText("Silahkan login untuk memulai aplikasi");
            btnFinish.setText("Login");
            back.setVisibility(View.GONE);

            btnFinish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(AlertActivity
                            .this, LoginActivity.class));
                }
            });
        } else if (menu.equals("report")) {
            title.setText("Laporan Anda telah terkirim !");
            description.setText("Kami akan segera memproses laporan Anda. Anda dapat melihat laporan Anda di halaman profil");
            btnFinish.setText("Detail Laporan");
            btnFinish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //halaman laporan saya
                }
            });
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //kembali ke
                }
            });
        }
    }
}