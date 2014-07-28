package com.mobiquity.LocalDelicacies.delicacies;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.ApplicationBus;
import com.mobiquity.LocalDelicacies.R;
import com.mobiquity.LocalDelicacies.http.DataUpdateEvent;
import com.mobiquity.LocalDelicacies.location.Location;
import com.squareup.picasso.Picasso;


/**
 * Created by dalexander on 7/24/14.
 */
public class DelicacyDetailFragment extends Fragment {

    public static final String TAG = "DELICACY_DETAIL_FRAGMENT";

    private Delicacy delicacy;
    private TextView delicacyName;
    private TextView delicacyDescription;
    private ImageView delicacyImage;
    private ImageView bookmarkedButton;
    private RatingBar ratingBar;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_layout,
                container,
                false);


        delicacy = Delicacy.createDelicacyFromBundle(getArguments());

        delicacyName = (TextView) view.findViewById(R.id.name);
        delicacyName.setText(delicacy.getName());

        delicacyDescription = (TextView) view.findViewById(R.id.description);
        delicacyDescription.setText(delicacy.getDescription());
        delicacyImage = (ImageView) view.findViewById(R.id.image);

        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);

        bookmarkedButton = (ImageView) view.findViewById(R.id.bookmarked_button);
        if (delicacy.isBookmarked()) {
            bookmarkedButton.setImageResource(R.drawable.love);
        } else {
            bookmarkedButton.setImageResource(R.drawable.no_love);
        }

        configureImage(inflater.getContext(), delicacy.getImageUrl(), delicacyImage);
        configureRatingBar();
        return view;
    }

    private void configureImage(Context context, String imageUrl, ImageView imageView) {
        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.sample_delicacy)
                .error(R.drawable.error_image)
                .into(imageView);
    }

    private void configureRatingBar()
    {
        ratingBar.setClickable(true);
        ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delicacy.setRatingInHalfStars((int) (ratingBar.getRating() * 2));
            }
        });
    }
}
