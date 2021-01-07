package com.example.binusezyfoody;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class CompleteActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.bar_layout);


        recyclerView = findViewById(R.id.recyclerView2);

        TextView myTitleText = (TextView) findViewById(R.id.homeTitle);
        myTitleText.setText("Binus EzyFoody:Order Complete");

        Button barBtn = (Button) findViewById(R.id.homeBtn);
        barBtn.setText("Menu\nUtama");
        barBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });

        //        Untuk menampilkan back button dan memberikan fungsinya
        ImageButton myImgBtn = (ImageButton) findViewById(R.id.backBtn);
        myImgBtn.setVisibility(View.GONE);

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

        AdapterMyOrder adapterMyOrder = new AdapterMyOrder(this, OrderActivity.dataOrderImage, OrderActivity.dataOrderName, OrderActivity.dataOrderQty, OrderActivity.dataOrderPrice, OrderActivity.dataOrderPriceInt, true);
        recyclerView.setAdapter(adapterMyOrder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void openMainMenu(){
        OrderActivity.dataOrderImage.clear();
        OrderActivity.dataOrderName.clear();
        OrderActivity.dataOrderQty.clear();
        OrderActivity.dataOrderPrice.clear();
        OrderActivity.dataOrderPriceInt.clear();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}