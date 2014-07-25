package com.mobiquity.LocalDelicacies.navdrawer;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.mobiquity.LocalDelicacies.ApplicationBus;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/21/14.
 */
public class NavigationDrawerItemClickListener implements ListView.OnItemClickListener {

    private Context context;
    private ArrayList<String> titles;
    private ArrayList<Class> fragmentClasses;

    public NavigationDrawerItemClickListener(Context context, ArrayList<String> titles, ArrayList<Class> fragmentClasses)
    {
        this.context = context;
        this.titles = titles;
        this.fragmentClasses = fragmentClasses;
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        selectItem(position);

    }

    private void selectItem(int position)
    {
        ApplicationBus.getInstance().post(new NavigationDrawerClickEvent(titles.get(position), fragmentClasses.get(position)));
        Toast.makeText(context, titles.get(position), Toast.LENGTH_SHORT).show();
    }
}
