package com.mobiquity.LocalDelicacies.location;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.R;

/**
 * Created by jwashington on 7/22/14.
 */
public class LocationDetailFragment extends Fragment {

    public static final String TAG = "LOCATION_DETAIL_FRAGMENT";

    public static Intent createIntent(Context context, Location location)
    {
        Intent intent = new Intent(context, LocationDetailFragment.class);
        intent.putExtras(Location.createBundleFromLocation(location));
        return intent;
    }

    private Location location;
    private TextView locationName;
    private ImageView bookmarkedButton;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.activity_location_detail,
                container,
                false );


        location = Location.createLocationFromBundle(getArguments());

        locationName = (TextView) view.findViewById(R.id.name);
        locationName.setText(location.getName());

        bookmarkedButton = (ImageView) view.findViewById(R.id.bookmarked_button);
        if (location.isBookmarked()) {
            bookmarkedButton.setImageResource(R.drawable.love);
        } else {
            bookmarkedButton.setImageResource(R.drawable.no_love);
        }

        return view;
    }

}
