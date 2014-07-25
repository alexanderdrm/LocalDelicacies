package com.mobiquity.LocalDelicacies.delicacies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.R;


/**
 * Created by jwashington on 7/23/14.
 */
public class DelicacyDetailActivity extends Activity{

    public static Intent createIntent(Context context, Delicacy delicacy)
    {
        Intent intent = new Intent(context, DelicacyDetailActivity.class);
        intent.putExtras(Delicacy.createBundleFromDelicacy(delicacy));
        return intent;
    }

    private Delicacy delicacy;
    private TextView name;
    private ImageView image;
    private ImageView bookmarkedButton;
    private RatingBar ratingBar;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_delicacy_detail);

        delicacy = Delicacy.createDelicacyFromBundle(getIntent().getExtras());
        name = (TextView) findViewById(R.id.name);
        image = (ImageView) findViewById(R.id.image);
        bookmarkedButton= (ImageView) findViewById(R.id.bookmarked_button);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        description = (TextView) findViewById(R.id.description);

        name.setText(delicacy.getName());
        image.setImageResource(R.drawable.sample_delicacy);
        if(delicacy.isBookmarked())
        {
            bookmarkedButton.setImageResource(R.drawable.love);
        }
        else
        {
            bookmarkedButton.setImageResource(R.drawable.no_love);
        }

    }
}
