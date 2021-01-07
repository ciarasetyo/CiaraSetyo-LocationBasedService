package com.example.binusezyfoody;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

//import android.support.v4.app.FragmentActivity;

public class MapsFragment extends AppCompatActivity implements OnMapReadyCallback {

    public static Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    static float size = 15.0f;
    public static LatLng pickCoor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_maps);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.bar_layout);

//        Mengubah title toolbar
        TextView myTitleText = (TextView) findViewById(R.id.homeTitle);
        myTitleText.setText("Binus EzyFoody: Location");

//        Mengubah tulisan button pada pada toolbar
        Button barBtn = (Button) findViewById(R.id.homeBtn);
        barBtn.setText("See\nNearest");

        barBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNearest();
            }
        });

        ImageButton myImgBtn = (ImageButton) findViewById(R.id.backBtn);
        myImgBtn.setVisibility(View.VISIBLE);

        myImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainMenu();
            }
        });

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();

    }

    private void fetchLastLocation() {
        
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
           ActivityCompat.requestPermissions(this, new String[]
                   {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    currentLocation = location;

                    Toast.makeText(getApplicationContext(), currentLocation.getLatitude()
                    +""+currentLocation.getLongitude(),Toast.LENGTH_SHORT).show();

                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(MapsFragment.this);
                }
            }
        });
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        LatLng basCoor = new LatLng(-6.2247123,106.6480861);
        MarkerOptions markerOptions2 = new MarkerOptions().position(basCoor).title("Alam Sutera Branch");

        googleMap.animateCamera(CameraUpdateFactory.newLatLng(basCoor));
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(basCoor, 15.0f));
        googleMap.addMarker(markerOptions2);

        LatLng bkgCoor = new LatLng(-6.2019433,106.7787429);
        MarkerOptions markerOptions3 = new MarkerOptions().position(bkgCoor).title("Kemanggisan Branch");

        googleMap.animateCamera(CameraUpdateFactory.newLatLng(bkgCoor));
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bkgCoor, 15.0f));
        googleMap.addMarker(markerOptions3);

        LatLng bbkCoor = new LatLng(-6.2200771,106.997531);
        MarkerOptions markerOptions4 = new MarkerOptions().position(bbkCoor).title("Bekasi Branch");

        googleMap.animateCamera(CameraUpdateFactory.newLatLng(bbkCoor));
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(bbkCoor, 15.0f));
        googleMap.addMarker(markerOptions4);

        final LatLng currCoor = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(currCoor).icon(BitmapDescriptorFactory.defaultMarker(220)).title("Your Location");

        googleMap.animateCamera(CameraUpdateFactory.newLatLng(currCoor));
        googleMap.addMarker(markerOptions);

        if(pickCoor==null) {
            pickCoor = currCoor;
        }
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pickCoor, size));

        Button zoomBtn = (Button) findViewById(R.id.zoomBtn);

        zoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size += 1;
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pickCoor, size));
            }
        });

        Button shrinkBtn = (Button) findViewById(R.id.shrinkBtn);

        shrinkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size -= 1;
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(pickCoor, size));
            }
        });

        Button currLocBtn = (Button) findViewById(R.id.currLocBtn);

        currLocBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickCoor = currCoor;
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(pickCoor));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode){
            case REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    fetchLastLocation();
                }
                break;
        }
    }

    public void openMainMenu(){
        pickCoor = null;
        size = 15.0f;
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openNearest(){
        Intent intent = new Intent(this, NearestActivity.class);
        startActivity(intent);
    }
}