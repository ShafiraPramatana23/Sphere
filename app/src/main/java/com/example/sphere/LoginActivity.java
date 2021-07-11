package com.example.sphere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    TextView email, password, register;
    ImageView showPassLogin;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById (R.id.etEmail);
        password = findViewById (R.id.etPassword);
        register = findViewById (R.id.txtRegister);
        btnLogin = findViewById (R.id.btnLogin);
        showPassLogin = findViewById (R.id.icPassLogin);

        password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(m);
                finish();
            }
        });

         showPassLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer inputType = password.getInputType();
                if (inputType == InputType.TYPE_CLASS_TEXT) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                }else{
                    password.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tex_email = Objects
                        .requireNonNull(email.getText()).toString();
                String tex_password = Objects
                        .requireNonNull(password.getText()).toString();
                if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)){
                    Toast.makeText(LoginActivity.this,
                            "Pastikan Semua Data Sudah Terisi !", Toast.LENGTH_SHORT).show();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()){
                    Toast.makeText(LoginActivity.this,
                            "Format Email Tidak Valid !", Toast.LENGTH_SHORT).show();
                }
                else{
                    login(email.getText().toString(),password.getText().toString());
                }
            }
        });
    }

    private void login(String email , String password){

    }
}