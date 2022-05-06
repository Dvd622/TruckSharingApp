package com.example.trucksharingapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NewDeliveryActivity extends AppCompatActivity {

    EditText receiverEditText, timeEditText, locationEditText;
    CalendarView calendarView;
    Button nextButton;
    Long date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_delivery);

        receiverEditText = findViewById(R.id.newDeliveryReceiverEditText);
        timeEditText = findViewById(R.id.newDeliveryTimeEditTextTime);
        locationEditText = findViewById(R.id.newDeliveryLocationEditText);
        calendarView = findViewById(R.id.newDeliveryCalendarView);
        nextButton = findViewById(R.id.newDeliveryNextButton);

        Intent intentReceive = getIntent();
        String sender = intentReceive.getStringExtra("sender");

        date = calendarView.getDate();

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String curYear = String.valueOf(year);
            String curMonth = String.valueOf(month+1);
            String curDate = String.valueOf(dayOfMonth);
            String dateString = curDate + "/" + curMonth + "/" + curYear;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH);
            try {
                Date date1 = simpleDateFormat.parse(dateString);
                assert date1 != null;
                date = date1.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        nextButton.setOnClickListener(view -> {
            if (receiverEditText.getText().toString().equals("")) {
                Toast.makeText(this, "Enter receiver name", Toast.LENGTH_SHORT).show();
            } else if (timeEditText.getText().toString().equals("")) {
                Toast.makeText(this, "Enter pickup time", Toast.LENGTH_SHORT).show();
            } else if (locationEditText.getText().toString().equals("")) {
                Toast.makeText(this, "Enter pickup location", Toast.LENGTH_SHORT).show();
            } else {
                String receiver, time, location;
                receiver = receiverEditText.getText().toString();
                time = timeEditText.getText().toString();
                location = locationEditText.getText().toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyy", Locale.ENGLISH);
                String dateString = simpleDateFormat.format(date);

                Intent intent = new Intent(NewDeliveryActivity.this, NewDeliveryDetailsActivity.class);
                intent.putExtra("receiver", receiver);
                intent.putExtra("time", time);
                intent.putExtra("location", location);
                intent.putExtra("date", date);
                intent.putExtra("sender", sender);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK && requestCode==1) {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
            finish();
        }
    }

}