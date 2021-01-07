package com.example.binusezyfoody;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ImageButton drinksButton;
    private ImageButton snacksButton;
    private ImageButton foodsButton;
    private ImageButton topupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Untuk menggunakan custom toolbar
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.bar_layout);

//        Untuk menghilangkan back button karena tidak digunakan di activity ini
        ImageButton backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setVisibility(View.GONE);

//        untuk pindah ke Menu Category Activity dengan data dari masing masing kategori
        drinksButton = (ImageButton) findViewById(R.id.drinksBtn);
        drinksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategory("Drinks");
            }
        });

        snacksButton = (ImageButton) findViewById(R.id.snacksBtn);
        snacksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategory("Snacks");
            }
        });

        foodsButton = (ImageButton) findViewById(R.id.foodsBtn);
        foodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategory("Foods");
            }
        });

        topupButton = (ImageButton) findViewById(R.id.topupBtn);
        topupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCategory("Topup");
            }
        });

//        fungsi button pada toolbar
        Button barBtn = (Button) findViewById(R.id.homeBtn);
        barBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyOrder();
            }
        });


//        Untuk location button
        Button locationBtn = (Button) findViewById(R.id.locationBtn);

        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocation();
            }
        });

    }

    public void openCategory(String value){
        Intent intent = new Intent(this, MenuCategory.class);
        intent.putExtra("String", value);
        startActivity(intent);
    }

    public void openMyOrder(){
        Intent intent = new Intent(this, MyOrderActivity.class);
        startActivity(intent);
    }

    public void openLocation(){
        Intent intent = new Intent(this, MapsFragment.class);
        startActivity(intent);
    }

}