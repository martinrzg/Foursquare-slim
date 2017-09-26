package com.example.martinruiz.foursquare.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.martinruiz.foursquare.R;
import com.example.martinruiz.foursquare.models.nearVenuesResponse.Venue;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailVenueActivity extends AppCompatActivity {

    @BindView(R.id.textViewDetailAddress)  TextView textViewDetailAddress;
    @BindView(R.id.textViewDetailCategory)  TextView textViewDetailCategory;
    @BindView(R.id.textViewDetailDistance)  TextView textViewDetailDistance;
    @BindView(R.id.textViewDetailCheckins)  TextView textViewDetailCheckins;
    @BindView(R.id.imageViewDetailCategory) ImageView imageViewDetailCategory;
    private Venue venue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_venue);

        Toolbar toolbar = findViewById(R.id.toolbar);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Bundle bundle = getIntent().getExtras();
        if(! bundle.isEmpty()){
            venue = (Venue) bundle.getSerializable("venue");
        }



        setTitle(venue.getName());
        textViewDetailAddress.setText("Address: "+venue.getLocation().getFormattedAddress()[0]  );
        textViewDetailCategory.setText("Category: "+venue.getCategories().get(0).getName());
        double distance = (venue.getLocation().getDistance()/1000);
        String distanceS = String.format("%.2f%s",distance,"km");
        textViewDetailDistance.setText("Distance: "+distanceS);
        textViewDetailCheckins.setText("Checkins: "+venue.getStats().getCheckinsCount());
        String url = venue.getCategories().get(0).getIcon().getPrefix()+"88"+venue.getCategories().get(0).getIcon().getSuffix();
        //System.out.println(url);
        Picasso.with(this).load(url).into(imageViewDetailCategory);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
