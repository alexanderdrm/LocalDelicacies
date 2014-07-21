package com.mobiquity.LocalDelicacies.NavDrawer;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.mobiquity.LocalDelicacies.ApplicationBus;

/**
 * Created by jwashington on 7/21/14.
 */
public class NavigationDrawerItemClickListener implements ListView.OnItemClickListener {

    private Context context;
    private String[] options;

    public NavigationDrawerItemClickListener(Context context, String[] options)
    {
        this.context = context;
        this.options = options;
    }

    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        selectItem(position);

    }

    private void selectItem(int position)
    {
        ApplicationBus.getInstance().post(new NavigationDrawerClickEvent(options[position]));
        Toast.makeText(context, options[position], Toast.LENGTH_SHORT).show();
    }
}
