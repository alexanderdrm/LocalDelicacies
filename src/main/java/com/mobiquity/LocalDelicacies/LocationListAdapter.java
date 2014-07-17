package com.mobiquity.LocalDelicacies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/17/14.
 */
public class LocationListAdapter extends BaseAdapter
{
    private Context           theContext;
    private ArrayList<String> locationList;

    public LocationListAdapter( Context theContext,
                                ArrayList<String> locationList )
    {
        this.theContext = theContext;
        this.locationList = locationList;
    }

    @Override
    public int getCount()
    {
        return locationList.size();
    }

    @Override
    public Object getItem( int position )
    {
        return locationList.get( position );
    }

    @Override
    public long getItemId( int position )
    {
        return position;
    }

    @Override
    public View getView( int i, View view, ViewGroup viewGroup )
    {
        return null;
    }
}
