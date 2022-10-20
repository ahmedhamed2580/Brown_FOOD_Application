package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;


public class MainActivity3 extends AppCompatActivity {
    public static RecyclerView recyclerView;
    mydatabase db;
    ArrayList<fooddb> fooddbArrayList = new ArrayList<>();
    ConstraintLayout disconutbtn,btnordercard;
    EditText disscounttxt;
    TextView subtotal;
    double x,x1,x2,x3,r2;
    TextView disscount,delivery,total;
    LinearLayout homebtn,menu,love;
    ConstraintLayout apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        recyclerView = findViewById(R.id.recyclerView2);
        subtotal=findViewById(R.id.subtotal);
        disscount=findViewById(R.id.discount);
        delivery=findViewById(R.id.delivery);
        total=findViewById(R.id.total);
        disconutbtn=findViewById(R.id.disscountbtn);
        disscounttxt=findViewById(R.id.disconttxt);
        btnordercard=findViewById(R.id.btnordercart);
        homebtn=findViewById(R.id.homebtn);
        menu=findViewById(R.id.menubtn);
        apply=findViewById(R.id.apply);
        love=findViewById(R.id.lovebtn);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity3.this, home_activty.class);
                startActivity(intent);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity3.this, Menu.class);
                startActivity(intent);
            }
        });

        btnordercard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             db.delete_all_data();
             Intent intent=new Intent(MainActivity3.this, home_activty.class);
             startActivity(intent);
            }
        });
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity3.this, Loves_Activity.class);
                startActivity(intent);

            }
        });
        delivery.setText("2");
        r2=Double.parseDouble(delivery.getText().toString());
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        db = new mydatabase(MainActivity3.this);
        recyclerView.setLayoutManager(layoutManager1);
        Cursor cursor = new mydatabase(this).getdata();
        if (cursor.moveToFirst()) {
            do {
                int image = cursor.getInt(1);
                double price = cursor.getDouble(2);
                String name = cursor.getString(3);
                int cardfood = 1;
                x += price * cardfood;
                fooddb f = new fooddb(cardfood, image, price, name);
                fooddbArrayList.add(f);
            } while (cursor.moveToNext());

            String mub=String.format(Locale.US,"%,.2f",x);
            String bub= String.format(Locale.US,"%,.2f",x+r2);
            subtotal.setText(mub);
            total.setText(bub);

            shopingrecyleview shopingrecyleview = new shopingrecyleview(fooddbArrayList, new oncardclick() {
                @Override
                public void oncard_click(ArrayList<fooddb>foodm, int position) {
                    double additem=foodm.get(position).getPrice();
                    x+=additem;
                    String resulttotal=String.format(Locale.US,"%,.2f",x+r2);
                    String result = String.format(Locale.US,"%,.2f", x);
                    total.setText(resulttotal);
                    subtotal.setText(result);
                    discountcode(x);
                    foodm.get(position).setCardnumber(foodm.get(position).getCardnumber()+1);

                }

                @Override
                public void oncardclickminus(ArrayList<fooddb> foody, int position) {
                    if(foody.get(position).getCardnumber()>1){
                        foody.get(position).setCardnumber(foody.get(position).getCardnumber()-1);
                        double additem=foody.get(position).getPrice();
                        x-=additem;
                        String resulttotal=String.format(Locale.US,"%,.2f",x+r2);
                        String result =  String.format(Locale.US,"%,.2f", x);
                        total.setText(resulttotal);
                        subtotal.setText(result);
                        discountcode(x);





                    }
                }

                @Override
                public void onexitclick(ArrayList<fooddb> food, int position) {
                    mydatabase db=new mydatabase(MainActivity3.this);
                    oncardclickminus(food,position);
                    double additem=food.get(position).getPrice();
                    double parsecard=Double.parseDouble(String.valueOf(food.get(position).getCardnumber()));
                   x= x-(additem*parsecard);
                    String result = String.format(Locale.US,"%,.2f", x);
                    String resulttotal = String.format(Locale.US,"%,.2f", x+r2);
                    total.setText(resulttotal);
                    subtotal.setText(result);
                    discountcode(x);
                    db.deletefood(food.get(position).getName());

                    if(food.size()==1){
                        db.deletefood(food.get(position).getName());
                        Intent intent=new Intent(MainActivity3.this, home_activty.class);
                        startActivity(intent);
                    }



                }
            },this);
            recyclerView.setAdapter(shopingrecyleview);


        }

        disconutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(disscounttxt.getVisibility()==View.GONE){
                    apply.setVisibility(View.VISIBLE);
                    disscounttxt.setVisibility(View.VISIBLE);
                }
                else{
                    disscounttxt.setVisibility(View.GONE);
                    apply.setVisibility(View.GONE);


                }
            }
        });
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               discountcode(x);
                if(discountcode(x)==false){
                    Toast.makeText(MainActivity3.this, "Error Discount Code", Toast.LENGTH_SHORT).show();

                }
            }
        });





    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(MainActivity3.this, home_activty.class);
        startActivity(intent);
    }
    public boolean discountcode(double t){


        if(disscounttxt.getText().toString().toLowerCase().equals("brown")){
            x1=t*0.15;
            disscount.setText(String.format(Locale.US,"%,.2f", x1));


        }
        else  if(disscounttxt.getText().toString().toLowerCase().equals("hackerbrown")){
            x2=t*0.30;
            disscount.setText(String.format(Locale.US,"%,.2f", x2));

        }
        else  if(disscounttxt.getText().toString().toLowerCase().equals("nursingprogramer")){
            x3=t*0.60;
            disscount.setText(String.format(Locale.US,"%,.2f", x3));

        }
        else{
            disscount.setText("0.0");
            return false;
        }
        double r4=Double.parseDouble(disscount.getText().toString());
        String res1= String.format(Locale.US,"%,.2f", (x+r2)-r4);
        total.setText(res1);
            return true;
    }
}