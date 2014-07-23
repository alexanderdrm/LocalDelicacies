package com.mobiquity.LocalDelicacies.navdrawer;

import com.mobiquity.LocalDelicacies.BaseEvent;

/**
 * Created by jwashington on 7/21/14.
 */
public class NavigationDrawerClickEvent extends BaseEvent{
    private String title;
    private String fragmentTag;

    public NavigationDrawerClickEvent(String title, String fragmentTag)
    {
        this.title = title;
        this.fragmentTag = fragmentTag;
    }

    public String getTitle()
    {
        return title;
    }

    public String getFragmentTag() {
        return fragmentTag;
    }
}
