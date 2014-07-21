package com.mobiquity.LocalDelicacies.NavDrawer;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.RobolectricTestRunner;

import static com.mobiquity.LocalDelicacies.support.FragmentUtil.startFragment;
import static junit.framework.Assert.assertNotNull;

/**
 * @since 1.0
 */
@RunWith (RobolectricTestRunner.class)
public class NavigationDrawerFragmentTest
{
    @Test
    public void shouldNotBeNull() throws Exception
    {
        NavigationDrawerFragment fragment = new NavigationDrawerFragment();
        startFragment( fragment );
        assertNotNull( fragment );
    }
}
