package com.example.martinruiz.foursquare.models.nearVenuesResponse;

import java.io.Serializable;

public class Stats implements Serializable{

    private int checkinsCount;

    public int getCheckinsCount() {
        return checkinsCount;
    }

    public void setCheckinsCount(int checkinsCount) {
        this.checkinsCount = checkinsCount;
    }


}
