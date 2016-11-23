package com.trip.jogja.jogjatrip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class TourPackage extends AppCompatActivity {
    ImageView gk;
    ImageView yk;
    ImageView mrp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_package);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gk = (ImageView) findViewById(R.id.tGk);
        yk = (ImageView) findViewById(R.id.tYk);
        mrp = (ImageView) findViewById(R.id.tMr);

        gk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TourPackage.this,TourGk.class);
                startActivity(intent);
            }
        });

        yk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TourPackage.this,TourCity.class);
                startActivity(intent);
            }
        });

        mrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TourPackage.this,TourMerapi.class);
                startActivity(intent);
            }
        });

    }

}
