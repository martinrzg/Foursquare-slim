package com.example.martinruiz.foursquare.models.nearVenuesResponse;

import java.io.Serializable;

/**
 * Created by MartinRuiz on 9/25/2017.
 */

public class NearVenuesResponse implements Serializable {

    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}

