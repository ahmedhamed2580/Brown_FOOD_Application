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
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;



public class loverecycle extends RecyclerView.Adapter<loverecycle.viewholder> {
    mydatabase db;
    mydatabase2 db2;
    exitclickfilter listnerfilter;
    @NonNull
    @NotNull

   public static ArrayList<loveclass>lovefoodarray;
    Context context;

    notifactionclick listner;


    public loverecycle(@NonNull ArrayList<loveclass> lovefoodarray, Context context,notifactionclick listner,exitclickfilter listnerfilter) {
        this.lovefoodarray = lovefoodarray;
        this.context = context;
        this.listner=listner;
        this.listnerfilter=listnerfilter;
    }
    @Override
    public viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.love,parent,false);
        loverecycle.viewholder vh=new loverecycle.viewholder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewholder holder, int position) {
        holder.imagelovefood.setImageResource(lovefoodarray.get(position).getImg());
        String fee=lovefoodarray.get(position).getPrice().toString();
        holder.pricelove.setText(fee);
        holder.namelove.setText(lovefoodarray.get(position).getName());
        holder.btnaddlove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fooddb food=new fooddb(lovefoodarray.get(position).getImg(),lovefoodarray.get(position).getPrice(),lovefoodarray.get(position).getName());
                db.inseartfood(food);
                listner.notficationonclick();
            }
        });
        holder.delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listnerfilter.onexitclick(lovefoodarray,position);






            }
        });
        setanimation(holder.itemView,position);


    }
    public static void filteredlist(ArrayList<loveclass>filteredlist) {
        lovefoodarray=filteredlist;





    }



    @Override
    public int getItemCount() {
        return lovefoodarray.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        TextView namelove,pricelove;
        ImageView imagelovefood,btnaddlove,delete2;
        public viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            namelove=itemView.findViewById(R.id.namelove);
            pricelove=itemView.findViewById(R.id.pricelove);
            imagelovefood=itemView.findViewById(R.id.lovefood);
            btnaddlove=itemView.findViewById(R.id.addcartlove);
            db=new mydatabase(context);
            db2=new mydatabase2(context);
            delete2=itemView.findViewById(R.id.delete2);
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
