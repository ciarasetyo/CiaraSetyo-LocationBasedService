package com.example.binusezyfoody;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyOrderActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.bar_layout);

        recyclerView = findViewById(R.id.recyclerView2);

//        Mengubah title toolbar
        TextView myTitleText = (TextView) findViewById(R.id.homeTitle);
        myTitleText.setText("Binus EzyFoody: My Order");

//        Mengubah tulisan button pada pada toolbar
        Button barBtn = (Button) findViewById(R.id.homeBtn);
        barBtn.setText("Pay Now");

//        Kalau blom ada yang diorder tombol tidak muncul
        if(OrderActivity.dataOrderName.size()==0){
            barBtn.setVisibility(View.GONE);
        }
        else{
            barBtn.setVisibility(View.VISIBLE);
        }

        barBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPay();
            }
        });

//        Untuk menampilkan back button dan memberikan fungsinya
        ImageButton myImgBtn = (ImageButton) findViewById(R.id.backBtn);
        myImgBtn.setVisibility(View.VISIBLE);

        myImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });

        for (int i=0; i<OrderActivity.dataOrderQty.size(); i++){
            int temp = (int) OrderActivity.dataOrderQty.get(i) * (int) OrderActivity.dataOrderPriceInt.get(i);
            total = total + temp;
        }

        TextView totalText = (TextView) findViewById(R.id.totalText);
        totalText.setText(String.valueOf(total));

//        Menggunakan adapter untuk recycler view
        AdapterMyOrder adapterMyOrder = new AdapterMyOrder(this, OrderActivity.dataOrderImage, OrderActivity.dataOrderName, OrderActivity.dataOrderQty, OrderActivity.dataOrderPrice, OrderActivity.dataOrderPriceInt, false);
        recyclerView.setAdapter(adapterMyOrder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void openMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openPay(){
        Intent intent = new Intent(this, CompleteActivity.class);
        startActivity(intent);
    }

}