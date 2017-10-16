/**
 * Created by PENGFEI XU on 2017.
 */
package com.example.android.aurin_android_pengfeixu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    // define functions for controllers.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        if ("action".equals(intent.getAction())) {

            Capabilities cap = (Capabilities)intent.getSerializableExtra("capobj");
            Picked_City.cap_picked = cap;

            TextView title = (TextView) findViewById(R.id.title_text);
            title.setText(cap.title);

            TextView org = (TextView) findViewById(R.id.org_text);
            org.setText(cap.organization);

            TextView abstracts = (TextView) findViewById(R.id.abstract_text);
            abstracts.setText(cap.abstracts);

            TextView lowla = (TextView) findViewById(R.id.lowerla_text);
            lowla.setText(cap.bbox.getLowerLa().toString());

            TextView hila = (TextView) findViewById(R.id.higherla_text);
            hila.setText(cap.bbox.getHigherLa().toString());

            TextView lowlo = (TextView) findViewById(R.id.lowerlo_text);
            lowlo.setText(cap.bbox.getLowerLon().toString());

            TextView hilo = (TextView) findViewById(R.id.higherlo_text);
            hilo.setText(cap.bbox.getHigherLon().toString());

            ImageButton showmap = (ImageButton) findViewById(R.id.show_map);
            showmap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DetailActivity.this,Map_Filter.class);
                    startActivity(intent);
                }
            });

        }

    }

    // map fragment
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        double lla = Picked_City.cap_picked.bbox.getLowerLa();
        double hla = Picked_City.cap_picked.bbox.getHigherLa();
        double llo = Picked_City.cap_picked.bbox.getLowerLon();
        double hlo = Picked_City.cap_picked.bbox.getHigherLon();

        LatLng center = new LatLng((lla+hla)/2.0,(llo+hlo)/2.0);
        int zoom = (int) Math.log(210/(hlo - llo)) + 1;
        mMap.addMarker(new MarkerOptions().position(center).title("Marker in place"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(center));
        mMap.isMyLocationEnabled();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, zoom));

        // Polylines are useful for marking paths and routes on the map.
        mMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(lla, llo))
                .add(new LatLng(hla, llo))
                .add(new LatLng(hla, hlo))
                .add(new LatLng(lla, hlo))
                .add(new LatLng(lla, llo))
        );
    }

}
