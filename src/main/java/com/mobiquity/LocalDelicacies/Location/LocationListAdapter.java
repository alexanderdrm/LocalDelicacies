package com.mobiquity.LocalDelicacies.Location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.ApplicationBus;
import com.mobiquity.LocalDelicacies.R;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/17/14.
 */
public class LocationListAdapter extends BaseAdapter
{
    private Context             theContext;
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
        final Location location = locationList.get( position );

        ViewHolder holder;
        if ( convertView == null )
        {
            convertView = inflater.inflate( R.layout.layout_text_image,
                                            parent,
                                            false );
            holder = ViewHolder.createViewHolder( convertView );
            convertView.setTag( holder );
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.locationName.setText( location.getName() );


        configureLocationView(location, holder);
        configureLoveButton( location, holder );
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationBus.getInstance().post(new LocationClickedEvent(location));
            }
        });


        return convertView;
    }

    private void configureLoveButton( final Location location,
                                      final ViewHolder holder )
    {
        holder.lovedButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                if ( location.isLoved() )
                {
                    holder.lovedButton.setImageResource( R.drawable.no_love );
                }
                else
                {
                    holder.lovedButton.setImageResource( R.drawable.love );
                }
                location.setLoved( !location.isLoved() );
            }
        } );
    }

    private void configureLocationView( Location location, ViewHolder holder )
    {
        if ( location.isLoved() )
        {
            holder.lovedButton.setImageDrawable( theContext.getResources().getDrawable( R.drawable.love ) );
        }
        else
        {
            holder.lovedButton.setImageDrawable( theContext.getResources().getDrawable( R.drawable.no_love ) );
        }
    }

    protected static class ViewHolder
    {
        public TextView  locationName;
        public ImageView locationImage;
        public ImageView lovedButton;

        public static ViewHolder createViewHolder( View theView )
        {
            ViewHolder holder = new ViewHolder();

            holder.locationName = (TextView) theView.findViewById( R.id.name);
            holder.locationImage = (ImageView) theView.findViewById( R.id.image);
            holder.lovedButton = (ImageView) theView.findViewById( R.id.bookmarked_button);

            return holder;
        }
    }


}
