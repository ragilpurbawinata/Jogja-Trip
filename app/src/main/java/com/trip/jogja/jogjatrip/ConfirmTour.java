package com.trip.jogja.jogjatrip;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConfirmTour extends AppCompatActivity {
    Button confirm;
    EditText et1,et2,et3,et4,et5;
    String tEmail, treEmail, tName, tPhone, tAcc, paketTour;
    String isiEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_tour);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        confirm = (Button) findViewById(R.id.btconfirmtour);
        et1 = (EditText) findViewById(R.id.tEmail);
        et2 = (EditText) findViewById(R.id.treEmail);
        et3 = (EditText) findViewById(R.id.tName);
        et4 = (EditText) findViewById(R.id.tPhone);
        et5 = (EditText) findViewById(R.id.tAcc);

        Intent i = getIntent();
        int kodeTour = i.getIntExtra("kdTour", 0);

        switch (kodeTour){
            case 1: paketTour = "Tour Gunung Kidul";break;
            case 2: paketTour = "Tour City";break;
            case 3: paketTour = "Tour Merapi";break;
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIsiEditText();
                kirimEmail();
                setKosongkan();
            }
        });

    }

    private void getIsiEditText(){
        tEmail = et1.getText().toString();
        treEmail = et2.getText().toString();
        tName = et3.getText().toString();
        tPhone = et4.getText().toString();
        tAcc = et5.getText().toString();
    }

    private void kirimEmail(){
        isiEmail = "Paket Tour : " + paketTour + "\n" +
                "Email : " + tEmail + "\n" +
                "Re-Email : " + treEmail + "\n" +
                "Name : " + tName + "\n" +
                "Phone : " + tPhone + "\n" +
                "Account Number : " + tAcc;

        String[] emailAdmin = {"trip.jogja@yahoo.com"};
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("mailto:"));
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_EMAIL, emailAdmin);
        i.putExtra(Intent.EXTRA_SUBJECT, "Tour Package");
        i.putExtra(Intent.EXTRA_TEXT, isiEmail);
        startActivity(i);
    }

    private void setKosongkan(){
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et1.requestFocus();
    }

}
