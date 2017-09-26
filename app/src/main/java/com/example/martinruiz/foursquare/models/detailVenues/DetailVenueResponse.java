package com.example.martinruiz.foursquare.models.detailVenues;

import com.example.martinruiz.foursquare.models.nearVenuesResponse.Response;

import java.io.Serializable;

/**
 * Created by MartinRuiz on 9/26/2017.
 */

public class DetailVenueResponse implements Serializable{

    private Response2 response;

}

class Response2{
    private Venue2 venue;


}

class Venue2{
    private String id ;
    private String name;
    private Location2 location;


}

class Location2{
    private String address;
    private double lat, lng;
    private double distance;
    private String [] formattedAddress;

}
