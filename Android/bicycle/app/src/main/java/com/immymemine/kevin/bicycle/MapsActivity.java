package com.immymemine.kevin.bicycle;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.immymemine.kevin.bicycle.domain.Json;
import com.immymemine.kevin.bicycle.domain.Row;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        load();
    }

    private void load() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                return Remote.getData("http://openAPI.seoul.go.kr:8088/7673796b5571756633387057415648/json/GeoInfoBikeConvenientFacilitiesWGS/1/100");
            }

            @Override
            protected void onPostExecute(String s) {
                Gson gson = new Gson();
                Json json = gson.fromJson(s, Json.class);
                rows = json.getGeoInfoBikeConvenientFacilitiesWGS().getRow();
                mapFragment.getMapAsync(MapsActivity.this);
            }
        }.execute();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    //좌표 데이터를 저장하기 위한 저장소
    Row[] rows = null;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng point = null;

        for(Row row : rows) {
            point = new LatLng(Double.parseDouble(row.getLAT()),
                    Double.parseDouble(row.getLNG()));
            mMap.addMarker(new MarkerOptions().position(point).title(row.getCLASS()));
        }
        point = new LatLng(37.5399344, 126.9737224);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 10));
    }
}
