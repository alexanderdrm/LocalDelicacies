package com.mobiquity.LocalDelicacies.Delicacies;

import com.mobiquity.LocalDelicacies.MainActivity;
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

public class DelicaciesActivityTest {

    private DelicaciesActivity activity;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity(DelicaciesActivity.class)
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
}
