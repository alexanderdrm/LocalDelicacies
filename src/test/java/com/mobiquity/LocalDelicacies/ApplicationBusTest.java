package com.mobiquity.LocalDelicacies;

import android.app.Activity;
import android.app.Fragment;
import com.mobiquity.LocalDelicacies.navdrawer.NavigationDrawerClickEvent;
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
        BaseEvent testEvent = new NavigationDrawerClickEvent("Test_Event", Fragment.class);
        bus.post(testEvent);
        assertTrue(busHelper.getLastEvent() instanceof NavigationDrawerClickEvent);
    }


}
