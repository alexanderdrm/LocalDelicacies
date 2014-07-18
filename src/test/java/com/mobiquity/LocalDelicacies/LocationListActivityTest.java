package com.mobiquity.LocalDelicacies;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static com.mobiquity.LocalDelicacies.support.Assert.assertViewIsVisible;
import static org.junit.Assert.*;

@RunWith (RobolectricTestRunner.class)
public class LocationListActivityTest
{

    private LocationListActivity activity;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity( LocationListActivity.class )
                              .create()
                              .start()
                              .resume()
                              .get();
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( activity );
    }

    @Test
    public void shouldHaveLocationListFragment() throws Exception
    {
        activity.getFragmentManager().findFragmentById( R.id.location_list_fragment );

    }
}
