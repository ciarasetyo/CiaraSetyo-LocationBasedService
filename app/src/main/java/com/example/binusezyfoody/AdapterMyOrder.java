package com.example.binusezyfoody;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterMyOrder extends RecyclerView.Adapter<AdapterMyOrder.MyViewHolder> {

    List data1, data2, data3, data4, data5;
    Boolean complete;

    Context context;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public AdapterMyOrder(Context ct, List gambar, List nama, List jumlah, List harga, List hargaInt, Boolean selesai){
        context = ct;
        data1 = gambar;
        data2= nama;
        data3 = jumlah;
        data4 = harga;
        data5 = hargaInt;
        complete = selesai;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_my_order, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        int total = (int) data3.get(position) * (int) data5.get(position);
        holder.myText1.setText((CharSequence) data2.get(position));
        holder.myText2.setText(data3.get(position) + " x " + data4.get(position) + " = " + total);
        holder.myImage.setImageResource((Integer) data1.get(position));

//        Fungsi ketika tombol hapus ditekan
        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData(position);
            }
        });

//        kalau activity myOrder maka ada tombol hapus kalau activity complete tidak ada
        if(complete==false){
            holder.removeBtn.setVisibility(View.VISIBLE);
        }
        else{
            holder.removeBtn.setVisibility(View.GONE);
        }
    }

//    Hapus semua data ketika sudah tekan tombol pay now
    public void removeData(int position){
        OrderActivity.dataOrderImage.remove(position);
        OrderActivity.dataOrderName.remove(position);
        OrderActivity.dataOrderQty.remove(position);
        OrderActivity.dataOrderPrice.remove(position);
        OrderActivity.dataOrderPriceInt.remove(position);
        Intent intent = new Intent(context, MyOrderActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView myText1, myText2;
        ImageView myImage, tes;
        Button removeBtn;

        public MyViewHolder (@NonNull View itemView){
            super(itemView);
            myText1 = itemView.findViewById(R.id.textMenu);
            myText2 = itemView.findViewById(R.id.textMenuDetail);
            myImage = itemView.findViewById(R.id.imageMenu);
            removeBtn = itemView.findViewById(R.id.removeBtn);
        }
    }
}
