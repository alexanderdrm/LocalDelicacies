package com.mobiquity.LocalDelicacies;

import android.app.Activity;
import com.mobiquity.LocalDelicacies.ApplicationBus;
import com.mobiquity.LocalDelicacies.BaseEvent;
import com.mobiquity.LocalDelicacies.NavDrawer.NavigationDrawerClickEvent;
import com.mobiquity.LocalDelicacies.support.BusHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

/**
 * Created by jwashington on 7/21/14.
 */
@RunWith(RobolectricTestRunner.class)
public class ApplicationBusTest {
    Activity activity;
    ApplicationBus bus;
    BusHelper busHelper;

    @Before
    public void setup()
    {

        busHelper = new BusHelper();
        bus = ApplicationBus.getInstance();
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
