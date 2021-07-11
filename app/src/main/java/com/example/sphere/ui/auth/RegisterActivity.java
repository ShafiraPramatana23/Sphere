package com.example.sphere.ui.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sphere.R;

public class RegisterActivity extends AppCompatActivity {

    TextView name , email , password , password_conf;
    Button btnRegister;
    ImageView showPassReg , showPassConfReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Daftar");
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        getSupportActionBar().setCustomView(R.layout.title_action_bar);

        name = findViewById (R.id.etName);
        email = findViewById (R.id.etEmail);
        password = findViewById (R.id.etPassword);
        password_conf = findViewById(R.id.etPasswordConfirmation);

        showPassReg = findViewById (R.id.icPassReg);
        showPassConfReg = findViewById (R.id.icPassConfReg);

        password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        password_conf.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);

        showPassReg.setOnClickListener(new View.OnClickListener() {
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

        showPassConfReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer inputType = password_conf.getInputType();
                if (inputType == InputType.TYPE_CLASS_TEXT) {
                    password_conf.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                }else{
                    password_conf.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent m = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(m);
        finish();
        return true;
    }

    private  void register(String name , String email , String password , String password_conf){

    }
}