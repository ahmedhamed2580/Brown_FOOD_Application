package com.example.myapplication;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public  class popularfoodadapter extends RecyclerView.Adapter<popularfoodadapter.viewholder> {
    @NonNull
    @NotNull
    mydatabase db;
    mydatabase2 db2;
    fooddb food;
    loveclass lovefood;
    notifactionclick listner;
    public static ArrayList<popularfood>popularfoods;
    private final ArrayList<Integer>select=new ArrayList<>();
    private final ArrayList<Integer>select2=new ArrayList<>();
    Dialog dialogs;

    private Context context;
    public popularfoodadapter(@NonNull ArrayList<popularfood> popularfoods, Context context,notifactionclick listner) {
        this.popularfoods = popularfoods;
        this.context=context;
        this.listner=listner;
    }

    public static void filteredlist(ArrayList<popularfood> filteredlist) {
        popularfoods=filteredlist;
    }

    @Override
    public viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewpopular,parent,false);
        viewholder vh=new viewholder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull  viewholder holder, int position) {

        holder.img1.setImageResource(popularfoods.get(position).getImage1());
        holder.img2.setImageResource(popularfoods.get(position).getImage2());
        holder.name.setText(popularfoods.get(position). getName());
        holder.subname.setText(popularfoods.get(position). getSubname());
        String fee=popularfoods.get(position).getFee().toString();
        holder.pri.setText(fee);
        if(!select.contains(position)){
            holder.frameLayout.setBackgroundColor(Color.parseColor("#283140"));

        }
        else{
            holder.frameLayout.setBackgroundColor(Color.parseColor("#0C5EF9"));

        }
        dialogs=new Dialog(context);
        dialogs.setContentView(R.layout.dialog);

        dialogs.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogs.setCancelable(false);
        dialogs.getWindow().getAttributes().windowAnimations=R.style.animation;
        ConstraintLayout submit=dialogs.findViewById(R.id.submit_dialog);
        TextView cancel=dialogs.findViewById(R.id.cancel_dailog);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select.contains(position)){
                    boolean sd=db.deletefood(popularfoods.get(position).getName());
                    if(sd==true){
                        select.remove(select.indexOf(position));
                        holder.frameLayout.setBackgroundColor(Color.parseColor("#283140"));

                    }
                    else{
                        Toast.makeText(context, "Already removed form Cart", Toast.LENGTH_SHORT).show();

                    }
                    listner.notficationonclick();


                }


                else{
                    food=new fooddb(popularfoods.get(position).getImage1(),popularfoods.get(position).getFee(),popularfoods.get(position).getName());
                    boolean rm= db.inseartfood(food);
                    if(rm==true){
                        select.add(position);
                        holder.frameLayout.setBackgroundColor(Color.parseColor("#0C5EF9"));
                    }
                    else{
                        Toast.makeText(context, "Already Added To Cart", Toast.LENGTH_SHORT).show();

                    }
                    listner.notficationonclick();


                }
                dialogs.dismiss();

            }

        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogs.dismiss();
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogs.show();


            }
        });
        if(!select2.contains(position)){
            holder.love.setImageTintList(ColorStateList.valueOf(Color.rgb(81, 86, 98)));


        }
        else{
            holder.love.setImageTintList(ColorStateList.valueOf(Color.rgb(241,68,67)));

        }
            holder.love.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if(select2.contains(position)){
                        boolean rm= db2.deletefood((popularfoods.get(position).getName()));
                        if(rm==true){
                            select2.remove(select2.indexOf(position));
                            holder.love.setImageTintList(ColorStateList.valueOf(Color.rgb(81, 86, 98)));


                        }
                        else{
                            Toast.makeText(context, "Already removed form Love List", Toast.LENGTH_SHORT).show();


                        }


                    }
                    else{
                        lovefood=new loveclass(popularfoods.get(position).getImage1(),popularfoods.get(position).getFee(),popularfoods.get(position).getName());
                        boolean sd = db2.inseartfood(lovefood);
                            if (sd == true) {
                                select2.add(position);
                                holder.love.setImageTintList(ColorStateList.valueOf(Color.rgb(241,68,67)));



                        }
                        else{

                                Toast.makeText(context, "Already Added To Love List", Toast.LENGTH_SHORT).show();

                        }

                    }
                }

            });




    }

    @Override
    public int getItemCount() {
        return popularfoods.size();
    }

    class viewholder extends RecyclerView.ViewHolder{
        ImageView img1,img2,love;
        TextView name,subname,pri;
        FrameLayout frameLayout;
        CardView card;
        ConstraintLayout add;

        public viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.image_popular_food);
            img2=itemView.findViewById(R.id.icon);
            name=itemView.findViewById(R.id.nameorder);
            subname=itemView.findViewById(R.id.sub_name_order);
            pri=itemView.findViewById(R.id.price);
            frameLayout=itemView.findViewById(R.id.frame);
            card=itemView.findViewById(R.id.cardViews);
            love=itemView.findViewById(R.id.love);
            add=itemView.findViewById(R.id.addtocaart);
            db=new mydatabase(context);
            db2=new mydatabase2(context);
        }
    }
}
