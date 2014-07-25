package com.mobiquity.LocalDelicacies.navdrawer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.mobiquity.LocalDelicacies.ApplicationBus;
import com.mobiquity.LocalDelicacies.delicacies.DelicacyListFragment;
import com.mobiquity.LocalDelicacies.R;
import com.mobiquity.LocalDelicacies.delicacies.DelicacyPagesFragment;
import com.mobiquity.LocalDelicacies.location.LocationPagesFragment;

import java.util.ArrayList;

/**
 * @since 1.0
 */
public class NavigationDrawerFragment extends Fragment
{

    private ArrayList<String> navigationListTitles;
    private ArrayList<String> fragmentTags;
    private ListView drawerList;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNavigationDrawerTitlesAndFragments();
    }

    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState )
    {
        View layout = inflater.inflate( R.layout.fragment_navigation_drawer, container, false );
        drawerList = (ListView) layout.findViewById(R.id.drawer_list);
        drawerList.setAdapter( new ArrayAdapter<String>(inflater.getContext(), R.layout.drawer_list_item, navigationListTitles));
        drawerList.setOnItemClickListener(
                new NavigationDrawerItemClickListener(
                        inflater.getContext(),
                        navigationListTitles,
                        fragmentTags)
        );

        return layout;
    }

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


    private void setupNavigationDrawerTitlesAndFragments()
    {
        navigationListTitles = new ArrayList<String>();
        navigationListTitles.add(getResources().getString(R.string.locations));
        navigationListTitles.add(getResources().getString(R.string.delicaices));

        fragmentTags = new ArrayList<String>();
        fragmentTags.add(LocationPagesFragment.TAG);
        fragmentTags.add(DelicacyPagesFragment.TAG);
    }

}
