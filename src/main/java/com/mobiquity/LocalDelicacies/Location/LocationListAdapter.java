package com.mobiquity.LocalDelicacies.location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mobiquity.LocalDelicacies.ApplicationBus;
import com.mobiquity.LocalDelicacies.BaseListAdapter;
import com.mobiquity.LocalDelicacies.R;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/17/14.
 */
public class LocationListAdapter extends BaseListAdapter
{


    public LocationListAdapter(Context context, ArrayList<Location> items) {
        super(context, items);
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent )
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        final Location location = (Location) items.get( position );

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
        configureView(location, holder);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationBus.getInstance().post(new LocationClickedEvent(location));
            }
        });


        return convertView;
    }


    private void configureView(final Location location, final ViewHolder holder)
    {
        holder.name.setText(location.getName());
        holder.image.setImageResource(R.drawable.sample_city);

        if ( location.isBookmarked() )
        {
            holder.bookmarkButton.setImageDrawable( context.getResources().getDrawable( R.drawable.love ) );
        }
        else
        {
            holder.bookmarkButton.setImageDrawable( context.getResources().getDrawable( R.drawable.no_love ) );
        }

        holder.bookmarkButton.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                if ( location.isBookmarked() )
                {
                    holder.bookmarkButton.setImageResource( R.drawable.no_love );
                }
                else
                {
                    holder.bookmarkButton.setImageResource( R.drawable.love );
                }
                location.setBookmarked(!location.isBookmarked());
            }
        } );

    }




}
