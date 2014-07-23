package com.mobiquity.LocalDelicacies.location;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.R;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import static com.mobiquity.LocalDelicacies.support.Assert.assertViewIsVisible;

/**
 * Created by jwashington on 7/22/14.
 */


@RunWith(RobolectricTestRunner.class)
public class LocationDetailActivityTest {

    LocationDetailActivity activity;
    Intent intent;

    @Before
    public void setUp()
    {
        intent = LocationDetailActivity.createIntent(
                Robolectric.application.getApplicationContext(),
                getTestLocation());

        activity = Robolectric.buildActivity(LocationDetailActivity.class)
                .withIntent(intent)
                .create()
                .start()
                .resume()
                .get();

    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull(activity);
    }

    @Test
    public void shouldHaveAnIntent() throws Exception
    {
        assertNotNull(activity.getIntent());
        assertThat(intent, equalTo(activity.getIntent()));
    }

    @Test
    public void shouldMakeLocationFromIntent() throws Exception
    {
        Bundle bundle = activity.getIntent().getExtras();
        assertNotNull(bundle);
        Object location = getBundledLocation();

        assertNotNull(location);
        assertTrue(location instanceof Location);
        assertTrue(location.equals(getTestLocation()));
    }



    @Test
    public void shouldContainLocationName() throws Exception
    {
        TextView locationName = (TextView) activity.findViewById(R.id.name);
        assertViewIsVisible(locationName);
        assertThat(locationName.getText().toString(), equalTo(getBundledLocation().getName()));
    }

    @Test
    public void shouldContainLocationImage() throws Exception
    {
        ImageView image = (ImageView)activity.findViewById(R.id.image);
        assertViewIsVisible(image);
    }

    @Test
    public void shouldContainLocationDescription() throws Exception
    {
        TextView description = (TextView) activity.findViewById(R.id.description);
        assertViewIsVisible(description);
    }

    @Test
    public void shouldContainBookmarkImage() throws Exception
    {
        ImageView bookmarkImage = (ImageView) activity.findViewById(R.id.bookmarked_button);
        assertViewIsVisible(bookmarkImage);
        if(getBundledLocation().isBookmarked())
        {
            assertThat(bookmarkImage.getDrawable(), equalTo(activity.getResources().getDrawable(R.drawable.love)));
        }
        else
        {
            assertThat(bookmarkImage.getDrawable(), equalTo(activity.getResources().getDrawable(R.drawable.no_love)));

        }
    }

    private Location getBundledLocation()
    {
        return Location.createLocationFromBundle(activity.getIntent().getExtras());
    }

    private Location getTestLocation()
    {
        return new Location("Test_Locaiton", null);
    }


}
