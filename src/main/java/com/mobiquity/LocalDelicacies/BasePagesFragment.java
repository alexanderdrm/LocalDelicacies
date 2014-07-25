package com.mobiquity.LocalDelicacies;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mobiquity.LocalDelicacies.location.Location;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/25/14.
 */
public class BasePagesFragment extends Fragment {

    protected ViewPager pager;
    protected BasePagesAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
        ApplicationBus.getInstance().register(this);

    }

    @Override
    public void onPause() {
        super.onPause();

        ApplicationBus.getInstance().unregister(this);
    }

    protected void configureActionBar(final ActionBar actionBar, ArrayList<ActionBar.Tab> tabs)
    {
        getActivity().getActionBar().removeAllTabs();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
            }
        };
        for(int index = 0; index < tabs.size(); index++)
        {
            ActionBar.Tab tab = tabs.get(index);
            tab.setTabListener(tabListener);
            actionBar.addTab(tab);
        }

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
