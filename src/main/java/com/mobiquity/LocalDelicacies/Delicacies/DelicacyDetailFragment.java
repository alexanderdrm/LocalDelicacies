package com.mobiquity.LocalDelicacies.delicacies;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.R;
import com.mobiquity.LocalDelicacies.location.Location;
import com.squareup.picasso.Picasso;


/**
 * Created by dalexander on 7/24/14.
 */
public class DelicacyDetailFragment extends Fragment {

    public static final String TAG = "DELICACY_DETAIL_FRAGMENT";

    private Delicacy delicacy;
    private TextView delicacyName;
    private ImageView delicacyImage;
    private ImageView bookmarkedButton;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_location_detail,
                container,
                false);


        delicacy = Delicacy.createDelicacyFromBundle(getArguments());

        delicacyName = (TextView) view.findViewById(R.id.name);
        delicacyName.setText(delicacy.getName());

        delicacyImage = (ImageView) view.findViewById(R.id.image);

        bookmarkedButton = (ImageView) view.findViewById(R.id.bookmarked_button);
        if (delicacy.isBookmarked()) {
            bookmarkedButton.setImageResource(R.drawable.love);
        } else {
            bookmarkedButton.setImageResource(R.drawable.no_love);
        }

        configureImage(inflater.getContext(), delicacy.getImageUrl(), delicacyImage);
        return view;
    }

    private void configureImage(Context context, String imageUrl, ImageView imageView) {
        Picasso.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.sample_delicacy)
                .error(R.drawable.error_image)
                .into(imageView);
    }
}
