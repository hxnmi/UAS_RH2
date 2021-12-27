package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    String data1[],data2 [],data3 [],data4 [],data5 [],data6[];
    int images[],data7[];
    double data8[];
    Context context;

    public Adapter(Context ct,String s1[],String s2[],String s3[],String s4[],String s5[],String s6[],int s7[],int img[], double s8[]){
        context=ct;
        data1=s1;
        data2=s2;
        data3=s3;
        data4=s4;
        data5=s5;
        data6=s6;
        data7=s7;
        images=img;
        data8= s8;
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
        if (data7[position]!=0){
            holder.jumlahBeli.setText(String.valueOf(data7[position]));
        }
        holder.jumlahBeli.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String jumlahBeli = holder.jumlahBeli.getText().toString();
                if (jumlahBeli.length()==0){
                    data7[position] = 0;
                }

                else if (jumlahBeli.compareTo(String.valueOf(0))==0){
                    holder.jumlahBeli.setText("");
                    data7[position] = 0;
                }

                else {
                    data7[position] = Integer.parseInt(jumlahBeli);
                }


            }
        });
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

    public boolean checkOutValidate(){
        if(checkOutPrice()==0){
            return false;
        }
        else {
            return true;
        }
    }

    public double checkOutPrice(){
        double totalBelanjaan = 0 ;
        for(int i = 0; i<data8.length; i++){
            totalBelanjaan += data7[i]*data8[i];
        }
        return totalBelanjaan;
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView myText1, myText2;
        ImageView myImage;
        Button mainLayout;
        EditText jumlahBeli;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            myText1=itemView.findViewById(R.id.title);
            myText2=itemView.findViewById(R.id.price);
            myImage=itemView.findViewById(R.id.imageIcon);
            mainLayout=itemView.findViewById(R.id.info_hw);
            jumlahBeli=(EditText) itemView.findViewById(R.id.jumlahBeli);

        }
    }
}
