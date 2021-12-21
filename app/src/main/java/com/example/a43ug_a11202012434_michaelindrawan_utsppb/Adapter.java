package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    String data1[],data2 [],data3 [],data4 [],data5 [],data6[];
    int images[];
    Context context;

    public Adapter(Context ct,String s1[],String s2[],String s3[],String s4[],String s5[],String s6[],int img[]){
        context=ct;
        data1=s1;
        data2=s2;
        data3=s3;
        data4=s4;
        data5=s5;
        data6=s6;
        images=img;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.appearance_hw_store,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myImage.setImageResource(images[position]);
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context,Info.class);
                intent.putExtra("nama",data1[position]);
                intent.putExtra("debut",data3[position]);
                intent.putExtra("prod",data4[position]);
                intent.putExtra("desg",data5[position]);
                intent.putExtra("desc",data6[position]);
                intent.putExtra("myImage",images[position]);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView myText1, myText2;
        ImageView myImage;
        Button mainLayout;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            myText1=itemView.findViewById(R.id.title);
            myText2=itemView.findViewById(R.id.price);
            myImage=itemView.findViewById(R.id.imageIcon);
            mainLayout=itemView.findViewById(R.id.info_hw);

        }
    }
}
