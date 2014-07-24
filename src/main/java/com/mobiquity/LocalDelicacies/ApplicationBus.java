package com.mobiquity.LocalDelicacies;

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

    public static void postEvent(BaseEvent e) {
        ApplicationBus.getInstance().post(e);
    }

    private ApplicationBus()
    {

    }



}
