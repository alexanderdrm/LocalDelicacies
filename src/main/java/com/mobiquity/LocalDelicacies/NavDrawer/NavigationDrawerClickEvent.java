package com.mobiquity.LocalDelicacies.navdrawer;

import com.mobiquity.LocalDelicacies.BaseEvent;

/**
 * Created by jwashington on 7/21/14.
 */
public class NavigationDrawerClickEvent extends BaseEvent{
    private String title;
    private Class fragmentClass;


    public NavigationDrawerClickEvent(String title, Class fragmentClass)
    {
        this.title = title;
        this.fragmentClass = fragmentClass;
    }

    public String getTitle()
    {
        return title;
    }
    public Class getFragmentClass() { return fragmentClass; }
}
