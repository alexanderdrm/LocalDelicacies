package com.mobiquity.LocalDelicacies.location;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.R;
import com.mobiquity.LocalDelicacies.support.FragmentUtil;
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
public class LocationDetailFragmentTest {

    LocationDetailFragment locationDetailFragment;

    @Before
    public void setUp()
    {
        locationDetailFragment = new LocationDetailFragment();
        FragmentUtil.startFragment(locationDetailFragment);

    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull(locationDetailFragment);
    }

    @Test
    public void shouldHaveArguments() throws Exception
    {
        assertNotNull(locationDetailFragment.getArguments());
    }

    @Test
    public void shouldMakeLocationFromArguments() throws Exception
    {
        Bundle bundle = locationDetailFragment.getArguments();
        assertNotNull(bundle);
        Object location = getBundledLocation();

        assertNotNull(location);
        assertTrue(location instanceof Location);
        assertTrue(location.equals(getTestLocation()));
    }

    @Test
    public void shouldContainLocationName() throws Exception
    {
        TextView locationName = (TextView) locationDetailFragment.getView().findViewById(R.id.name);
        assertViewIsVisible(locationName);
        assertThat(locationName.getText().toString(), equalTo(getBundledLocation().getName()));
    }

    @Test
    public void shouldContainLocationImage() throws Exception
    {
        ImageView image = (ImageView)locationDetailFragment.getView().findViewById(R.id.image);
        assertViewIsVisible(image);
    }

    @Test
    public void shouldContainLocationDescription() throws Exception
    {
        TextView description = (TextView) locationDetailFragment.getView().findViewById(R.id.description);
        assertViewIsVisible(description);
    }

    @Test
    public void shouldContainBookmarkImage() throws Exception
    {
        ImageView bookmarkImage = (ImageView) locationDetailFragment.getView().findViewById(R.id.bookmarked_button);
        assertViewIsVisible(bookmarkImage);
        if(getBundledLocation().isBookmarked())
        {
            assertThat(bookmarkImage.getDrawable(), equalTo(locationDetailFragment.getActivity().getResources().getDrawable(R.drawable.love)));
        }
        else
        {
            assertThat(bookmarkImage.getDrawable(), equalTo(locationDetailFragment.getActivity().getResources().getDrawable(R.drawable.no_love)));

        }
    }

    private Location getBundledLocation()
    {
        return Location.createLocationFromBundle(locationDetailFragment.getArguments());
    }

    private Location getTestLocation()
    {
        return new Location("Test_Locaiton", null);
    }


}
