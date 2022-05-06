package com.example.trucksharingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class NewDeliveryDetailsActivity extends AppCompatActivity {

    EditText otherGoodTypeEditText, otherVehicleTypeEditText, weightEditText, widthEditText, lengthEditText, heightEditText;
    RadioGroup goodTypeRadioGroup, vehicleTypeRadioGroup;
    RadioButton goodTypeRadioButton, vehicleTypeRadioButton;
    Button createOrderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_delivery_details);

        otherGoodTypeEditText = findViewById(R.id.newDeliveryDetailOtherGoodEditText);
        otherVehicleTypeEditText = findViewById(R.id.newDeliveryDetailOtherVehicleEditText);
        weightEditText = findViewById(R.id.newDeliveryDetailWeightEditText);
        widthEditText = findViewById(R.id.newDeliveryDetailWidthEditText);
        lengthEditText = findViewById(R.id.newDeliveryDetailLengthEditText);
        heightEditText = findViewById(R.id.newDeliveryDetailHeightEditText);
        goodTypeRadioGroup = findViewById(R.id.newDeliveryDetailGoodTypeRadioGroup);
        vehicleTypeRadioGroup = findViewById(R.id.newDeliveryDetailVehicleTypeRadioGroup);
        createOrderButton = findViewById(R.id.newDeliveryDetailCreateOrderButton);

        DatabaseHelper db = new DatabaseHelper(this);

        Intent intentReceive = getIntent();
        String receiver, time, location, sender;
        Long date;
        receiver = intentReceive.getStringExtra("receiver");
        time = intentReceive.getStringExtra("time");
        location = intentReceive.getStringExtra("location");
        sender = intentReceive.getStringExtra("sender");
        date = intentReceive.getLongExtra("date", Calendar.getInstance().getTime().getTime());

        createOrderButton.setOnClickListener(view -> {

            int goodTypeSelectedButtonId = goodTypeRadioGroup.getCheckedRadioButtonId();
            int vehicleTypeSelectedButtonId = vehicleTypeRadioGroup.getCheckedRadioButtonId();

            if (goodTypeSelectedButtonId == -1) {
                Toast.makeText(this, "Please select a good type", Toast.LENGTH_SHORT).show();
            } else if (vehicleTypeSelectedButtonId == -1) {
                Toast.makeText(this, "Please select a vehicle type", Toast.LENGTH_SHORT).show();
            } else if (weightEditText.getText().toString().equals("") || widthEditText.getText().toString().equals("") || lengthEditText.getText().toString().equals("") || heightEditText.getText().toString().equals("") ) {
                Toast.makeText(this, "Please enter all the required dimensions", Toast.LENGTH_SHORT).show();
            } else {
                goodTypeRadioButton = findViewById(goodTypeSelectedButtonId);
                vehicleTypeRadioButton = findViewById(vehicleTypeSelectedButtonId);

                int weight = Integer.parseInt(weightEditText.getText().toString());
                int width = Integer.parseInt(widthEditText.getText().toString());
                int length = Integer.parseInt(lengthEditText.getText().toString());
                int height = Integer.parseInt(heightEditText.getText().toString());

                // check for radio button selection, if other selected: get other text
                String goodType;
                if (goodTypeSelectedButtonId == R.id.newDeliveryDetailOtherGoodRadioButton) {
                    goodType = otherGoodTypeEditText.getText().toString();
                } else {
                    goodType = goodTypeRadioButton.getText().toString();
                }
                String vehicleType;
                if (vehicleTypeSelectedButtonId == R.id.newDeliveryDetailOtherVehicleRadioButton) {
                    vehicleType = otherVehicleTypeEditText.getText().toString();
                } else {
                    vehicleType = vehicleTypeRadioButton.getText().toString();
                }

                Delivery delivery = new Delivery(sender, receiver, date, time, location, goodType, vehicleType, weight, length, width, height);
                long result = db.insertDelivery(delivery);
                if (result > -1) {
                    Toast.makeText(this, "Order created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast.makeText(this, "Failed to make order, error: " + result, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}