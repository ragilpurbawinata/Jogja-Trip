package com.trip.jogja.jogjatrip;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map extends AppCompatActivity implements OnMapReadyCallback {
    LatLng bandara, destination;
    float jarak, biayaDollar;
    int biayaRupiah;
    Marker secondMarker = null;
    Button btnSubmit, btnCancel;
    Location awal, tujuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.maps);
        mapFragment.getMapAsync(this);

        btnSubmit = (Button) findViewById(R.id.btSubmit);
        btnCancel = (Button) findViewById(R.id.btCancel);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latTujuan = String.valueOf(tujuan.getLatitude());
                String lngTujuan = String.valueOf(tujuan.getLongitude());

                finish();
                Intent iSub = new Intent(Map.this, AirportPickUp.class);
                iSub.putExtra("hrRupiah",String.valueOf(biayaRupiah));
                iSub.putExtra("hrDollar",String.valueOf(biayaDollar));
                iSub.putExtra("korTujuan", latTujuan + "," + lngTujuan);
                startActivity(iSub);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        awal = new Location("awal");
        tujuan = new Location("tujuan");

        //penentuan koordinat bandara adi sucipto
        bandara = new LatLng(-7.788180, 110.431758);

        //menambahkan tanda pada poisi bandara
        googleMap.addMarker(new MarkerOptions()
                .position(bandara)
                .title("Starting Point")
                .snippet("Bandara Adi Sutjipto")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.airport)));

        //menempatkan posisi camera ke lokasi bandara
        CameraPosition cameraPosition = new CameraPosition.Builder().target(bandara).zoom(15).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        //menampilkan tombol zoom pada peta
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        //agar maptoolbar saat marker diklik tidak muncul
        googleMap.getUiSettings().setMapToolbarEnabled(false);

        googleMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if (secondMarker == null) {
                    secondMarker = googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(latLng.latitude, latLng.longitude))
                            .title("Your Destination")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.destination))
                            .draggable(true));
                } else {
                    secondMarker.remove();
                    secondMarker = googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(latLng.latitude, latLng.longitude))
                            .title("Your Destination")
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.destination))
                            .draggable(true));
                }

                awal.setLatitude(bandara.latitude);
                awal.setLongitude(bandara.longitude);
                tujuan.setLatitude(latLng.latitude);
                tujuan.setLongitude(latLng.longitude);
                jarak = awal.distanceTo(tujuan) / 1000;
                biayaRupiah = (int) (jarak * 8000);
                biayaDollar = biayaRupiah / 13000;


            }
        });

        googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                destination = marker.getPosition();

                awal.setLatitude(bandara.latitude);
                awal.setLongitude(bandara.longitude);
                tujuan.setLatitude(destination.latitude);
                tujuan.setLongitude(destination.longitude);
                jarak = awal.distanceTo(tujuan) / 1000;

                biayaRupiah = (int) (jarak * 8000);
                biayaDollar = biayaRupiah / 13000;
            }
        });
    }
}
