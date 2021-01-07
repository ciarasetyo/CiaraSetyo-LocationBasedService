package com.example.binusezyfoody;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class NearestActivity extends AppCompatActivity {
    ListView nearestList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.bar_layout);

//        Mengubah title toolbar
        TextView myTitleText = (TextView) findViewById(R.id.homeTitle);
        myTitleText.setText("Binus EzyFoody: Nearest");

//        Mengubah tulisan button pada pada toolbar
        Button barBtn = (Button) findViewById(R.id.homeBtn);
        barBtn.setText("Map");

        barBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });


        nearestList = (ListView) findViewById(R.id.nearestList);

        final ArrayList<String> dataNearest = new ArrayList<>();
        final ArrayList<Double> dataNearestX = new ArrayList<>();
        final ArrayList<Double> dataNearestY = new ArrayList<>();
        final ArrayList<Float> dataNearestDistance = new ArrayList<>();
//        final ArrayList<Integer> dataNearestIndex = new ArrayList<>();
        final ArrayList<String> dataFinal = new ArrayList<>();

        for(int i=0; i<getResources().getStringArray(R.array.address).length; i++){
            dataNearest.add(getResources().getStringArray(R.array.address)[i]);
            dataNearestX.add(Double.parseDouble(getResources().getStringArray(R.array.coordinateX)[i]));
            dataNearestY.add(Double.parseDouble(getResources().getStringArray(R.array.coordinateY)[i]));

            float result[] = new float[10];
            Location.distanceBetween(MapsFragment.currentLocation.getLatitude(), MapsFragment.currentLocation.getLongitude(), dataNearestX.get(i), dataNearestY.get(i), result);
            dataNearestDistance.add(result[0]/1000);
        }
        final int[] index = argsort(dataNearestDistance);
        for(int i=0; i<getResources().getStringArray(R.array.address).length; i++){

            String input = "Branch = " + dataNearest.get(index[i]) +
                    "\nCoordinate = (" + dataNearestX.get(index[i]) +" , "+ dataNearestY.get(index[i]) +
                    ")\nDistance = " + dataNearestDistance.get(index[i]) + " KM";

            dataFinal.add(input);
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataFinal);
        nearestList.setAdapter(arrayAdapter);

        nearestList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(NearestActivity.this,"Clicked item: "+ dataNearest.get(index[position]), Toast.LENGTH_SHORT).show();

                MapsFragment.pickCoor = new LatLng(dataNearestX.get(index[position]), dataNearestY.get(index[position]));

                openMap();
            }
        });
    }
    public void openMap(){
        Intent intent = new Intent(this, MapsFragment.class);
        startActivity(intent);
    }

    public int[] argsort(ArrayList<Float> distance){
        int[] index = new int[distance.size()];

        for (int i=0; i<distance.size(); i++){
            index[i] = i;
        }

        for(int i=0; i<distance.size(); i++){
            for(int j=i+1; j<distance.size(); j++){
                if(distance.get(i)>distance.get(j)){
                    int temp = index[i];
                    index[i] = index[j];
                    index[j] = temp;
                }
            }
        }

        return index;
    }
}