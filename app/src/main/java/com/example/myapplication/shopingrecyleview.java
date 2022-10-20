package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

public  class shopingrecyleview extends RecyclerView.Adapter<shopingrecyleview.viewholder> {
    @NonNull
    @NotNull
    ArrayList<fooddb> cardarrayfood;
    oncardclick listner;
    double x=0;
   public static String sub;
    Context context;


    public shopingrecyleview(@NonNull ArrayList<fooddb> cardarrayfood, oncardclick listner, Context context) {
        this.cardarrayfood = cardarrayfood;
        this.listner=listner;
        this.context=context;
    }

    @Override
    public viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardshop,parent,false);
        shopingrecyleview.viewholder vh=new shopingrecyleview.viewholder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewholder holder, int position) {

        holder.cardfood.setImageResource(cardarrayfood.get(position).getImg());
        String fee=cardarrayfood.get(position).getPrice().toString();
        holder.price.setText(fee);
        holder.name.setText(cardarrayfood.get(position).getName());
       String cfo= String.valueOf(cardarrayfood.get(position).getCardnumber());

       holder.numberfood.setText(cfo);
        holder.btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.oncard_click(cardarrayfood,position);
notifyDataSetChanged();
            }
        });
        holder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.oncardclickminus(cardarrayfood,position);


                notifyDataSetChanged();

            }
        });
        holder.btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               listner.onexitclick(cardarrayfood,position);
               notifyItemRemoved(position);
                cardarrayfood.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,cardarrayfood.size());


            }
        });

        setanimation(holder.itemView,position);





    }

    @Override
    public int getItemCount() {
        return cardarrayfood.size();
    }

    public  class viewholder extends RecyclerView.ViewHolder{
        TextView name,rate,location,price;
        ImageView cardfood;
        ImageView btnadd,btnminus,btnexit;
        TextView numberfood;
        public viewholder(@NonNull @NotNull View itemView){
            super(itemView);
            name= itemView.findViewById(R.id.name_card_order);
            rate=itemView.findViewById(R.id.rate_card_order);
            location=itemView.findViewById(R.id.card_location);
            price=itemView.findViewById(R.id.card_price);
            cardfood=itemView.findViewById(R.id.image_card_food);
            btnadd=itemView.findViewById(R.id.plusbtn);
            btnminus=itemView.findViewById(R.id.minusbtn);
            numberfood=itemView.findViewById(R.id.number_card_food);
            btnexit=itemView.findViewById(R.id.delete);





        }



    }


  private  int lastpostion=-1;
    private  void  setanimation(View viewtoanimation,int position){
        if(position>lastpostion){
            ScaleAnimation anim=new ScaleAnimation(0.0f,1.0f,0.0f,1.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            anim.setDuration(new Random().nextInt(2001));
            viewtoanimation.startAnimation(anim);
            lastpostion=position;
        }
    }
}
