package com.example.martinruiz.foursquare.models;

/**
 * Created by MartinRuiz on 9/24/2017.
 */

public class Location {

    private String address;
    private float distance;
    private double lat;
    private double lng;
    private String []formattedAddress;

    public Location(String address, float distance, double lat, double lng, String[] formattedAddress) {
        this.address = address;
        this.distance = distance;
        this.lat = lat;
        this.lng = lng;
        this.formattedAddress = formattedAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String[] getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String[] formattedAddress) {
        this.formattedAddress = formattedAddress;
    }
}
