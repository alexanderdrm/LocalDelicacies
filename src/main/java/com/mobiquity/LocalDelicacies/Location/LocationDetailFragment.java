package com.mobiquity.LocalDelicacies.location;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.*;
import com.mobiquity.LocalDelicacies.delicacies.Delicacy;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import sun.applet.AppletListener;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/22/14.
 */
public class LocationDetailFragment extends BasePagesFragment {

    Location location;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.layout_view_pager,
                container,
                false );

        pager = (ViewPager)view.findViewById(R.id.pager);
        location = new Location(getArguments());
        prepareAdapter(inflater.getContext());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

        SQLiteDatabase db = DatabaseHelper.getInstance(getActivity()).getWritableDatabase();
        location.saveToDatabase(db);
        db.close();
    }

    private void prepareAdapter(Context context)
    {
        TextView title;
        ImageView image;
        TextView description;
        ImageView bookmarkButton;
        RatingBar ratingBar;

        //Setting up the location Details
        final CustomDetailView locationView = new CustomDetailView(context);

        title = (TextView) locationView.findViewById(R.id.name);
        title.setText(location.getTitle());

        image = (ImageView) locationView.findViewById(R.id.image);
        Picasso.with(context)
                .load(location.getImageUrl())
                .placeholder(R.drawable.sample_city)
                .error(R.drawable.error_image)
                .into(image);

        description = (TextView) locationView.findViewById(R.id.description);
        description.setText(location.getDescription());

        bookmarkButton = (ImageView) locationView.findViewById(R.id.bookmarked_button);
        if (location.isBookmarked()) {
            bookmarkButton.setImageResource(R.drawable.love);
        } else {
            bookmarkButton.setImageResource(R.drawable.no_love);
        }
        ratingBar = (RatingBar) locationView.findViewById(R.id.ratingBar);
        ratingBar.setVisibility(View.GONE);

        ArrayList<View> views = new ArrayList<View>();
        views.add(locationView);

        ArrayList<Delicacy> delicacies = DatabaseHelper.getDelicacies(context, location);
        //Setting up views for each delicacy
        for(Delicacy delicacy : delicacies)
        {
            CustomDetailView delicacyView = new CustomDetailView(context);

            title = (TextView) delicacyView.findViewById(R.id.name);
            title.setText(delicacy.getTitle());

            image = (ImageView) delicacyView.findViewById(R.id.image);
            Picasso.with(context)
                    .load(delicacy.getImageUrl())
                    .placeholder(R.drawable.sample_city)
                    .error(R.drawable.error_image)
                    .into(image);

            description = (TextView) delicacyView.findViewById(R.id.description);
            description.setText(delicacy.getDescription());

            bookmarkButton = (ImageView) delicacyView.findViewById(R.id.bookmarked_button);

            ratingBar = (RatingBar) delicacyView.findViewById(R.id.ratingBar);
            views.add(delicacyView);
        }

        pager.setAdapter(new BasePagesAdapter(views));
    }


    @Subscribe
    public void onDataUpdated(NotifyFragmentsOfDataEvent nfde) {

        Context context = getActivity();
        if(context == null) {
            return; //we aren't attached, no need to update
        }

        Location newData = DatabaseHelper.getLocationData(context, location.getId());

        location = newData;

        Log.d("com.mobiquity.LocalDelicacies.location.LocationDetailFragment", "Oh hey, updating live data");

        prepareAdapter(context);
    }


}
