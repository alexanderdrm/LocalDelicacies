package com.mobiquity.LocalDelicacies.location;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.mobiquity.LocalDelicacies.BasePagesAdapter;
import com.mobiquity.LocalDelicacies.R;
import com.mobiquity.LocalDelicacies.support.FragmentUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by jwashington on 7/24/14.
 */
@RunWith(RobolectricTestRunner.class)
public class LocationPagesFragmentTest
{
    private Fragment locationPagesFragment;
    private View locationList;
    private ViewPager pager;

    @Before
    public void setUp()
    {
        locationPagesFragment = new LocationPagesFragment();
        FragmentUtil.startFragment(locationPagesFragment);

        pager = (ViewPager) locationPagesFragment.getView().findViewById(R.id.pager);


    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( locationPagesFragment );
    }

    @Test
    public void shouldHaveViewPager() throws Exception
    {
        assertNotNull(pager);
    }
    @Test
    public void shouldHaveAnAdapter() throws Exception
    {
        assertNotNull(pager.getAdapter());
        assertTrue(pager.getAdapter() instanceof BasePagesAdapter);
    }


}
