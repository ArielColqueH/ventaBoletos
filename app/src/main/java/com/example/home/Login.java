package com.example.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    Button loginb;
    TextView user,password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginb=(Button)findViewById(R.id.btnLogin);
        user = (TextView)findViewById(R.id.inputUserName);
        password=(TextView)findViewById(R.id.inputPassword);
        loginb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userS,passS;
                userS=user.getText().toString();
                passS = password.getText().toString();
                Log.d("login",userS+":"+passS);
                if(userS.equals("admin") && passS.equals("admin")){
                    Toast.makeText(Login.this, "ingresa", Toast.LENGTH_SHORT).show();
                    open();
                }else{
                    Toast.makeText(Login.this, "No ingresa", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
    private void open()
    {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }
}
