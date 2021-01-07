package com.example.binusezyfoody;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.MyViewHolder> {

    String data1[], data2[], valueName;
    int images[];
    Context context;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public AdapterMenu(Context ct, String nama[], String harga[], int img[], String value){
        context = ct;
        data1 = nama;
        data2= harga;
        images = img;
        valueName = value;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_menu_category, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.myText1.setText((data1[position]));
        holder.myText2.setText((data2[position]));
        holder.myImage.setImageResource((images[position]));

//        Untuk pindah ke orderActivity
        holder.myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrder(position);
            }
        });
    }

    public void openOrder(int position){
        Intent intent = new Intent(context, OrderActivity.class);
        intent.putExtra("image", String.valueOf(position));
        intent.putExtra("value", valueName);
        context.startActivity(intent);

    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView myText1, myText2;
        ImageView myImage;

        public MyViewHolder (@NonNull View itemView){
            super(itemView);
            myText1 = itemView.findViewById(R.id.textMenu);
            myText2 = itemView.findViewById(R.id.textMenuPrice);
            myImage = itemView.findViewById(R.id.imageMenu);
        }
    }
}
