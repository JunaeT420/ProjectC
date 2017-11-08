package com.example.user.projectc;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent i=getIntent();
        String house=i.getStringExtra("house");
        String road=i.getStringExtra("road");
        String area=i.getStringExtra("area");
        String city=i.getStringExtra("city");
        location=house + " " +road + " " +area+ " " +city;


    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getGeoLocation();

    }

    private void getGeoLocation() {
        List<Address> addressList=null;
        if (location !=null && !location.isEmpty()){
            Geocoder geocoder=new Geocoder(this);
            try {
                addressList= geocoder.getFromLocationName(location,1);
                Address address=addressList.get(0);


                String add=addressList.get(0).getAddressLine(0);
                String city =addressList.get(0).getLocality();
                String contry=addressList.get(0).getCountryName();
                String postalcode=addressList.get(0).getPostalCode();

                String totaol_address=""+add+",   "+city+",  " +contry+",  "+postalcode+"";


                LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title(totaol_address));

                mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(latLng, 15) );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
