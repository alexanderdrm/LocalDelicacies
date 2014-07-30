package com.mobiquity.LocalDelicacies.delicacies;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.BussedFragment;
import com.mobiquity.LocalDelicacies.DatabaseHelper;
import com.mobiquity.LocalDelicacies.NotifyFragmentsOfDataEvent;
import com.mobiquity.LocalDelicacies.R;
import com.mobiquity.LocalDelicacies.location.Location;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;


/**
 * Created by dalexander on 7/24/14.
 */
public class DelicacyDetailFragment extends BussedFragment {

    public static final String TAG = "DELICACY_DETAIL_FRAGMENT";

    private Delicacy delicacy;
    private TextView delicacyName;
    private TextView delicacyDescription;
    private ImageView delicacyImage;
    private ImageView bookmarkButton;
    private RatingBar ratingBar;

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail_layout,
                container,
                false);

        this.view = view;

        delicacy = new Delicacy(getArguments());

        configureView(inflater.getContext());

        return view;
    }

    private void configureView(Context context) {
        delicacyName = (TextView) view.findViewById(R.id.name);
        delicacyName.setText(delicacy.getTitle());

        delicacyDescription = (TextView) view.findViewById(R.id.description);
        delicacyDescription.setText(delicacy.getDescription());
        delicacyImage = (ImageView) view.findViewById(R.id.image);

        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);

        bookmarkButton = (ImageView) view.findViewById(R.id.bookmarked_button);
        if (delicacy.isBookmarked()) {
            bookmarkButton.setImageResource(R.drawable.love);
        } else {
            bookmarkButton.setImageResource(R.drawable.no_love);
        }

        bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delicacy.setBookmarked(!delicacy.isBookmarked());
                if (delicacy.isBookmarked()) {
                    bookmarkButton.setImageResource(R.drawable.love);
                } else {
                    bookmarkButton.setImageResource(R.drawable.no_love);
                }
            }
        });



        ratingBar.setRating(delicacy.getRatingInHalfStars() / 2f);

        configureImage(context, delicacy.getImageUrl(), delicacyImage);
        configureRatingBar();
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

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                int temp = Math.round(rating * 2);
                delicacy.setRatingInHalfStars(temp);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        SQLiteDatabase db = DatabaseHelper.getInstance(getActivity()).getWritableDatabase();
        delicacy.saveToDatabase(db);
        db.close();
    }

    @Subscribe
    public void onDataUpdated(NotifyFragmentsOfDataEvent nfde) {

        Context context = getActivity();
        if(context == null) {
            return; //we aren't attached, no need to update
        }

        Delicacy newData = DatabaseHelper.getDelicacyData(context, delicacy.getId());

        delicacy = newData;

        Log.d("com.mobiquity.LocalDelicacies.location.LocationDetailFragment", "Oh hey, updating live data");

        configureView(context);
    }
}
