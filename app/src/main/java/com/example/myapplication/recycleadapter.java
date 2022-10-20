package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class recycleadapter extends RecyclerView.Adapter<recycleadapter.viewholder> {
    @NonNull
    @NotNull
    private ArrayList<details>detailsArrayList;
    private Context context;
    notifactionclick listner;
    private final ArrayList<Integer>select=new ArrayList<>();
    mydatabase db;
    fooddb food;

    public recycleadapter(@NonNull ArrayList<details> detailsArrayList, Context context,notifactionclick listner) {
        this.detailsArrayList = detailsArrayList;
        this.context = context;
        this.listner=listner;
    }

    @Override
    public viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.burger,parent,false);
        viewholder vh=new viewholder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewholder holder, int position) {
        holder.name.setText(detailsArrayList.get(position).getName());
        holder.desc.setText(detailsArrayList.get(position).getDescription());
        holder.image.setImageResource(detailsArrayList.get(position).getPic());
        String fee=detailsArrayList.get(position).getFes().toString();
        holder.fe.setText(fee);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select.contains(position)){


                    boolean sd=db.deletefood(detailsArrayList.get(position).getName());
                    if(sd==true){
                        select.remove(select.indexOf(position));
                        holder.constraintLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.backgroundbutton));
                    }
                    else{
                        Toast.makeText(context, "Already removed form Cart", Toast.LENGTH_SHORT).show();

                    }
                    listner.notficationonclick();




                }


                else{

                     food=new fooddb(detailsArrayList.get(position).getPic(),detailsArrayList.get(position).getFes(),detailsArrayList.get(position).getName());

                    boolean rm= db.inseartfood(food);
                    if(rm==true){
                        select.add(position);
                        holder.constraintLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.backgroundbutton2));
                    }
                    else{
                        Toast.makeText(context, "Already Added To Cart", Toast.LENGTH_SHORT).show();

                    }
                    listner.notficationonclick();





                }


            }
        });

    }

    @Override
    public int getItemCount() {
        return detailsArrayList.size();
    }



    public class viewholder extends RecyclerView.ViewHolder{
        TextView name,desc,fe;
        ImageView image;
        ConstraintLayout constraintLayout;

        public viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nameburger);
            desc=itemView.findViewById(R.id.descriptionburger);
            image=itemView.findViewById(R.id.img);
            fe=itemView.findViewById(R.id.fem);
            constraintLayout=itemView.findViewById(R.id.cart);
            db=new mydatabase(context);

        }
    }


}
