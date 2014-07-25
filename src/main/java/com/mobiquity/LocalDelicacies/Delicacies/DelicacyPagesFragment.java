package com.mobiquity.LocalDelicacies.delicacies;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.mobiquity.LocalDelicacies.ApplicationBus;
import com.mobiquity.LocalDelicacies.DelicacyData;
import com.mobiquity.LocalDelicacies.R;
import com.mobiquity.LocalDelicacies.filters.Filter;
import com.mobiquity.LocalDelicacies.filters.PermissiveFilter;
import com.mobiquity.LocalDelicacies.location.Location;

import java.util.ArrayList;

/**
 * Created by dalexander on 7/24/14.
 */
public class DelicacyPagesFragment extends Fragment {

    ViewPager pager;
    DelicacyPagesAdapter adapter;
    //volatile ArrayList<Delicacy> allDelicacies = TestModule.generateTestDelicacies();

    ArrayList<DelicacyListAdapter> adapters = new ArrayList<DelicacyListAdapter>();
    public static String TAG="DELICACY_PAGES_FRAGMENT";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_view_pager, container, false);

        pager = (ViewPager) rootView.findViewById(R.id.pager);
        adapter = generateTestAdapter(inflater.getContext());
        pager.setAdapter(adapter);

        return rootView;
    }

    private DelicacyPagesAdapter generateTestAdapter(Context context)
    {
        ListView all = new ListView(context);
        DelicacyListAdapter delicacyListAdapter = new DelicacyListAdapter(context, DelicacyData.getDelicacyData(), new PermissiveFilter());
        all.setAdapter(delicacyListAdapter);

        adapters.add(delicacyListAdapter);

        ListView pinned =  new ListView(context);
        DelicacyListAdapter pinnedListAdapter = new DelicacyListAdapter(context, DelicacyData.getDelicacyData(), new Filter() {
            @Override
            public boolean shouldDisplay(Object object) {
                return ((Location)object).isBookmarked();
            }
        });
        pinned.setAdapter(pinnedListAdapter);

        adapters.add(pinnedListAdapter);

        ArrayList<ListView> views = new ArrayList<ListView>();
        views.add(all);
        views.add(pinned);
        return new DelicacyPagesAdapter(views);
    }

    /*@Subscribe
    public void onDataUpdated(DataUpdateEvent due) {
        allDelicacies = due.getDelicacies();

        for(DelicacyListAdapter dladapter : adapters) {
            dladapter.updateData(allDelicacies);
        }
    }*/

    @Override
    public void onResume()
    {
        super.onResume();
        configureActionBar();

        ApplicationBus.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        ApplicationBus.getInstance().unregister(this);
        getActivity().getActionBar().removeAllTabs();
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

    }

    private void configureActionBar()
    {
        final ActionBar actionBar = getActivity().getActionBar();
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
        actionBar.addTab(
                actionBar.newTab()
                        .setText(getResources()
                                .getString(R.string.all))
                        .setTabListener(tabListener));
        actionBar.addTab(
                actionBar.newTab()
                        .setText(getResources()
                                .getString(R.string.bookmarked))
                        .setTabListener(tabListener));
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

    protected class DelicacyPagesAdapter extends PagerAdapter
    {

        private ArrayList<ListView> pages;

        public DelicacyPagesAdapter(ArrayList<ListView> pages)
        {
            this.pages = pages;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(pages.get(position));
            return pages.get(position);
        }

        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }
}
