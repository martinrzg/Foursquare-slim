package com.example.martinruiz.foursquare.models.nearVenuesResponse;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable{
    private List<Venue> venues;

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }
}
