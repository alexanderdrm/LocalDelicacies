package com.mobiquity.LocalDelicacies;

import android.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;

import android.widget.FrameLayout;
import android.widget.ListView;
import com.mobiquity.LocalDelicacies.delicacies.DelicacyPagesFragment;
import com.mobiquity.LocalDelicacies.location.*;
import com.mobiquity.LocalDelicacies.navdrawer.NavigationDrawerClickEvent;

import com.mobiquity.LocalDelicacies.navdrawer.NavigationDrawerFragment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.tester.android.view.TestMenuItem;

import static com.mobiquity.LocalDelicacies.support.Assert.assertViewIsVisible;
import static org.junit.Assert.*;

@RunWith (RobolectricTestRunner.class)
public class MainActivityTest
{
    private MainActivity activity;

    private DrawerLayout  drawerLayout;
    private ApplicationBus bus;
    private NavigationDrawerFragment navigationDrawerFragment;
    private FrameLayout frameLayout;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity( MainActivity.class )
                              .create()
                              .start()
                              .resume()
                              .get();
        drawerLayout = (DrawerLayout) activity.findViewById( R.id.drawer_layout );
        navigationDrawerFragment = (NavigationDrawerFragment) activity.getFragmentManager().findFragmentById(R.id.navigation_drawer_fragment);
        frameLayout = (FrameLayout) activity.findViewById(R.id.content_frame);
        bus = ApplicationBus.getInstance();
        bus.register( activity );
    }

    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull( activity );
    }

    @Test
    public void shouldHaveFrameLayout() throws Exception
    {
        assertViewIsVisible(frameLayout);
    }

    @Test
    public void shouldHaveNavigationDrawerFragment() throws Exception
    {
        assertNotNull(navigationDrawerFragment);
    }

    @Test
    public void shouldHaveDrawerLayout() throws Exception
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
        assertTrue(drawerLayout.isDrawerOpen(Gravity.START));
        bus.post(new NavigationDrawerClickEvent("Test_Fragment", Fragment.class));
        assertFalse(drawerLayout.isDrawerOpen(Gravity.START));
    }

    @Test
    public void shouldToggleDrawerOnHomeClick() throws Exception
    {
        MenuItem homeItem = new TestMenuItem( android.R.id.home );
        activity.onOptionsItemSelected( homeItem );
        assertTrue( drawerLayout.isDrawerOpen( Gravity.START ) );
        activity.onOptionsItemSelected( homeItem );
        assertFalse( drawerLayout.isDrawerOpen( Gravity.START ) );
    }

    @Test
    public void shouldSwapFragmentsOnDrawerSelection() throws Exception
    {
        ListView drawerList = (ListView) navigationDrawerFragment.getView();
        Fragment fragment;

        //Locations Fragment
        Robolectric.shadowOf(drawerList).performItemClick(0);
        fragment = activity.getFragmentManager().findFragmentById(R.id.content_frame);
        assertTrue(fragment instanceof LocationPagesFragment);

        //Delicacies Fragment
        Robolectric.shadowOf(drawerList).performItemClick(1);
        fragment = activity.getFragmentManager().findFragmentById(R.id.content_frame);
        assertTrue(fragment instanceof DelicacyPagesFragment);
    }

    @Test
    public void shouldLaunchDetailFragment() throws Exception
    {
        LocationClickedEvent event = new LocationClickedEvent(new Location("TestLocation", null, "", false, -1));
        bus.post(event);
        Fragment fragment = activity.getFragmentManager().findFragmentById(R.id.content_frame);
        assertTrue(fragment instanceof LocationDetailFragment);
    }
}
