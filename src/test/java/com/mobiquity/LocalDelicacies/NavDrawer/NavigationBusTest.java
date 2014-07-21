package com.mobiquity.LocalDelicacies.NavDrawer;

import android.app.Activity;
import com.mobiquity.LocalDelicacies.BaseEvent;
import com.mobiquity.LocalDelicacies.LocationListActivity;
import com.mobiquity.LocalDelicacies.support.BusHelper;
import com.squareup.otto.Subscribe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

/**
 * Created by jwashington on 7/21/14.
 */
@RunWith(RobolectricTestRunner.class)
public class NavigationBusTest {
    Activity activity;
    NavigationBus bus;
    BusHelper busHelper;

    @Before
    public void setup()
    {

        busHelper = new BusHelper();
        bus = NavigationBus.getInstance();
        bus.register(busHelper);
    }

    @Test
    public void shouldNotBeNull()
    {
        assertNotNull(bus);
    }

    @Test
    public void shouldPostNavigationDrawerClickEvent()
    {
        NavigationDrawerClickEvent testEvent = new NavigationDrawerClickEvent("Test_Event");
        bus.post(testEvent);
        assertTrue(busHelper.getLastEvent() instanceof NavigationDrawerClickEvent);
    }

    @Test
    public void shouldNotPostEventsOtherThanNavigationDrawerClickEvent()
    {
        BaseEvent testEvent = new BaseEvent();
        bus.post(testEvent);
        assertNull(busHelper.getLastEvent());
    }

}
