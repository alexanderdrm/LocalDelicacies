package com.mobiquity.LocalDelicacies.delicacies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mobiquity.LocalDelicacies.ApplicationBus;
import com.mobiquity.LocalDelicacies.BaseListAdapter;
import com.mobiquity.LocalDelicacies.R;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/22/14.
 */
public class DelicacyListAdapter extends BaseListAdapter {


    public DelicacyListAdapter(Context context, ArrayList<Delicacy> items) {
        super(context, items);
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
        holder.name.setText(delicacy.getName());
        holder.image.setImageResource(R.drawable.sample_delicacy);

        if(delicacy.isBookmarked())
        {
            holder.bookmarkButton.setImageResource(R.drawable.no_love);
        }
        else
        {
            holder.bookmarkButton.setImageResource(R.drawable.love);
        }

        holder.bookmarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(delicacy.isBookmarked())
                {
                    holder.bookmarkButton.setImageResource(R.drawable.love);
                }
                else
                {
                    holder.bookmarkButton.setImageResource(R.drawable.no_love);
                }
                delicacy.setBookmarked(!delicacy.isBookmarked());
            }
        });
    }

}
