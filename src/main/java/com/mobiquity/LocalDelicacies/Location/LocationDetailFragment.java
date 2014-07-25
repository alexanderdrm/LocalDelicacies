package com.mobiquity.LocalDelicacies.location;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.R;
import com.squareup.picasso.Picasso;

/**
 * Created by jwashington on 7/22/14.
 */
public class LocationDetailFragment extends Fragment {

    public static final String TAG = "LOCATION_DETAIL_FRAGMENT";

    private Location location;
    private TextView locationName;
    private TextView locationDescription;
    private ImageView locationImage;
    private ImageView bookmarkedButton;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.fragment_location_detail,
                container,
                false );


        location = Location.createLocationFromBundle(getArguments());

        locationName = (TextView) view.findViewById(R.id.name);
        locationName.setText(location.getTitle());

        locationDescription = (TextView) view.findViewById(R.id.description);
        locationDescription.setText(location.getDescription());

        locationImage = (ImageView) view.findViewById(R.id.image);

        bookmarkedButton = (ImageView) view.findViewById(R.id.bookmarked_button);
        if (location.isBookmarked()) {
            bookmarkedButton.setImageResource(R.drawable.love);
        } else {
            bookmarkedButton.setImageResource(R.drawable.no_love);
        }

        configureImage(inflater.getContext(), location.getImageUrl(), locationImage);
        return view;
    }

    private void configureImage(Context context, String imageUrl, ImageView imageView)
    {
        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.sample_city)
                .error(R.drawable.error_image)
                .into(imageView);
    }

}
