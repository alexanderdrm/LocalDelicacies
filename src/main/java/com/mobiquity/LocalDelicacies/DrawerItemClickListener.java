package com.mobiquity.LocalDelicacies;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/21/14.
 */
public class DrawerItemClickListener implements ListView.OnItemClickListener {

    private Context context;
    private String[] options;

    public DrawerItemClickListener(Context context, String[] options)
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
        Toast.makeText(context, options[position], Toast.LENGTH_SHORT).show();
    }
}
