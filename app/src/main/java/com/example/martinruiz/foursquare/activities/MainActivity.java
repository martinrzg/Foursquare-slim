package com.example.martinruiz.foursquare.activities;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.example.martinruiz.foursquare.API.API;
import com.example.martinruiz.foursquare.API.APIServices.FoursquareServices;
import com.example.martinruiz.foursquare.R;
import com.example.martinruiz.foursquare.adapters.VenuesAdapter;
import com.example.martinruiz.foursquare.models.Venue;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private final int REQUEST_LOCATION = 1;

    private GoogleApiClient googleApiClient;
    private Location location;
    private SharedPreferences preferences;
    private FoursquareServices services;
    private FoursquareServices services2;

    private String token;
    private List<Venue> venues;
    @BindView(R.id.recyclerViewVenueCards) RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);


        services = API.getApi().create(FoursquareServices.class);
        services2 = API.getApi2().create(FoursquareServices.class);

        preferences = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        token = preferences.getString("token", null);
        venues = getVenues();

        //System.out.println("onCreate");
        googleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).addApi(LocationServices.API).build();

        layoutManager = new LinearLayoutManager(this);
        adapter = new VenuesAdapter(venues, R.layout.venue_card, this, new VenuesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Venue venue, int position, View view) {
                Toast.makeText(MainActivity.this, venue.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
        //System.out.println("onSTART");
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        processLocation();
    }

    private void processLocation() {
        getLocation();
        if (location != null) {
            printLocation();
            retrieveVenus();

        }
    }

    private void retrieveVenus() {
        String ll = location.getLatitude() + "," + location.getLongitude();
        final Call<Venue> venuesCall = services.getNearVenues(ll, 1, token, API.API_VERSION, 2);

        venuesCall.enqueue(new Callback<Venue>() {
            @Override
            public void onResponse(Call<Venue> call, Response<Venue> response) {
                if (response.code() == 200) {
                    venues = response.body();
                    for (int i = 0; i < venues.size(); i++) {
                        adapter.notifyItemChanged(i);
                    }

                    System.out.println("venues: "+venues.size());
                    System.out.println("count: "+adapter.getItemCount());

                    //getVenueDetails();
                }
            }
            @Override
            public void onFailure(Call<Venue> call, Throwable t) {
                Toast.makeText(MainActivity.this,"No internet connection, try later...",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getVenueDetails(){
        for (int i = 0; i < venues.size(); i++) {
            String id  = venues.get(i).getId();
            System.out.println("Venue details");
            Call<JSONObject> venueDetails = services2.getVenueInfo(id,token, API.API_VERSION);

            venueDetails.enqueue(new Callback<JSONObject>() {
                @Override
                public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                    System.out.println("CALL:"+call.request().url());
                    System.out.println("CALL:"+response.body());
                }
                @Override
                public void onFailure(Call<JSONObject> call, Throwable t) {

                }
            });
        }
    }

    private void getLocation() {
        if (isLocationPermissionGranted()) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        } else {
            requestPermission();
        }
    }

    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "No quisiste dar acceso a tu ubicación", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(
                    this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length == 1
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                if (location != null)
                    printLocation();
                else
                    Toast.makeText(this, "Ubicación no encontrada", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Permisos no otorgados", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void printLocation() {
        System.out.println("Lat: "+location.getLatitude()+" Lon: "+location.getLongitude());
    }

    private boolean isLocationPermissionGranted() {
        int permission = ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private List<Venue> getVenues() {
        return new ArrayList<Venue>(){
            {
            }
        };
    }
}
