package com.mobiquity.LocalDelicacies.support;

import com.mobiquity.LocalDelicacies.BaseEvent;
import com.mobiquity.LocalDelicacies.NavDrawer.NavigationDrawerClickEvent;
import com.squareup.otto.Subscribe;

/**
 * Created by jwashington on 7/21/14.
 */
public class BusHelper {
    protected BaseEvent event;

    public BaseEvent getLastEvent()
    {
        return event;
    }

    @Subscribe
    public void onNavigationDrawerClickEvent(NavigationDrawerClickEvent event)
    {
        this.event = event;
    }

}
