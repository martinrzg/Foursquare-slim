package com.example.martinruiz.foursquare.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MartinRuiz on 9/21/2017.
 */

public class API {
    public static final String CLIENT_ID = "P0YHT1Y3HVLS45CIGFH3N11MD0S5XXBXOTQF1VAOELDMYGAY";
    public static final String CLIENT_SECRET = "0VSA31KFPJWGZPYGFBITIPZF5ZJCBQ14U5NNDVAAUVFNYOUN";
    public static final String BASE_URL = "https://api.foursquare.com/v2/";
    public static final String API_VERSION = "20170921";

    private static Retrofit retrofit = null;

    public static Retrofit getApi(){
        if(retrofit == null){


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
