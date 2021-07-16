package com.example.sphere.ui.auth;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.sphere.MainActivity;
import com.example.sphere.R;
import com.example.sphere.util.MySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EditPasswordActivity extends AppCompatActivity {

    TextView oldpass, newpass, confirmpass;
    ImageView showPassEditPass;
    Button btnEditPass;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);

        oldpass = findViewById(R.id.oldPassword);
        newpass = findViewById(R.id.newPassword);
        confirmpass = findViewById(R.id.confirmPassword);
        btnEditPass = findViewById(R.id.btnEditPassword);
        showPassEditPass = findViewById(R.id.icPassEdit);
        sharedPreferences = getSharedPreferences("UserInfo",
                Context.MODE_PRIVATE);

        oldpass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        newpass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
        confirmpass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);

        showPassEditPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer inputType1 = oldpass.getInputType();
                Integer inputType2 = newpass.getInputType();
                Integer inputType3 = confirmpass.getInputType();

                if (inputType1 == InputType.TYPE_CLASS_TEXT) {
                    oldpass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                } else {
                    oldpass.setInputType(InputType.TYPE_CLASS_TEXT);
                }

                if (inputType2 == InputType.TYPE_CLASS_TEXT) {
                    newpass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                } else {
                    newpass.setInputType(InputType.TYPE_CLASS_TEXT);
                }

                if (inputType3 == InputType.TYPE_CLASS_TEXT) {
                    confirmpass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                } else {
                    confirmpass.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });

        btnEditPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tex_password1 = Objects
                        .requireNonNull(oldpass.getText()).toString();
                String tex_password2 = Objects
                        .requireNonNull(newpass.getText()).toString();
                String tex_password3 = Objects
                        .requireNonNull(confirmpass.getText()).toString();
                if (TextUtils.isEmpty(tex_password1) || TextUtils.isEmpty(tex_password2) || TextUtils.isEmpty(tex_password3)) {
                    Toast.makeText(EditPasswordActivity.this,
                            "Pastikan Semua Data Sudah Terisi !", Toast.LENGTH_SHORT).show();
                } else if ((newpass.getText().toString().length() < 8) || (confirmpass.getText().toString().length() < 8)) {
                    Toast.makeText(EditPasswordActivity.this,
                            "Kata Sandi Minimal 8 Karakter !", Toast.LENGTH_SHORT).show();
                } else if (!newpass.getText().toString().equals(confirmpass.getText().toString())) {
                    Toast.makeText(EditPasswordActivity.this,
                            "Password dan Konfirmasi Password Tidak Sama !", Toast.LENGTH_SHORT).show();
                } else {
                    editPassword(oldpass.getText().toString(), newpass.getText().toString(), confirmpass.getText().toString());
                }
            }
        });
    }

    private void editPassword(String oldpass, String newpass, String confirmpass) {
        final ProgressDialog progressDialog = new ProgressDialog(
                EditPasswordActivity.this);
        progressDialog.setTitle("Tunggu....");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        String uRl = "https://sphere-apps.herokuapp.com/api/auth/edit-password";

    }
}