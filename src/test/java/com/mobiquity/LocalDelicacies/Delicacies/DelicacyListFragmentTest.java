package com.mobiquity.LocalDelicacies.Delicacies;

import android.app.Fragment;
import com.mobiquity.LocalDelicacies.MainActivity;
import com.mobiquity.LocalDelicacies.support.FragmentUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.FragmentTestUtil;

import static org.junit.Assert.assertNotNull;

/**
 * Created by jwashington on 7/22/14.
 */

@RunWith(RobolectricTestRunner.class)

public class DelicacyListFragmentTest {

    private Fragment delicacyListFragment;

    @Before
    public void setUp() throws Exception
    {
        delicacyListFragment = new DelicacyListFragment();
        FragmentUtil.startFragment(delicacyListFragment);

    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( delicacyListFragment );
    }


}
