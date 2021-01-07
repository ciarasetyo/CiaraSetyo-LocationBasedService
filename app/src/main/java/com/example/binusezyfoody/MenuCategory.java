package com.example.binusezyfoody;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MenuCategory extends AppCompatActivity {
    RecyclerView recyclerView;

    String name[], price[];
    int images[];

//    Data gambar masing masing kategori
    int images1[] = {R.drawable.mineralwater,R.drawable.applejuice,R.drawable.mangojuice,R.drawable.avocadojuice};
    int images2[] = {R.drawable.snack,};
    int images3[] = {R.drawable.foods};
    int images4[] = {R.drawable.topupicon};

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_category);

        recyclerView = findViewById(R.id.recyclerView);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.bar_layout);

//        Untuk menampilkan back button dan memberikan fungsinya
        ImageButton backBtn = (ImageButton) findViewById(R.id.backBtn);
        backBtn.setVisibility(View.VISIBLE);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });

//        Buat dapetin value dari intent di main activity supaya bisa ambil data sesuai kategorinya
        TextView myTitleText = (TextView) findViewById(R.id.homeTitle);

        Bundle extras = getIntent().getExtras();
        String value = extras.getString("String");

        switch (value){
            case "Drinks":
                myTitleText.setText("Binus EzyFoody: Drinks");

                name = getResources().getStringArray(R.array.drinksItem);
                price = getResources().getStringArray(R.array.drinksPrice);
                images = images1;
                break;
            case "Snacks":
                myTitleText.setText("Binus EzyFoody: Snacks");

                name = getResources().getStringArray(R.array.snacksItem);
                price = getResources().getStringArray(R.array.snacksPrice);
                images = images2;
                break;
            case "Foods":
                myTitleText.setText("Binus EzyFoody: Foods");

                name = getResources().getStringArray(R.array.foodsItem);
                price = getResources().getStringArray(R.array.foodsPrice);
                images = images3;
                break;
            case "Topup":
                myTitleText.setText("Binus EzyFoody: Top Up");

                name = getResources().getStringArray(R.array.topupItem);
                price = getResources().getStringArray(R.array.topupPrice);
                images = images4;
                break;
        }

//        menggunakan adapter untuk menggunakan recycler view
        AdapterMenu adapterMenu = new AdapterMenu(this, name, price, images, value);
        recyclerView.setAdapter(adapterMenu);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

//        Fungsi button pada toolbar
        Button barBtn = (Button) findViewById(R.id.homeBtn);
        barBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyOrder();
            }
        });
    }

    public void openMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openMyOrder(){
        Intent intent = new Intent(this, MyOrderActivity.class);
        startActivity(intent);
    }
}