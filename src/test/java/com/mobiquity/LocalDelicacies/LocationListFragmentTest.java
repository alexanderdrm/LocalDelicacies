package com.mobiquity.LocalDelicacies;

import android.app.Fragment;
import android.widget.ListView;
import com.mobiquity.LocalDelicacies.support.FragmentUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class LocationListFragmentTest {

    private Fragment locationListFragment;

    @Before
    public void setUp()
    {
        locationListFragment = new LocationListFragment();
        FragmentUtil.startFragment(locationListFragment);
    }

    @Test
    public void shouldNotBeNull()
    {
        assertNotNull(locationListFragment);
    }

    @Test
    public void shouldContainListView()
    {
        assertNotNull(locationListFragment.getView());
        assertTrue(locationListFragment.getView() instanceof ListView);
    }

}