package com.mobiquity.LocalDelicacies.delicacies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mobiquity.LocalDelicacies.ApplicationBus;
import com.mobiquity.LocalDelicacies.BaseListAdapter;
import com.mobiquity.LocalDelicacies.R;
import com.mobiquity.LocalDelicacies.UIUtil;
import com.mobiquity.LocalDelicacies.filters.Filter;
import com.mobiquity.LocalDelicacies.http.DataUpdateEvent;
import com.mobiquity.LocalDelicacies.location.Location;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/22/14.
 */
public class DelicacyListAdapter extends BaseListAdapter {

    boolean ignoreNextDataUpdate = false;

    public DelicacyListAdapter(Context context, ArrayList<Delicacy> items, Filter filter) {
        super(context, items, filter);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Delicacy delicacy = (Delicacy) items.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.layout_text_image,
                    parent,
                    false);
            holder = ViewHolder.createViewHolder(convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        configureView(delicacy, holder);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplicationBus.getInstance().post(new DelicacyClickedEvent(delicacy));
            }
        });
        return convertView;
    }

    private void configureView(final Delicacy delicacy, final ViewHolder holder)
    {
        holder.name.setText(delicacy.getTitle());
        //holder.image.setImageResource(R.drawable.sample_delicacy);
        Picasso.with(context)
                .load(delicacy.getImageUrl())
                .placeholder(R.drawable.sample_delicacy)
                .error(R.drawable.error_image)
                .into(holder.image);

        if(delicacy.isBookmarked())
        {
            holder.bookmarkButton.setImageResource(R.drawable.love);
        }
        else
        {
            holder.bookmarkButton.setImageResource(R.drawable.no_love);
        }

        holder.bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delicacy.setBookmarked(!delicacy.isBookmarked());
                if(delicacy.isBookmarked())
                {
                    UIUtil.setImageDrawableCrossFade(holder.bookmarkButton,
                            context.getResources().getDrawable(R.drawable.love));
                }
                else
                {
                    UIUtil.setImageDrawableCrossFade(holder.bookmarkButton,
                            context.getResources().getDrawable(R.drawable.no_love));
                }
                ignoreNextDataUpdate = true;
                ApplicationBus.getInstance().post(new DataUpdateEvent(null, (ArrayList<Delicacy>) items));
            }
        });

        if(filter.shouldDisplay(delicacy))
        {
            holder.name.setVisibility(View.VISIBLE);
            holder.image.setVisibility(View.VISIBLE);
            holder.bookmarkButton.setVisibility(View.VISIBLE);
            holder.ratingBar.setVisibility(View.VISIBLE);

        }
        else
        {
           holder.name.setVisibility(View.GONE);
            holder.image.setVisibility(View.GONE);
            holder.bookmarkButton.setVisibility(View.GONE);
            holder.ratingBar.setVisibility(View.GONE);
        }

        holder.ratingBar.setIsIndicator(true);
        holder.ratingBar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });

        holder.ratingBar.setRating(delicacy.getRatingInHalfStars() / 2f);
    }

    public void updateData(ArrayList<Delicacy> locs) {
        if(ignoreNextDataUpdate) {
            ignoreNextDataUpdate = false;
            return;
        }

        items = locs;
        notifyDataSetChanged();
    }

}
