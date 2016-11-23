package com.trip.jogja.jogjatrip;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ConfirmPickUp extends AppCompatActivity {
    Button confirm;
    EditText et1,et2,et3,et4,et5,et6;
    String pTgl, pJam, pTujuan, pEmail, preEmail, pName, pFlight, pPhone, pAcc;
    String isiEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_pick_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        confirm = (Button) findViewById(R.id.btconfirmpickup);
        et1 = (EditText) findViewById(R.id.pickEmail);
        et2 = (EditText) findViewById(R.id.pickreEmail);
        et3 = (EditText) findViewById(R.id.pickName);
        et4 = (EditText) findViewById(R.id.pickFlight);
        et5 = (EditText) findViewById(R.id.pickPhone);
        et6 = (EditText) findViewById(R.id.pickAcc);

        Intent i = getIntent();
        pTgl = i.getStringExtra("tgl");
        pJam = i.getStringExtra("jam");
        pTujuan = i.getStringExtra("tujuan");

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
        pEmail = et1.getText().toString();
        preEmail = et2.getText().toString();
        pName = et3.getText().toString();
        pFlight = et4.getText().toString();
        pPhone = et5.getText().toString();
        pAcc = et6.getText().toString();
    }

    private void kirimEmail(){
        isiEmail = "Destination : " + pTujuan + "\n" +
                "Date : " + pTgl + "\n" +
                "Time : " + pJam + "\n" +
                "Email : " + pEmail + "\n" +
                "Re-Email : " + preEmail + "\n" +
                "Name : " + pName + "\n" +
                "Flight Number : " + pFlight + "\n" +
                "Phone : " + pPhone + "\n" +
                "Account Number : " + pAcc;

        String[] emailAdmin = {"trip.jogja@yahoo.com"};
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setData(Uri.parse("mailto:"));
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_EMAIL, emailAdmin);
        i.putExtra(Intent.EXTRA_SUBJECT, "Airport PickUp");
        i.putExtra(Intent.EXTRA_TEXT, isiEmail);
        startActivity(i);
    }

    private void setKosongkan(){
        et1.setText(null);
        et2.setText(null);
        et3.setText(null);
        et4.setText(null);
        et5.setText(null);
        et6.setText(null);
        et1.requestFocus();
    }

}
