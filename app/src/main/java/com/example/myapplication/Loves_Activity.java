package com.example.myapplication;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Console;
import java.util.ArrayList;

import static com.example.myapplication.loverecycle.lovefoodarray;


public class Loves_Activity extends AppCompatActivity {
   ArrayList<loveclass> loveclassArrayList = new ArrayList<>();

    RecyclerView recyclerViewlove;
    loverecycle loverecycles;
    FrameLayout fmnotf;
    TextView numberlovefood;
    ConstraintLayout burger,pizza,fish,meat,chicken,fries,salad,juice,soda,icecream,all;
    EditText search2;
    mydatabase2 db2;

    private final ArrayList<Integer>select=new ArrayList<>();

    mydatabase mydatabase =new mydatabase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loves);
        recyclerViewlove=findViewById(R.id.recylelove);
        fmnotf =findViewById(R.id.frameLayoutnotf);
        numberlovefood=findViewById(R.id.numberdata);
        burger=findViewById(R.id.burger);
        pizza=findViewById(R.id.pizza);
        fish=findViewById(R.id.fish);
        meat=findViewById(R.id.meat);
        chicken=findViewById(R.id.chicken);
        fries=findViewById(R.id.fries);
        salad=findViewById(R.id.salad);
        juice=findViewById(R.id.juice);
        soda=findViewById(R.id.soda);
        icecream=findViewById(R.id.icecream);
        all=findViewById(R.id.all);
        search2=findViewById(R.id.editsearch2);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        db2=new mydatabase2(this);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Loves_Activity.this, Loves_Activity.class);
                startActivity(intent);
            }
        });
        burger.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                filter("burger");
            }
        });
        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter("pizza");
            }
        });
        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter("fish");
            }
        });
        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter("meat");
            }
        });
        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter("chicken");
            }
        });
        fries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter("fries");
            }
        });
        salad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter("salad");
            }
        });
        juice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter("juice");
            }
        });
        soda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter("cola");
            }
        });
        icecream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filter("icecream");
            }
        });







        if( mydatabase.getfoodcount()>0){
            fmnotf.setVisibility(View.VISIBLE);
            mydatabase.getfoodcount();
            String countf= String.valueOf(mydatabase.getfoodcount());
            numberlovefood.setText(countf);
        }
        Cursor cursor = new mydatabase2(this).getdata();
        fmnotf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Loves_Activity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
        if (cursor.moveToFirst()) {
            do {
                int image = cursor.getInt(1);
                double price = cursor.getDouble(2);
                String name = cursor.getString(3);
                loveclass f = new loveclass(image, price, name);
                loveclassArrayList.add(f);
            } while (cursor.moveToNext());
            LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
            recyclerViewlove.setLayoutManager(layoutManager3);
            loverecycles=new loverecycle(loveclassArrayList, this, new notifactionclick() {
                @Override
                public void notficationonclick() {
                    if (fmnotf.getVisibility() == View.GONE) {
                        fmnotf.setVisibility(View.VISIBLE);
                    }
                    mydatabase.getfoodcount();
                    String countf = String.valueOf(mydatabase.getfoodcount());
                    numberlovefood.setText(countf);
                    if (mydatabase.getfoodcount() == 0) {
                        fmnotf.setVisibility(View.GONE);

                    }

                }
            }, new exitclickfilter() {
                @Override
                public void onexitclick(ArrayList<loveclass> food, int position) {
                    db2.deletefood(lovefoodarray.get(position).getName());
                    lovefoodarray.remove(position);
                    loverecycles.notifyItemRemoved(position);
                    loverecycles.notifyItemRangeChanged(position,loveclassArrayList.size());
                    if(db2.getfoodcount()==0){
                        Intent intent=new Intent(Loves_Activity.this, home_activty.class);
                        startActivity(intent);
                    }

                }
            });
            recyclerViewlove.setAdapter(loverecycles);

        }

        search2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });
    }
    private void filter(String text){
        ArrayList<loveclass>filterlist=new ArrayList<>();
        for(loveclass loveclass:loveclassArrayList){
            if(loveclass.getName().toLowerCase().contains(text.toLowerCase())){
                filterlist.add(loveclass);

            }

        }
        loverecycle.filteredlist(filterlist);
        loverecycles.notifyDataSetChanged();



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Loves_Activity.this, home_activty.class);
        startActivity(intent);
    }
}
