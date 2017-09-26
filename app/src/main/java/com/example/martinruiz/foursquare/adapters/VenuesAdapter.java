package com.example.martinruiz.foursquare.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.martinruiz.foursquare.R;
import com.example.martinruiz.foursquare.models.nearVenuesResponse.Venue;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MartinRuiz on 9/25/2017.
 */

public class VenuesAdapter extends RecyclerView.Adapter<VenuesAdapter.ViewHolder>  {

    private List<Venue> venues;
    private int layoutReference;
    private OnItemClickListener onItemClickListener;
    private Activity activity;
    private View parentView;

    public VenuesAdapter(List<Venue> venues, int layoutReference, Activity activity, OnItemClickListener onItemClickListener) {
        this.venues = venues;
        this.layoutReference = layoutReference;
        this.onItemClickListener = onItemClickListener;
        this.activity = activity;
    }

    @Override
    public VenuesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        parentView = parent;
        View view = LayoutInflater.from(activity).inflate(layoutReference,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(VenuesAdapter.ViewHolder holder, int position) {
        holder.bind( venues.get(position), onItemClickListener );
    }
    @Override
    public int getItemCount (){
        return venues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.textViewVenueName) TextView textViewVenueName;
        @BindView(R.id.textViewVenueCategory) TextView textViewVenueCategory;
        @BindView(R.id.textViewVenuePrice) TextView textViewVenuePrice;
        @BindView(R.id.textViewVenueDistance) TextView textViewVenueDistance;
        @BindView(R.id.textViewVenueAddress) TextView textViewVenueAddress;
        @BindView(R.id.imageViewCategory) ImageView imageViewVenueCategory;
        @BindView(R.id.cardViewVenue) CardView cardViewVenue;

        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bind(final Venue venue, final OnItemClickListener onItemClickListener ){
            textViewVenueName.setText(venue.getName());
            textViewVenueCategory.setText(venue.getCategories().get(0).getName());
            textViewVenuePrice.setText("$$$");
            double distance = (venue.getLocation().getDistance()/1000);
            String distanceS = String.format("%.2f%s",distance,"km");
            textViewVenueDistance.setText(distanceS);
            textViewVenueAddress.setText(venue.getLocation().getAddress());
            Picasso.with(activity).load(venue.getCategories().get(0).getIcon().getPrefix()+"64"+venue.getCategories().get(0).getIcon().getSuffix()).into(imageViewVenueCategory);

            cardViewVenue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(venue, getAdapterPosition() , cardViewVenue);
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(Venue venue, int position, View view);
    }
}
