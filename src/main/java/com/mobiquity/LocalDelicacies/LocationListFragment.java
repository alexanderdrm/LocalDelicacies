package com.mobiquity.LocalDelicacies;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * @since 1.0
 */
public class LocationListFragment extends Fragment
{

    private LocationListAdapter adapter;
    private View listView;

    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState )
    {
        listView = inflater.inflate( R.layout.fragment_location_list,
                                        container,
                                        false );
        return listView;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter = new LocationListAdapter(getActivity(), createDummyData());
        ((ListView)listView).setAdapter(adapter);
    }

    private ArrayList<Location> createDummyData()
    {
        ArrayList<Location> list = new ArrayList<Location>();
        list.add(new Location("Las Vegas", null));
        list.add(new Location("Nevada", null, true));
        list.add(new Location("Philly", null, true));
        list.add(new Location("Gainseville", null, true));
        list.add(new Location("Tallahassee", null, true));
        list.add(new Location("Amsterdam", null, true));
        list.add(new Location("Oaxaca", null, true));
        list.add(new Location("Portland", null, true));
        list.add(new Location("London", null, true));
        return list;
    }
}
