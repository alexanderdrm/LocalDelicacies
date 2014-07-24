package com.mobiquity.LocalDelicacies;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.filters.Filter;
import com.mobiquity.LocalDelicacies.filters.PermissiveFilter;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/22/14.
 */
public class BaseListAdapter extends BaseAdapter {

    protected Context context;
    protected ArrayList<?> items;

    protected Filter filter;

    public BaseListAdapter(Context context, ArrayList<?> items) {
        this(context, items, new PermissiveFilter());
    }

    public BaseListAdapter(Context context, ArrayList<?> items, Filter filter)
    {
        this.context = context;
        this.items = items;
        this.filter = filter;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    public static class ViewHolder
    {
        public TextView name;
        public ImageView image;
        public ImageView bookmarkButton;

        public static ViewHolder createViewHolder( View theView )
        {
            ViewHolder holder = new ViewHolder();

            holder.name = (TextView) theView.findViewById( R.id.name);
            holder.image = (ImageView) theView.findViewById( R.id.image);
            holder.bookmarkButton = (ImageView) theView.findViewById( R.id.bookmarked_button);

            return holder;
        }
    }
}
