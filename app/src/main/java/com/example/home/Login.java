package com.example.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    Button loginb;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginb=(Button)findViewById(R.id.btnLogin);
        loginb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open();
            }
        });

    }
    private void open()
    {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(Login.this, "Clicked on Button", Toast.LENGTH_LONG).show();
    }
}
