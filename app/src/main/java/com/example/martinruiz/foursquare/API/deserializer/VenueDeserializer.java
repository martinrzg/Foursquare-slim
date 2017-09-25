package com.example.martinruiz.foursquare.API.deserializer;

import com.example.martinruiz.foursquare.models.Category;
import com.example.martinruiz.foursquare.models.Location;
import com.example.martinruiz.foursquare.models.Venue;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MartinRuiz on 9/24/2017.
 */

public class VenueDeserializer implements JsonDeserializer<ArrayList<Venue>> {
    @Override
    public ArrayList<Venue> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        //int venueID = json.getAsJsonObject().get("response")
        JsonArray venuesRawArray = json.getAsJsonObject().get("response").getAsJsonObject().get("venues").getAsJsonArray();
        ArrayList<Venue> venues = new ArrayList<>();

        for (int i = 0; i < venuesRawArray.size() ; i++) {
            String id = venuesRawArray.get(i).getAsJsonObject().get("id").getAsString();
            String name = venuesRawArray.get(i).getAsJsonObject().get("name").getAsString();

            //Category category = getCategory(venuesRawArray.get(i).getAsJsonObject());
            Location location = getLocation(venuesRawArray.get(i).getAsJsonObject());
            Category category = getCategory(venuesRawArray.get(i).getAsJsonObject());

            /*
            System.out.println("-------------------------------");
            System.out.println(i);
            System.out.println(venuesRawArray.get(i).toString());
            System.out.println(id);
            System.out.println(name);
            System.out.println(location.getAddress());
            System.out.println(location.getDistance());
            System.out.println(location.getLat()+" "+location.getLng());
            System.out.println(location.getFormattedAddress()[0] + location.getFormattedAddress()[1]);
            System.out.println(category.getId());
            System.out.println(category.getName());
            System.out.println(category.getIconPrefix());
            System.out.println(category.getIconSuffix());
            System.out.println("-------------------------------");
            */
            venues.add(new Venue(id,name,location,category));
        }
        return venues;
    }

    private Category getCategory(JsonObject venueElement) {
        String id, name, iconPrefix, iconSuffix;
        id = venueElement.getAsJsonObject().get("categories").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
        name = venueElement.getAsJsonObject().get("categories").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
        iconPrefix = venueElement.getAsJsonObject().get("categories").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsJsonObject().get("prefix").getAsString();
        iconSuffix = venueElement.getAsJsonObject().get("categories").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsJsonObject().get("suffix").getAsString();

        return new Category(id,name,iconPrefix,iconSuffix);
    }

    private Location getLocation(JsonObject venueElement) {
        String address ="Unknown";
        float distance;
        Double lat, lng;
        String []formattedAddress = {"Unknown","Unknown"};

        lat = venueElement.getAsJsonObject().get("location").getAsJsonObject().get("lat").getAsDouble();
        lng = venueElement.getAsJsonObject().get("location").getAsJsonObject().get("lng").getAsDouble();
        distance = venueElement.getAsJsonObject().get("location").getAsJsonObject().get("distance").getAsFloat();

        try{
            formattedAddress[0] = venueElement.getAsJsonObject().get("location").getAsJsonObject().get("formattedAddress").getAsJsonArray().get(0).getAsString();
            formattedAddress[1] = venueElement.getAsJsonObject().get("location").getAsJsonObject().get("formattedAddress").getAsJsonArray().get(1).getAsString();
        }catch (Exception exception){  }

        try{
            address = venueElement.getAsJsonObject().get("location").getAsJsonObject().get("address").getAsString();
        }catch (Exception exception){  }

        return new Location(address,distance,lat,lng,formattedAddress);

    }
}
