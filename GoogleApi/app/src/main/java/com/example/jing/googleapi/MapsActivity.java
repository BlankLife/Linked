package com.example.jing.googleapi;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleMap mMap;
    private Marker mMarker;
    GoogleApiClient mGoogleApiClient;
    private Circle c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        initMap();


        Button Zin = (Button) findViewById(R.id.button3);
        Zin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.zoomTo(mMap.getCameraPosition().zoom + 1));
            }
        });
        Button Zout = (Button) findViewById(R.id.button4);
        Zout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.animateCamera(CameraUpdateFactory.zoomTo(mMap.getCameraPosition().zoom - 1));
            }
        });
    }

    private void initMap() {
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // goToLocation(36.1070, -115.1424);
        // mMap.addMarker().
      /* if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        //when I open the map, automatically set my current location
        mMap.setMyLocationEnabled(true);
        */
        mGoogleApiClient = new GoogleApiClient.Builder(this).addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mGoogleApiClient.connect();
    }

    private void goToLocation(double lat, double lng) {
        LatLng ll = new LatLng(lat, lng);

        if(c == null)
        {
            c = mMap.addCircle(new CircleOptions().center(new
                    LatLng(lat,lng))
                    .radius(1000).strokeColor(Color.parseColor("#500084d3")).fillColor(Color.parseColor("#500084d3")));
            MarkerOptions m = new MarkerOptions()
                    .position(new LatLng(lat,lng))
                    .title("Current Location");
            mMarker = mMap.addMarker(m);
        }
        else
            updateCircle(new LatLng(lat,lng));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 13.5f));
    }

    public void geoLocate(View view) throws IOException {
        EditText et = (EditText) findViewById(R.id.editText2);
        String location = et.getText().toString();

        Geocoder gc = new Geocoder(this);
        List<android.location.Address> l = gc.getFromLocationName(location, 1);
        android.location.Address address = l.get(0);
        String locality = address.getLocality();

        Toast.makeText(this, locality, Toast.LENGTH_LONG).show();

        double lat = address.getLatitude();
        double lng = address.getLongitude();

        //update the circle and marker
        if(c == null)
        {
            c = mMap.addCircle(new CircleOptions().center(new
                    LatLng(lat,lng))
                    .radius(1000).strokeColor(Color.parseColor("#500084d3")).fillColor(Color.parseColor("#500084d3")));
            MarkerOptions m = new MarkerOptions()
                        .position(new LatLng(lat,lng))
                        .title("Current Location");
                mMarker = mMap.addMarker(m);
        }
        else
            updateCircle(new LatLng(lat,lng));

        goToLocation(lat, lng);
    }

    LocationRequest mLocationRequest;

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(100000);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if(location == null){
            Toast.makeText(this, "Can't get current location", Toast.LENGTH_LONG).show();
        }
        else{
            LatLng ll = new LatLng(location.getLatitude(),location.getLongitude());
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, 13.5f);
            mMap.animateCamera(update);
            if(c == null)
            {
                c = mMap.addCircle(new CircleOptions().center(new
                        LatLng(location.getLatitude(),location.getLongitude()))
                        .radius(1000).strokeColor(Color.parseColor("#500084d3")).fillColor(Color.parseColor("#500084d3")));
                MarkerOptions m = new MarkerOptions()
                        .position(new LatLng(location.getLatitude(),location.getLongitude()))
                        .title("Current Location");
                mMarker = mMap.addMarker(m);
            }
            else
                updateCircle(new LatLng(location.getLatitude(),location.getLongitude()));
        }
    }

    private void updateCircle(LatLng pos) {
       c.setCenter(pos);
       mMarker.setPosition(pos);
    }


}
