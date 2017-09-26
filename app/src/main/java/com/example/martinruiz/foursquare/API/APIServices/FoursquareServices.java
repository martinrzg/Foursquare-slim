package com.example.martinruiz.foursquare.API.APIServices;

import com.example.martinruiz.foursquare.models.nearVenuesResponse.NearVenuesResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by MartinRuiz on 9/21/2017.
 */

public interface FoursquareServices {

    @GET("venues/search")
    Call<NearVenuesResponse> getNearVenues (@Query("ll") String ll, @Query("venuePhoto")int venuePhoto,
                                            @Query("oauth_token") String token, @Query("v") String version);
    //@Query("limit") int limit

    @GET("venues/{id}")
    Call<JSONObject> getVenueInfo(@Path("id") String id, @Query("oauth_token") String token, @Query("v") String version );
}
