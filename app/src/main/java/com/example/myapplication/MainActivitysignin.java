package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.*;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivitysignin extends AppCompatActivity {
Button login,signupm;
ImageView imgvector;
ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainsignin);
        login=findViewById(R.id.btnlogin);
        signupm=findViewById(R.id.btnsignup);
        constraintLayout=findViewById(R.id.consntrauin);
        imgvector =findViewById(R.id.imagevector);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivitysignin.this, MainActivitysignin2.class);
                startActivity(intent);
            }
        });
        signupm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivitysignin.this, MainActivitysignin3.class);
                startActivity(intent);
            }
        });
    }
}