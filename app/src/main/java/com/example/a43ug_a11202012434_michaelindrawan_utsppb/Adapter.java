package com.example.a43ug_a11202012434_michaelindrawan_utsppb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.NamaViewHolder> {
    @NonNull
    @Override
    public NamaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.appearance_hw_store, viewGroup, false);
        return new NamaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NamaViewHolder namaViewHolder, int i) {
        String title = hw_list[i];
        String price = hw_price[i];
        int img = images[i];
        namaViewHolder.title.setText(title);
        namaViewHolder.price.setText(price);
        namaViewHolder.imgIcon.setImageResource(img);

    }

    @Override
    public int getItemCount() {
        return hw_list.length;
    }

    public class NamaViewHolder extends RecyclerView.ViewHolder{
        ImageView imgIcon;
        TextView title;
        TextView price;
        public NamaViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = (ImageView) itemView.findViewById(R.id.imageIcon);
            title = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }
    private String[] hw_list;
    private String[] hw_price;
    private int[] images;
    public Adapter(String[] hw_list, String[] hw_price, int[] images){
        this.hw_price = hw_price;
        this.hw_list = hw_list;
        this.images = images;
    }
}
