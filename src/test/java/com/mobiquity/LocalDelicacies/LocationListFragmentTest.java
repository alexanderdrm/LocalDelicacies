package com.mobiquity.LocalDelicacies;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.mobiquity.LocalDelicacies.support.FragmentUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class LocationListFragmentTest {

    private Fragment locationListFragment;
    private View locationList;

    @Before
    public void setUp()
    {
        locationListFragment = new LocationListFragment();
        FragmentUtil.startFragment(locationListFragment);
    }

    @Test
    public void shouldNotBeNull()
    {
        assertNotNull(locationListFragment);
    }

    @Test
    public void shouldContainListView()
    {
        locationList = locationListFragment.getView().findViewById(R.id.location_list);
        assertNotNull(locationList);
        assertTrue(locationList instanceof ListView);
    }

    @Test
    public void shouldContainAdapter()
    {
        locationList = locationListFragment.getView().findViewById(R.id.location_list);
        ListAdapter adapter = ((ListView)locationList).getAdapter();
        assertNotNull(adapter);
        assertTrue(adapter instanceof LocationListAdapter);
    }

    @Test
    public void shouldHideListViewIfEmpty()
    {
        TestEmptyLocationListFragment emptyFragment = new TestEmptyLocationListFragment();
        FragmentUtil.startFragment(emptyFragment);
        assertNotNull(emptyFragment);

        ListView listView = (ListView) emptyFragment.getView().findViewById(R.id.location_list);
        assertTrue(listView.getAdapter().isEmpty());
        assertThat(listView.getVisibility(), equalTo(View.INVISIBLE));

    }

    private class TestEmptyLocationListFragment extends LocationListFragment
    {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = super.onCreateView(inflater, container, savedInstanceState);
            locationListView.setAdapter(new LocationListAdapter(inflater.getContext(), new ArrayList<Location>()));
            return rootView;
        }
    }





}