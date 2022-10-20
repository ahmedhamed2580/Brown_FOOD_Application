package com.example.myapplication;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class home_activty extends AppCompatActivity implements OnitemRecycle{
    RecyclerView rv,rp;
    recycleview_catgoires rcv;
    popularfoodadapter pfr;
    LinearLayout ln,lo,lp;
    public static ArrayList<details> detailss = new ArrayList<>();
    ArrayList<popularfood>popularArrayList=new ArrayList<>();

    EditText search;
    mydatabase mydatabase =new mydatabase(this);
    mydatabase2 mydatabase2=new mydatabase2(this);
    FloatingActionButton floatingActionButton;
    public static FrameLayout frameLayout;
    public static TextView numberitems;

ImageView homeprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activty);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        rv=findViewById(R.id.rv_catgoirs);
        frameLayout= findViewById(R.id.frameLayoutnotf);
        numberitems=findViewById(R.id.numberdata);
        lo=findViewById(R.id.lovebtn);
        lp=findViewById(R.id.settingsbtn);
        homeprofile=findViewById(R.id.imagehomeprofile);
        homeprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(home_activty.this,profile.class);
                startActivity(intent);
            }
        });
        if( mydatabase.getfoodcount()>0){
        frameLayout.setVisibility(View.VISIBLE);
        mydatabase.getfoodcount();



        String countf= String.valueOf(mydatabase.getfoodcount());
        numberitems.setText(countf);
        }
        floatingActionButton=findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mydatabase.getfoodcount()>0) {
                    Intent intent = new Intent(home_activty.this, MainActivity3.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(home_activty.this, "Add The Items First", Toast.LENGTH_SHORT).show();
            }
        });
        ArrayList<catgoirs>catgoirsArrayList=new ArrayList<>();
        catgoirsArrayList.add(new catgoirs("Burger",R.drawable.burg));
        catgoirsArrayList.add(new catgoirs("Pizza",R.drawable.pizza));
        catgoirsArrayList.add(new catgoirs("Fish",R.drawable.pngegg));
        catgoirsArrayList.add(new catgoirs("meat",R.drawable.meat));
        catgoirsArrayList.add(new catgoirs("Chicken",R.drawable.chickens));
        catgoirsArrayList.add(new catgoirs("Fries",R.drawable.patatos));
        catgoirsArrayList.add(new catgoirs("Salad",R.drawable.salds));
        catgoirsArrayList.add(new catgoirs("Juice",R.drawable.juices));
        catgoirsArrayList.add(new catgoirs("Soda",R.drawable.sodas));
        catgoirsArrayList.add(new catgoirs("Ice Cream",R.drawable.icecream));
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rcv=new recycleview_catgoires(catgoirsArrayList,this,this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(rcv);
        rp=findViewById(R.id.rc_popular);

        popularArrayList.add(new popularfood(R.drawable.burg,R.drawable.burg,"Burger","Burger",12.5));
        popularArrayList.add(new popularfood(R.drawable.suchipizza,R.drawable.suchipizza,"Suchi Pizza","Suchi Pizza",18.0));
        popularArrayList.add(new popularfood(R.drawable.cookedfish,R.drawable.cookedfish,"Cooked Fish","Cooked Fish",12.5));
        popularArrayList.add(new popularfood(R.drawable.chicken_barbque,R.drawable.chicken_barbque,"Chicken Barbque","Chicken Barbque",25.0));
        popularArrayList.add(new popularfood(R.drawable.mixed_girl,R.drawable.mixed_girl,"MixGirl Meat","MixGirl Meat",23.0));
        popularArrayList.add(new popularfood(R.drawable.furitsalad,R.drawable.furitsalad,"Fruit Salad","Fruit Salad",5.0));
        popularArrayList.add(new popularfood(R.drawable.chess_burger,R.drawable.chess_burger,"Chess Burger","Chess Burger",8.5));
        popularArrayList.add(new popularfood(R.drawable.chicken_burger,R.drawable.chicken_burger,"Chicken Burger","Chicken Burger",10.0));
        popularArrayList.add(new popularfood(R.drawable.bigmac_burger,R.drawable.bigmac_burger,"BigMac Burger","BigMac Burger",15.0));
        popularArrayList.add(new popularfood(R.drawable.pizzabarbecue,R.drawable.pizzabarbecue,"Pizza Barbcue","Pizza Barbcue",20.0));
        popularArrayList.add(new popularfood(R.drawable.vegtablepizza,R.drawable.vegtablepizza,"Vegtable Pizza","Vegtable Pizza",15.0));
        popularArrayList.add(new popularfood(R.drawable.sougapizza,R.drawable.sougapizza,"Sough Pizza","Sough Pizza",17.5));
        popularArrayList.add(new popularfood(R.drawable.fried_fish,R.drawable.fried_fish,"Fried Fish","Fried Fish",15.0));
        popularArrayList.add(new popularfood(R.drawable.mashoui_fish,R.drawable.mashoui_fish,"Grilled Fish","Grilled Fish",11.0));
        popularArrayList.add(new popularfood(R.drawable.fillet_fish,R.drawable.fillet_fish,"Fillet Fish","Fillet Fish",10.0));
        popularArrayList.add(new popularfood(R.drawable.meattomato,R.drawable.meattomato,"Meat Tomatto","Meat Tomatto",20.0));
        popularArrayList.add(new popularfood(R.drawable.mixed,R.drawable.mixed,"Gril Meat","Gril Meat",22.0));
        popularArrayList.add(new popularfood(R.drawable.steak,R.drawable.steak,"Steak Meat","Steak Meat",18.0));
        popularArrayList.add(new popularfood(R.drawable.crispychicken,R.drawable.crispychicken,"Crispy Chicken","Crispy Chicken",15.0));
        popularArrayList.add(new popularfood(R.drawable.chicken_sauss,R.drawable.chicken_sauss,"Chicken Sauce","Chicken Sauce",13.5));
        popularArrayList.add(new popularfood(R.drawable.roasdchicken,R.drawable.roasdchicken,"Roasd Chicken","Roasd Chicken",25.0));
        popularArrayList.add(new popularfood(R.drawable.patatos,R.drawable.patatos,"Small Fries","Small Fries",3.0));
        popularArrayList.add(new popularfood(R.drawable.meduim_fries,R.drawable.meduim_fries,"Medium Fries","Medium Fries",5.0));
        popularArrayList.add(new popularfood(R.drawable.big_fries,R.drawable.big_fries,"Large Fries","Large Fries",8.0));
        popularArrayList.add(new popularfood(R.drawable.greeksalad,R.drawable.greeksalad,"Greek Salad","Greek Salad",6.5));
        popularArrayList.add(new popularfood(R.drawable.tacosalad,R.drawable.tacosalad,"Taco Salad","Taco Salad",4.5));
        popularArrayList.add(new popularfood(R.drawable.gardansalat,R.drawable.gardansalat,"Gardan Salad","Gardan Salad",3.5));
        popularArrayList.add(new popularfood(R.drawable.juicemango,R.drawable.juicemango,"Mango Juice","Mango Juice",7.0));
        popularArrayList.add(new popularfood(R.drawable.juicestrwabrey,R.drawable.juicestrwabrey,"Strawberry juice","Strawberry juice",5.5));
        popularArrayList.add(new popularfood(R.drawable.juicelemon,R.drawable.juicelemon,"Lemon Juice","Lemon Juice",2.0));
        popularArrayList.add(new popularfood(R.drawable.juiceorane,R.drawable.juiceorane,"Orange Juice","Orange Juice",3.0));
        popularArrayList.add(new popularfood(R.drawable.pepsi,R.drawable.pepsi,"Pepsi Cola","Pepsi Cola",3.0));
        popularArrayList.add(new popularfood(R.drawable.cola,R.drawable.cola,"Coca Cola","Coca Cola",3.0));
        popularArrayList.add(new popularfood(R.drawable.sprite,R.drawable.sprite,"Sprite Cola","Sprite Cola",3.0));
        popularArrayList.add(new popularfood(R.drawable.fanta,R.drawable.fanta,"Fanta Cola","Fanta Cola",3.0));
        popularArrayList.add(new popularfood(R.drawable.icecreamchoclate,R.drawable.icecreamchoclate,"Chocolate IceCream","Chocolate IceCream",4.5));
        popularArrayList.add(new popularfood(R.drawable.icecreamvanlia,R.drawable.icecreamvanlia,"Vanilla IceCream","Vanilla IceCream",5.0));
        popularArrayList.add(new popularfood(R.drawable.icecreammango,R.drawable.icecreammango,"Mango IceCream","Mango IceCream",6.0));
        popularArrayList.add(new popularfood(R.drawable.icecreamstrwabrey,R.drawable.icecreamstrwabrey,"Strawberry IceCream","Strawberry IceCream",5.5));
        pfr=new popularfoodadapter(popularArrayList, this, new notifactionclick() {
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

        LinearLayoutManager layoutManager1=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

      rp.setLayoutManager(layoutManager1);
        rp.setAdapter(pfr);
rp.setHasFixedSize(true);
rp.setNestedScrollingEnabled(false);
ln=findViewById(R.id.menubtn);

search=findViewById(R.id.editsearch);
search.addTextChangedListener(new TextWatcher() {
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

ln.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(home_activty.this,Menu.class);
        startActivity(intent);
    }
});
lp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(home_activty.this, profile.class);
        startActivity(intent);

    }
});
        lo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mydatabase2.getfoodcount()!=0) {
                    Intent intent = new Intent(home_activty.this, Loves_Activity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(home_activty.this, "Add items to The Loves List First", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slideleft_in_activty,R.anim.slideright_out_activty);
    }

    @Override
    public Void onitemClick(int position) {
        if(position==0){
            detailss.add(new details("Burger", "Burger is best food in our app it is so good food", R.drawable.burg,12.5,1));
            detailss.add(new details("Chesse Burger", "Chesse Burger is best food in our app it is so good food", R.drawable.chess_burger,8.5,2));
            detailss.add(new details("Chicken Burger", "Chicken Burger is best food in our app it is so good food", R.drawable.chicken_burger,10.0,3));
            detailss.add(new details("Bigmac Burger", "Bigmac Burger is best food in our app it is so good food", R.drawable.bigmac_burger,15.0,4));

        }
        if(position==1){
            detailss.add(new details("Pizza Barbcue", "Pizza Barbcue is best food in our app it is so good food", R.drawable.pizzabarbecue,20.0,5));
            detailss.add(new details("Suchi Pizza", "Suchi Pizza is best food in our app it is so good food", R.drawable.suchipizza,18.0,6));
            detailss.add(new details("Vegetables Pizza", "Vegetables Pizza is best food in our app it is so good food", R.drawable.vegtablepizza,15.0,7));
            detailss.add(new details("Sough Pizza", "Sough Pizza is best food in our app it is so good food", R.drawable.sougapizza,17.5,8));
        }
        if(position==2){

            detailss.add(new details("FriedFish", "Fried Fish is best food in our app it is so good food", R.drawable.fried_fish,15.0,9));
            detailss.add(new details("Grilled Fish", "Grilled Fish is best food in our app it is so good food", R.drawable.mashoui_fish,11.0,10));
            detailss.add(new details("Cooked Fish", "Cooked Fish is best food in our app it is so good food", R.drawable.cookedfish,12.5,11));
            detailss.add(new details("Fillet Fish", "Fillet Fish is best food in our app it is so good food", R.drawable.fillet_fish,10.0,12));
        }
        if(position==3){

            detailss.add(new details("MixGril Meat", "MixGril Meat is best food in our app it is so good food", R.drawable.mixed_girl,23.0,13));
            detailss.add(new details("Meat Tomatto", "Meat Tomatto is best food in our app it is so good food", R.drawable.meattomato,20.0,14));
            detailss.add(new details("Gril Meat", "Mix Gril is best food in our app it is so good food", R.drawable.mixed,22.0,15));
            detailss.add(new details("Steak Meat", "Steak Meat is best food in our app it is so good food", R.drawable.steak,18.0,16));
        }
        if(position==4){

            detailss.add(new details("Crispy Chicken", "Crispy Chicken is best food in our app it is so good food", R.drawable.crispychicken,15.0,17));
            detailss.add(new details("Chicken Barbecue", "Chicken Barbecue is best food in our app it is so good food", R.drawable.chicken_barbque,25.0,18));
            detailss.add(new details("Chicken Sauce", "Chicken Sauce is best food in our app it is so good food", R.drawable.chicken_sauss,13.5,19));
            detailss.add(new details("Roasd Chicken", "Roasd Chicken is best food in our app it is so good food", R.drawable.roasdchicken,25.0,20));
        }
        if(position==5){
            detailss.add(new details("Small Fries", "Small Fries is best food in our app it is so good food", R.drawable.patatos,3.0,21));
            detailss.add(new details("Medium Fries", "Medium Fries is best food in our app it is so good food", R.drawable.meduim_fries,5.0,22));
            detailss.add(new details("Large Fries", "Large Fries is best food in our app it is so good food", R.drawable.big_fries,8.0,23));
        }

        if(position==6){
            detailss.add(new details("Greek Salad", "Greek Salad is best food in our app it is so good food", R.drawable.greeksalad,6.5,24));
            detailss.add(new details("Taco Salad", "Taco Salad is best food in our app it is so good food", R.drawable.tacosalad,4.5,25));
            detailss.add(new details("Gardan Salad", "Gardan Salad is best food in our app it is so good food", R.drawable.gardansalat,3.5,26));
            detailss.add(new details("Fruit Salad", "Fruit Salad is best food in our app it is so good food", R.drawable.furitsalad,5.0,27));
        }
        if(position==7){
            detailss.add(new details("Mango Juice", "Juice Mango is best juice in our app it is so good food", R.drawable.juicemango,7.0,28));
            detailss.add(new details("Strawberry Juice", "Juice Strawberry is best juice in our app it is so good food", R.drawable.juicestrwabrey,5.5,29));
            detailss.add(new details("Lemon Juice", "Juice Lemon is best juice in our app it is so good food", R.drawable.juicelemon,2.0,30));
            detailss.add(new details("Orange Juice", "Bigmac Burger is best juice in our app it is so good food", R.drawable.juiceorane,3.0,31));
        }
        if(position==8){
            detailss.add(new details("Pepsi", "Pepsi is best Soda in our app it is so good food", R.drawable.pepsi,3.0,32));
            detailss.add(new details("CocaCola", "CocaCola is best Soda in our app it is so good food", R.drawable.cola,3.0,33));
            detailss.add(new details("Sprite", "Sprite is best Soda in our app it is so good food", R.drawable.sprite,3.0,34));
            detailss.add(new details("Fanta", "Fanta Burger is best Soda in our app it is so good food", R.drawable.fanta,3.0,35));
        }
        if(position==9){
            detailss.add(new details("Chocolate IceCream", "Chocolate IceCream is best IceCream in our app it is so good food", R.drawable.icecreamchoclate,4.5,36));
            detailss.add(new details("Vanilla IceCream", "Vanilla IceCream is best  IceCream in our app it is so good food", R.drawable.icecreamvanlia,5.0,37));
            detailss.add(new details("Mango IceCream", "Mango IceCream is best IceCream in our app it is so good food", R.drawable.icecreammango,6.0,38));
            detailss.add(new details("Strawberry IceCream", "Strawberry IceCream is best  IceCream in our app it is so good food", R.drawable.icecreamstrwabrey,5.5,39));
        }
        Intent intent =new Intent(home_activty.this, MainActivity2.class);
        startActivity(intent);

        overridePendingTransition(R.anim.slideright_in_activty,R.anim.slideleft_out_activty);
        return null;
    }
    private void filter(String text){

        ArrayList<popularfood>filterlist=new ArrayList<>();
        for(popularfood popularfood:popularArrayList){
            if(popularfood.getName().toLowerCase().contains(text.toLowerCase())){
                filterlist.add(popularfood);
            }

        }
        popularfoodadapter.filteredlist(filterlist);
        pfr.notifyDataSetChanged();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(home_activty.this, home_activty.class);
        startActivity(intent);
    }
}