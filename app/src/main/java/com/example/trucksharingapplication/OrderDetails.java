package com.example.trucksharingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class OrderDetails extends AppCompatActivity {

    TextView senderTextView, receiverTextView, dateTextView, timeTextView, locationTextView, weightTextView, widthTextView, heightTextView, lengthTextView, goodTypeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        senderTextView = findViewById(R.id.orderDetailsSenderTextView);
        receiverTextView = findViewById(R.id.orderDetailsReceiverTextView);
        dateTextView = findViewById(R.id.orderDetailsDateTextView);
        timeTextView = findViewById(R.id.orderDetailsTimeTextView);
        locationTextView = findViewById(R.id.orderDetailsLocationTextView);
        weightTextView = findViewById(R.id.orderDetailsWeightTextView);
        widthTextView = findViewById(R.id.orderDetailsWidthTextView);
        heightTextView = findViewById(R.id.orderDetailsHeightTextView);
        lengthTextView = findViewById(R.id.orderDetailslengthTextView);
        goodTypeTextView = findViewById(R.id.orderDetailsGoodTypeTextView);

        Intent intentReceive = getIntent();
        String sender = "Sender: " + intentReceive.getStringExtra("sender");
        String receiver = "Receiver: " + intentReceive.getStringExtra("receiver");
        Long date = intentReceive.getLongExtra("date", 0);
        String time = "Pickup time: " + intentReceive.getStringExtra("time");
        String location = "Pickup location: " + intentReceive.getStringExtra("location");
        String goodType = "Good type: \n" + intentReceive.getStringExtra("goodType");
        int weight = intentReceive.getIntExtra("weight", 0);
        int width = intentReceive.getIntExtra("width", 0);
        int height = intentReceive.getIntExtra("height", 0);
        int length = intentReceive.getIntExtra("length", 0);
        String weightString = "Weight: \n" + Integer.toString(weight);
        String widthString = "Width: \n" + Integer.toString(width);
        String heightString = "Height: \n" + Integer.toString(height);
        String lengthString = "Length: \n" + Integer.toString(length);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String dateString = "Pickup date: " + simpleDateFormat.format(date).toString();

        senderTextView.setText(sender);
        receiverTextView.setText(receiver);
        dateTextView.setText(dateString);
        timeTextView.setText(time);
        locationTextView.setText(location);
        weightTextView.setText(weightString);
        widthTextView.setText(widthString);
        heightTextView.setText(heightString);
        lengthTextView.setText(lengthString);
        goodTypeTextView.setText(goodType);
    }
}