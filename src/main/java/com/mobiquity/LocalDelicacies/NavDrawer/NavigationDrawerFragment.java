package com.mobiquity.LocalDelicacies.navdrawer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.mobiquity.LocalDelicacies.ApplicationBus;
import com.mobiquity.LocalDelicacies.R;
import com.mobiquity.LocalDelicacies.delicacies.DelicacyPagesFragment;
import com.mobiquity.LocalDelicacies.location.LocationPagesFragment;

import java.util.ArrayList;

/**
 * @since 1.0
 */
public class NavigationDrawerFragment extends Fragment
{

    private ArrayList<String> navigationTitles;
    private ArrayList<Class> fragmentClasses;
    private ListView drawerList;

    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState )
    {
        setupNavigationDrawerTitlesAndFragments();

        View layout = inflater.inflate( R.layout.fragment_navigation_drawer, container, false );
        drawerList = (ListView) layout.findViewById(R.id.drawer_list);
        drawerList.setAdapter( new ArrayAdapter<String>(inflater.getContext(), R.layout.drawer_list_item, navigationTitles));
        drawerList.setOnItemClickListener(
                new NavigationDrawerItemClickListener(
                        inflater.getContext(),
                        navigationTitles,
                        fragmentClasses)
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
        navigationTitles = new ArrayList<String>();
        navigationTitles.add(getResources().getString(R.string.locations));
        navigationTitles.add(getResources().getString(R.string.delicaices));

        fragmentClasses = new ArrayList<Class>();
        fragmentClasses.add(LocationPagesFragment.class);
        fragmentClasses.add(DelicacyPagesFragment.class);
    }

}
