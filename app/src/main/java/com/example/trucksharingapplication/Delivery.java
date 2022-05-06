package com.example.trucksharingapplication;

public class Delivery {
    int deliveryId;
    String sender, receiver, goodType, vehicleType, time, location;
    Long date;
    Integer weight, length, width, height;

    public Delivery(String sender, String receiver, Long date, String time, String location, String goodType, String vehicleType, Integer weight, Integer length, Integer width, Integer height) {
        this.sender = sender;
        this.receiver = receiver;
        this.date = date;
        this.time = time;
        this.location = location;
        this.goodType = goodType;
        this.vehicleType = vehicleType;
        this.weight = weight;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public int getDeliveryId() { return deliveryId; }

    public String getSender() { return sender; }

    public String getReceiver() { return receiver; }

    public Long getDate() { return date; }

    public String getTime() { return time; }

    public String getLocation() { return location; }

    public String getGoodType() { return goodType; }

    public String getVehicleType() { return vehicleType; }

    public Integer getWeight() { return weight; }

    public Integer getLength() { return length; }

    public Integer getWidth() { return width; }

    public Integer getHeight() { return height; }
}
