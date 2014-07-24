package com.mobiquity.LocalDelicacies.location;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mobiquity.LocalDelicacies.*;
import com.mobiquity.LocalDelicacies.Filter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/17/14.
 */
public class LocationListAdapter extends BaseListAdapter
{

    public LocationListAdapter(Context context, ArrayList<Location> items)
    {
        this(context, items, new PermissiveFilter());
    }

    public LocationListAdapter(Context context, ArrayList<Location> items, Filter filter) {
        super(context, items, filter);
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
        if(holder==null) {
            return;
        }
        holder.name.setText(location.getTitle());
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

        if(filter.shouldDisplay(location))
        {
            holder.name.setVisibility(View.VISIBLE);
            holder.image.setVisibility(View.VISIBLE);
            holder.bookmarkButton.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.name.setVisibility(View.GONE);
            holder.image.setVisibility(View.GONE);
            holder.bookmarkButton.setVisibility(View.GONE);
        }

    }


    public void updateData(ArrayList<Location> locs) {
        items = locs;

        notifyDataSetChanged();
    }
}

