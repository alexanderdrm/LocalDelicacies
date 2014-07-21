package com.mobiquity.LocalDelicacies.NavDrawer;

import com.mobiquity.LocalDelicacies.BaseEvent;

/**
 * Created by jwashington on 7/21/14.
 */
public class NavigationDrawerClickEvent extends BaseEvent{
    private String title;

    public NavigationDrawerClickEvent(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
}
