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
import com.mobiquity.LocalDelicacies.*;
import com.mobiquity.LocalDelicacies.http.DataUpdateEvent;
import com.mobiquity.LocalDelicacies.location.Location;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


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

    private boolean ignoreNextDataUpdate = false;

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

    private void configureView(final Context context) {
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
                    //bookmarkButton.setImageResource(R.drawable.love);
                    UIUtil.setImageDrawableCrossFade(bookmarkButton,
                            context.getResources().getDrawable(R.drawable.love));
                } else {
                    //bookmarkButton.setImageResource(R.drawable.no_love);
                    UIUtil.setImageDrawableCrossFade(bookmarkButton,
                            context.getResources().getDrawable(R.drawable.no_love));
                }

                ignoreNextDataUpdate = true;
                ArrayList<Delicacy> delicacyWrapper = new ArrayList<Delicacy>();
                delicacyWrapper.add(delicacy);
                ApplicationBus.getInstance().post(new DataUpdateEvent(null, delicacyWrapper));
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
                if(!fromUser) return;

                int temp = Math.round(rating * 2);
                delicacy.setRatingInHalfStars(temp);

                ratingBar.setRating(temp / 2f);

                ignoreNextDataUpdate = true;
                ArrayList<Delicacy> delicacyWrapper = new ArrayList<Delicacy>();
                delicacyWrapper.add(delicacy);
                ApplicationBus.getInstance().post(new DataUpdateEvent(null, delicacyWrapper));
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

        if(ignoreNextDataUpdate) {
            ignoreNextDataUpdate = false;
            return;
        }

        Context context = getActivity();
        if(context == null) {
            return; //we aren't attached, no need to update
        }

        Delicacy newData = DatabaseHelper.getDelicacyData(context, delicacy.getId());

        delicacy = newData;

        configureView(context);
    }
}
