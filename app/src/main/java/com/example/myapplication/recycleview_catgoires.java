package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.startActivity;

public class recycleview_catgoires extends RecyclerView.Adapter<recycleview_catgoires.viewholder> {
    @NonNull
    @NotNull
            private  final  OnitemRecycle listner;
    ArrayList<catgoirs>catgoirsArrayList;
    Context context;

    public recycleview_catgoires(@NonNull ArrayList<catgoirs> catgoirsArrayList,Context context,OnitemRecycle listner) {
        this.catgoirsArrayList = catgoirsArrayList;
        this.context=context;
        this.listner=listner;
    }

    @Override
    public viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.catgoire,parent,false);
        viewholder vh=new viewholder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull viewholder holder, int position) {
    holder.names.setText(catgoirsArrayList.get(position).getName());
    holder.picn.setImageResource(catgoirsArrayList.get(position).getPic());
    }

    @Override
    public int getItemCount() {
        return catgoirsArrayList.size();
    }

    class viewholder extends RecyclerView.ViewHolder{
        TextView names;
        ImageView picn;
        public viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            names=itemView.findViewById(R.id.Name);
            picn=itemView.findViewById(R.id.image_food);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 if(listner!=null){
                     int pos=getAdapterPosition();
                     if (pos!=RecyclerView.NO_POSITION){
                         listner.onitemClick(pos);
                     }
                 }

                }
            });
        }
    }
}
