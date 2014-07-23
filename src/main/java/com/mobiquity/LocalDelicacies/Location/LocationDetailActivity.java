package com.mobiquity.LocalDelicacies.location;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.R;

/**
 * Created by jwashington on 7/22/14.
 */
public class LocationDetailActivity extends Activity {

    public static Intent createIntent(Context context, Location location)
    {
        Intent intent = new Intent(context, LocationDetailActivity.class);
        intent.putExtras(Location.createBundleFromLocation(location));
        return intent;
    }

    private Location location;
    private TextView locationName;
    private ImageView bookmarkedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);
        location = Location.createLocationFromBundle(
                getIntent().getExtras());

        locationName = (TextView) findViewById(R.id.name);
        locationName.setText(location.getName());

        bookmarkedButton = (ImageView) findViewById(R.id.bookmarked_button);
        if(location.isBookmarked())
            bookmarkedButton.setImageResource(R.drawable.love);
        else
            bookmarkedButton.setImageResource(R.drawable.no_love);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,0);
    }
}
