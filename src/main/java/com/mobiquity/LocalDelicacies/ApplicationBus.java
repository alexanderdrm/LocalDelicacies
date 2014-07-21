package com.mobiquity.LocalDelicacies;

import com.mobiquity.LocalDelicacies.NavDrawer.NavigationDrawerClickEvent;
import com.squareup.otto.Bus;

/**
 * Created by jwashington on 7/21/14.
 */
public class ApplicationBus extends Bus {

    private static ApplicationBus instance = new ApplicationBus();

    public static ApplicationBus getInstance()
    {
        return instance;
    }

    @Override
    public void post(Object event) {
        if(event instanceof NavigationDrawerClickEvent)
            super.post(event);
    }

    private ApplicationBus()
    {

    }



}
