package com.mobiquity.LocalDelicacies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/17/14.
 */
public class LocationListAdapter extends BaseAdapter
{
    private Context           theContext;
    private ArrayList<Location> locationList;

    public LocationListAdapter( Context theContext,
                                ArrayList<Location> locationList )
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
    public View getView( int position, View convertView, ViewGroup parent )
    {
        LayoutInflater inflater = (LayoutInflater) theContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        ViewHolder holder;

        if ( convertView == null )
        {
            convertView = inflater.inflate( R.layout.list_location,
                                            parent,
                                            false );
            holder = ViewHolder.createViewHolder( convertView );
            convertView.setTag( holder );
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }

    public static class ViewHolder
    {
        public TextView  locationName;
        public ImageView locationImage;

        public static ViewHolder createViewHolder( View theView )
        {
            ViewHolder holder = new ViewHolder();

            holder.locationName = (TextView) theView.findViewById( R.id.location_name );
            holder.locationImage = (ImageView) theView.findViewById( R.id.location_image );

            return holder;
        }
    }
}
