package com.mobiquity.LocalDelicacies;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;
@RunWith(RobolectricTestRunner.class)
public class LocationListActivityTest {


    @Test
    public void shouldNotBeNull() throws Exception {
        LocationListActivity activity = Robolectric.buildActivity(LocationListActivity.class)
                .create()
                .start()
                .resume()
                .get();

        assertNotNull(activity);

    }
}