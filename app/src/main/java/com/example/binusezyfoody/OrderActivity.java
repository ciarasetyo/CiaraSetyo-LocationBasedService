package com.example.binusezyfoody;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    int images1[] = {R.drawable.mineralwater,R.drawable.applejuice,R.drawable.mangojuice,R.drawable.avocadojuice};
    int images2[] = {R.drawable.snack,};
    int images3[] = {R.drawable.foods};
    int images4[] = {R.drawable.topupicon};
    int images;

    public static List dataOrderImage = new ArrayList<Integer>();
    public static List dataOrderName = new ArrayList<String>();
    public static List dataOrderQty = new ArrayList<Integer>();
    public static List dataOrderPrice = new ArrayList<String>();
    public static List dataOrderPriceInt = new ArrayList<Integer>();
    int priceInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_order);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.bar_layout);

        TextView myTitleText = (TextView) findViewById(R.id.homeTitle);
        myTitleText.setText("Binus EzyFoody: Order");

//        Untuk menampilkan back button dan memberikan fungsinya
        ImageButton myImgBtn = (ImageButton) findViewById(R.id.backBtn);
        myImgBtn.setVisibility(View.VISIBLE);

        myImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });

//        Untuk mengambil data makanan yang dipilih
        Bundle extras = getIntent().getExtras();
        final String position = extras.getString("image");
        final String values = extras.getString("value");

        final ImageView imgMenu = (ImageView) findViewById(R.id.imageMenu);
        final TextView name = (TextView) findViewById(R.id.menuName);
        final TextView price = (TextView) findViewById(R.id.menuPrice);

        switch (values){
            case "Drinks":
                images = images1[Integer.parseInt(position)];
                String tempPriceInt = getResources().getStringArray(R.array.intDrinksPrice)[Integer.parseInt(position)];
                priceInt = Integer.parseInt(tempPriceInt);

                imgMenu.setImageResource(images1[Integer.parseInt(position)]);
                name.setText(getResources().getStringArray(R.array.drinksItem)[Integer.parseInt(position)]);
                price.setText(getResources().getStringArray(R.array.drinksPrice)[Integer.parseInt(position)]);
                break;
            case "Snacks":
                images = images2[Integer.parseInt(position)];
                String tempPriceInt2 = getResources().getStringArray(R.array.intSnacksPrice)[Integer.parseInt(position)];
                priceInt = Integer.parseInt(tempPriceInt2);

                imgMenu.setImageResource(images2[Integer.parseInt(position)]);
                name.setText(getResources().getStringArray(R.array.snacksItem)[Integer.parseInt(position)]);
                price.setText(getResources().getStringArray(R.array.snacksPrice)[Integer.parseInt(position)]);
                break;
            case "Foods":
                images = images3[Integer.parseInt(position)];
                String tempPriceInt3 = getResources().getStringArray(R.array.intFoodsPrice)[Integer.parseInt(position)];
                priceInt = Integer.parseInt(tempPriceInt3);

                imgMenu.setImageResource(images3[Integer.parseInt(position)]);
                name.setText(getResources().getStringArray(R.array.foodsItem)[Integer.parseInt(position)]);
                price.setText(getResources().getStringArray(R.array.foodsPrice)[Integer.parseInt(position)]);
                break;
            case "Topup":
                images = images4[Integer.parseInt(position)];
                String tempPriceInt4 = getResources().getStringArray(R.array.intTopupPrice)[Integer.parseInt(position)];
                priceInt = Integer.parseInt(tempPriceInt4);

                imgMenu.setImageResource(images4[Integer.parseInt(position)]);
                name.setText(getResources().getStringArray(R.array.topupItem)[Integer.parseInt(position)]);
                price.setText(getResources().getStringArray(R.array.topupPrice)[Integer.parseInt(position)]);
                break;
        }

//        fungsi tambah kurang qty
        ImageButton add = (ImageButton) findViewById(R.id.plusBtn);
        ImageButton subtract = (ImageButton) findViewById(R.id.minusBtn);
        final TextView qty = (TextView) findViewById(R.id.qty);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQty = Integer.parseInt(qty.getText().toString()) + 1;
                qty.setText(String.valueOf(newQty));
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQty = Integer.parseInt(qty.getText().toString()) -1;

                if (newQty<0){
                    return;
                }
                qty.setText(String.valueOf(newQty));
            }
        });

        Button orderMore = (Button) findViewById(R.id.orderMoreBtn);

        orderMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = (String) name.getText();
                String jumlah = (String) qty.getText();
                String harga = (String) price.getText();

//                Kembali ke activity sebelumnya
                openMenuCategory(images, nama, Integer.parseInt(jumlah), harga, priceInt);
            }
        });

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

    public void openMenuCategory(int gambar, String nama, int jumlah, String harga, int hargaInt){

        if(jumlah < 1){
            finish();
            return;
        }

        if(dataOrderName.contains(nama)){
            int index = dataOrderName.indexOf(nama);
            dataOrderQty.set(index, (int)dataOrderQty.get(index) + jumlah);
            finish();
            return;
        }
        dataOrderImage.add(gambar);
        dataOrderName.add(nama);
        dataOrderQty.add(jumlah);
        dataOrderPrice.add(harga);
        dataOrderPriceInt.add(hargaInt);
        finish();
    }

    public void openMyOrder(){
        Intent intent = new Intent(this, MyOrderActivity.class);
        startActivity(intent);
    }
}