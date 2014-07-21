package com.mobiquity.LocalDelicacies;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.mobiquity.LocalDelicacies.support.ResourceLocator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static com.mobiquity.LocalDelicacies.support.Assert.assertViewIsVisible;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith (RobolectricTestRunner.class)
public class LocationListActivityTest
{
    private LocationListActivity activity;

    private View        drawerLayout;
    private ListAdapter getAdapter;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity( LocationListActivity.class )
                              .create()
                              .start()
                              .resume()
                              .get();
        drawerLayout = activity.findViewById( R.id.drawer_layout );
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( activity );
    }

    @Test
    public void shouldHaveLocationListFragment() throws Exception
    {
        assertNotNull( activity.getFragmentManager().findFragmentById( R.id.location_list_fragment ) );
    }

    @Test
    public void shouldHaveNavigationDrawer() throws Exception
    {
        assertViewIsVisible( drawerLayout );
        assertTrue( drawerLayout instanceof DrawerLayout );
    }

    @Test
    public void shouldHaveNavDrawerOptions() throws Exception
    {
        ListView drawerList = (ListView) activity.findViewById( R.id.left_drawer );
        String[] stringArray = ResourceLocator.getStringArray( R.array.nav_titles );
        getAdapter = drawerList.getAdapter();
        for ( int index = 0; index < getAdapter.getCount() - 1; index++ )
        {
            assertThat( stringArray[index],
                        equalTo( drawerList.getAdapter().getItem( index ) ) );
        }
    }
}
