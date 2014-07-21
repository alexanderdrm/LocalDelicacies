package com.mobiquity.LocalDelicacies.NavDrawer;

import android.app.Application;
import com.squareup.otto.Bus;

/**
 * Created by jwashington on 7/21/14.
 */
public class NavigationBus extends Bus {

    private static NavigationBus instance = new NavigationBus();

    public static NavigationBus getInstance()
    {
        return instance;
    }

    @Override
    public void post(Object event) {
        if(event instanceof NavigationDrawerClickEvent)
            super.post(event);
    }

    private NavigationBus()
    {

    }



}
