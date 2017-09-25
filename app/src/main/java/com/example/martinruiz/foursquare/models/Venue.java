package com.example.martinruiz.foursquare.models;

import java.util.ArrayList;

/**
 * Created by MartinRuiz on 9/23/2017.
 */

public class Venue {

    private String id;
    private String name;
    private Location location;
    private Category category;
    private float rating;
    private String ratingColor;
    private String numRatings;
    private int priceTier;
    private String photoPreffix;
    private String photoSuffix;

    public Venue(String id, String name, Location location, Category category) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.category = category;
        /*
        this.rating = rating;
        this.ratingColor = ratingColor;
        this.numRatings = numRatings;
        this.priceTier = priceTier;
        this.photoPreffix = photoPreffix;
        this.photoSuffix = photoSuffix;
        */
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getRatingColor() {
        return ratingColor;
    }

    public void setRatingColor(String ratingColor) {
        this.ratingColor = ratingColor;
    }

    public String getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(String numRatings) {
        this.numRatings = numRatings;
    }

    public int getPriceTier() {
        return priceTier;
    }

    public void setPriceTier(int priceTier) {
        this.priceTier = priceTier;
    }

    public String getPhotoPreffix() {
        return photoPreffix;
    }

    public void setPhotoPreffix(String photoPreffix) {
        this.photoPreffix = photoPreffix;
    }

    public String getPhotoSuffix() {
        return photoSuffix;
    }

    public void setPhotoSuffix(String photoSuffix) {
        this.photoSuffix = photoSuffix;
    }
}
