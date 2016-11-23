package com.trip.jogja.jogjatrip;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AirportPickUp extends AppCompatActivity{
    int hour, minutes, mYear,mMonth, mDay;
    static final int TIME_DIALOG_ID = 0;
    static final int DATE_DIALOG_ID = 1;
    private EditText txtDate;
    private EditText txtTime;
    Button bookPickup, btnDes;
    TextView hrgDollar, hrgRupiah;
    String nRupiah, nDollar = "0", nkorTujuan;
    String sdate, stime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_pick_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookPickup = (Button) findViewById(R.id.btpickup);
        btnDes = (Button) findViewById(R.id.btDestination);
        hrgDollar = (TextView) findViewById(R.id.hargaDolar);
        hrgRupiah = (TextView) findViewById(R.id.hargaRupiah);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        showDialogOnEditDateClick();
        showDialogOnEditTimeClick();

        btnDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent iMap = new Intent(AirportPickUp.this, Map.class);
                startActivity(iMap);
            }
        });

        Intent i = getIntent();
        nRupiah = i.getStringExtra("hrRupiah");
        nDollar = i.getStringExtra("hrDollar");
        nkorTujuan = i.getStringExtra("korTujuan");

        hrgRupiah.setText(nRupiah);
        hrgDollar.setText(nDollar);

        bookPickup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AirportPickUp.this, ConfirmPickUp.class);
                intent.putExtra("tgl", sdate);
                intent.putExtra("jam", stime);
                intent.putExtra("tujuan", nkorTujuan);
                startActivity(intent);
            }
        });

    }

    public void showDialogOnEditDateClick(){
        txtDate = (EditText) findViewById(R.id.editTextDate);
        txtDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showDialog(DATE_DIALOG_ID);
                return true;
            }
        });
    }

    public void showDialogOnEditTimeClick(){
        txtTime = (EditText) findViewById(R.id.editTextTime);
        txtTime.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                showDialog(TIME_DIALOG_ID);
                return true;
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id){
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dpickerListener, mYear, mMonth, mDay);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, tpickerListener, hour, minutes, true);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear + 1;
            mDay = dayOfMonth;
            sdate = mDay + "/" + mMonth + "/" + mYear;
            txtDate.setText(sdate);
        }
    };

    private TimePickerDialog.OnTimeSetListener tpickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hour = hourOfDay;
            minutes = minute;
            if(hour < 10) {
                if (minutes < 10)
                    stime = "0" + hour + ":" + "0" + minutes;
                else
                    stime = "0" + hour + ":" + minutes;
            }
            else if(minutes < 10)
                stime = hour + ":" + "0" + minutes;
            else
                stime = hour + ":" + minutes;

            txtTime.setText(stime);
        }
    };
}