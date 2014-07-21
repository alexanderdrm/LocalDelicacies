package com.mobiquity.LocalDelicacies;

import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;

import com.mobiquity.LocalDelicacies.NavDrawer.NavigationDrawerClickEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.tester.android.view.TestMenuItem;

import static com.mobiquity.LocalDelicacies.support.Assert.assertViewIsVisible;
import static org.junit.Assert.*;

@RunWith (RobolectricTestRunner.class)
public class LocationListActivityTest
{
    private LocationListActivity activity;

    private DrawerLayout  drawerLayout;
    private ApplicationBus bus;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity( LocationListActivity.class )
                              .create()
                              .start()
                              .resume()
                              .get();
        drawerLayout = (DrawerLayout) activity.findViewById( R.id.drawer_layout );
        bus = ApplicationBus.getInstance();
        bus.register( activity );
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
    public void shouldHaveNavidgationDrawerFragment() throws Exception
    {
        assertNotNull(activity.getFragmentManager().findFragmentById(R.id.navigation_drawer_fragment));
    }

    @Test
    public void shouldHaveNavigationDrawer() throws Exception
    {
        assertViewIsVisible( drawerLayout );
    }

    @Test
    public void shouldChangeActionBarTitleOnNavDrawerClickEventReceived() throws
                                                                          Exception
    {
        //Todo: test this... returns null for the action bar title
    }

    @Test
    public void shouldCloseDrawerLayoutOnEvent() throws Exception
    {
        drawerLayout.openDrawer(Gravity.START);
        assertTrue( drawerLayout.isDrawerOpen(Gravity.START ) );
        bus.post(new NavigationDrawerClickEvent(""));
        assertFalse( drawerLayout.isDrawerOpen( Gravity.START ) );
    }

    @Test
    public void shouldOpenDrawerOnHomeClick() throws Exception
    {
        MenuItem homeItem = new TestMenuItem( android.R.id.home );
        activity.onOptionsItemSelected( homeItem );
        assertTrue( drawerLayout.isDrawerOpen( Gravity.START ) );
        activity.onOptionsItemSelected( homeItem );
        assertFalse( drawerLayout.isDrawerOpen( Gravity.START ) );
    }
}
