package com.mobiquity.LocalDelicacies;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
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
        public RatingBar ratingBar;

        public static ViewHolder createViewHolder( View theView )
        {
            ViewHolder holder = new ViewHolder();

            holder.name = (TextView) theView.findViewById( R.id.name);
            holder.image = (ImageView) theView.findViewById( R.id.image);
            holder.bookmarkButton = (ImageView) theView.findViewById( R.id.bookmarked_button);
            holder.ratingBar = (RatingBar) theView.findViewById(R.id.ratingBar);

            return holder;
        }
    }

    public void setImageDrawableCrossFade(ImageView imageView, Drawable newDrawable)
    {
        Drawable currentDrawable = imageView.getDrawable();
        if(currentDrawable != null) {
            Drawable [] arrayDrawable = new Drawable[2];
            arrayDrawable[0] = currentDrawable;
            arrayDrawable[1] = newDrawable;
            TransitionDrawable transitionDrawable = new TransitionDrawable(arrayDrawable);
            transitionDrawable.setCrossFadeEnabled(true);
            imageView.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(500);
        } else {
            imageView.setImageDrawable(newDrawable);
        }
    }
}
