package com.mobiquity.LocalDelicacies.Location;

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

import static com.mobiquity.LocalDelicacies.support.Assert.assertViewIsGone;
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
                new Location("TestLocation", null));

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
        Object location = Location.createLocationFromBundle(bundle);

        assertNotNull(location);
        assertTrue(location instanceof Location);
        assertThat(((Location) location).getName(), equalTo("TestLocation"));
    }

    @Test
    public void shouldContainLocationName() throws Exception
    {
        TextView locationName = (TextView) activity.findViewById(R.id.name);
        assertViewIsVisible(locationName);
        assertThat(locationName.getText().toString(), equalTo(getLocation().getName()));
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

    private Location getLocation()
    {
        return Location.createLocationFromBundle(activity.getIntent().getExtras());
    }


}
