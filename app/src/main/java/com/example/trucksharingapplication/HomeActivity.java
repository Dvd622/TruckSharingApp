package com.example.trucksharingapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements DeliveryAdapter.OnDeliveryClickListener {

    RecyclerView deliveryRecyclerView;
    DeliveryAdapter deliveryAdapter;
    List<Delivery> deliveryList;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    ImageButton addDeliveryImageButton;
    String user;
    Intent intentReceive = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        addDeliveryImageButton = findViewById(R.id.addDeliveryImageButton);

        intentReceive = getIntent();
        user = intentReceive.getStringExtra("user");

        DatabaseHelper db = new DatabaseHelper(this);

        deliveryRecyclerView = findViewById(R.id.deliveryRecyclerView);

        deliveryList = db.fetchAllDelivery();
        deliveryAdapter = new DeliveryAdapter( HomeActivity.this, deliveryList, this);
        deliveryRecyclerView.setAdapter(deliveryAdapter);
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        deliveryRecyclerView.setLayoutManager(recyclerViewLayoutManager);

        addDeliveryImageButton.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, NewDeliveryActivity.class);
            intent.putExtra("sender", user);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK && requestCode==1) {
            DatabaseHelper db = new DatabaseHelper(this);
            deliveryList = db.fetchAllDelivery();
            deliveryAdapter = new DeliveryAdapter( HomeActivity.this, deliveryList, this);
            deliveryRecyclerView.setAdapter(deliveryAdapter);
            recyclerViewLayoutManager = new LinearLayoutManager(this);
            deliveryRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        }
    }

    @Override
    public void onDeliveryClick(int position) {
        Intent intent = new Intent(HomeActivity.this, OrderDetails.class);
        intent.putExtra("sender", deliveryList.get(position).getSender());
        intent.putExtra("receiver", deliveryList.get(position).getReceiver());
        intent.putExtra("date", deliveryList.get(position).getDate());
        intent.putExtra("time", deliveryList.get(position).getTime());
        intent.putExtra("location",deliveryList.get(position).getLocation());
        intent.putExtra("goodType", deliveryList.get(position).getGoodType());
        intent.putExtra("weight", deliveryList.get(position).getWeight());
        intent.putExtra("width", deliveryList.get(position).getWidth());
        intent.putExtra("height", deliveryList.get(position).getHeight());
        intent.putExtra("length", deliveryList.get(position).getLength());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemSelected = item.getItemId();
        if (itemSelected == R.id.homeMenu) {
            DatabaseHelper db = new DatabaseHelper(this);
            deliveryRecyclerView = findViewById(R.id.deliveryRecyclerView);
            deliveryList = db.fetchAllDelivery();
            deliveryAdapter = new DeliveryAdapter( HomeActivity.this, deliveryList, this);
            deliveryRecyclerView.setAdapter(deliveryAdapter);
            recyclerViewLayoutManager = new LinearLayoutManager(this);
            deliveryRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        } else if (itemSelected == R.id.myOrdersMenu) {
            user = intentReceive.getStringExtra("user");
            DatabaseHelper db = new DatabaseHelper(this);
            deliveryRecyclerView = findViewById(R.id.deliveryRecyclerView);
            deliveryList = db.fetchAllDelivery();
            List<Delivery> myDeliveryList = new ArrayList<>();
            for (Delivery delivery:deliveryList) {
                if (delivery.getSender().equals(user)) {
                    myDeliveryList.add(delivery);
                }
            }
            deliveryAdapter = new DeliveryAdapter( HomeActivity.this, myDeliveryList, this);
            deliveryRecyclerView.setAdapter(deliveryAdapter);
            recyclerViewLayoutManager = new LinearLayoutManager(this);
            deliveryRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        }
        return super.onOptionsItemSelected(item);
    }
}