package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.myapplication.databinding.ActivityMain2Binding;

import java.util.ArrayList;

import static com.example.myapplication.home_activty.*;


public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;
    int r=0;
    mydatabase mydatabase =new mydatabase(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(binding.getRoot());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        recycleadapter rc = new recycleadapter(detailss, this, new notifactionclick() {
            @Override
            public void notficationonclick() {
                if(frameLayout.getVisibility()==View.GONE) {
                    frameLayout.setVisibility(View.VISIBLE);
                }
              mydatabase.getfoodcount();
                String countf= String.valueOf(mydatabase.getfoodcount());
                numberitems.setText(countf);
                if( mydatabase.getfoodcount()==0){
                    frameLayout.setVisibility(View.GONE);

                }
            }


        });

        binding.recyclerView.setAdapter(rc);


    }

    @Override
    protected void onPause() {
        super.onPause();
        detailss.removeAll(detailss);

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent=new Intent(this, home_activty.class);
        startActivity(intent);
    }
}