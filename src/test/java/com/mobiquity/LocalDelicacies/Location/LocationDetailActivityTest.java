package com.mobiquity.LocalDelicacies.Location;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jwashington on 7/22/14.
 */


@RunWith(RobolectricTestRunner.class)
public class LocationDetailActivityTest {

    LocationDetailActivity activity;

    @Before
    public void setUp()
    {
        activity = Robolectric.buildActivity(LocationDetailActivity.class)
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
    public void shouldHaveALocation() throws Exception
    {

    }

}
