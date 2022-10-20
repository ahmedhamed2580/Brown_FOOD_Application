package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    ImageView plat,order;
    TextView t;
    DBHELPER db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plat=findViewById(R.id.plate);
        order=findViewById(R.id.orernow);
        t=findViewById(R.id.textplate);
        db=new DBHELPER(this);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        plat.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadein));
        order.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_from_right));
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, MainActivitysignin.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slideright_in_activty, R.anim.slideleft_out_activty);


            }
        });



    }
}