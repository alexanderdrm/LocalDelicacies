package com.mobiquity.LocalDelicacies.location;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.mobiquity.LocalDelicacies.*;
import com.mobiquity.LocalDelicacies.delicacies.Delicacy;
import com.mobiquity.LocalDelicacies.filters.Filter;
import com.mobiquity.LocalDelicacies.filters.PermissiveFilter;
import com.mobiquity.LocalDelicacies.http.DataUpdateEvent;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/22/14.
 */
public class LocationDetailFragment extends BasePagesFragment {

    Location location;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate( R.layout.layout_view_pager,
                container,
                false );

        pager = (ViewPager)view.findViewById(R.id.pager);
        location = Location.createLocationFromBundle(getArguments());
        prepareAdapter(inflater.getContext());
        return view;
    }

    private void prepareAdapter(Context context)
    {
        TextView title;
        ImageView image;
        TextView description;
        ImageView bookmarkButton;
        RatingBar ratingBar;

        //Setting up the location Details
        CustomDetailView locationView = new CustomDetailView(context);

        title = (TextView) locationView.findViewById(R.id.name);
        title.setText(location.getTitle());

        image = (ImageView) locationView.findViewById(R.id.image);
        Picasso.with(context)
                .load(location.getImageUrl())
                .placeholder(R.drawable.sample_city)
                .error(R.drawable.error_image)
                .into(image);

        description = (TextView) locationView.findViewById(R.id.description);
        description.setText(location.getDescription());

        bookmarkButton = (ImageView) locationView.findViewById(R.id.bookmarked_button);

        ratingBar = (RatingBar) locationView.findViewById(R.id.ratingBar);
        ratingBar.setVisibility(View.GONE);

        ArrayList<CustomDetailView> views = new ArrayList<CustomDetailView>();
        views.add(locationView);

        //Setting up views for each delicacy
        for(Delicacy delicacy : location.getDelicacies())
        {
            CustomDetailView delicacyView = new CustomDetailView(context);

            title = (TextView) delicacyView.findViewById(R.id.name);
            title.setText(delicacy.getName());

            image = (ImageView) delicacyView.findViewById(R.id.image);
            Picasso.with(context)
                    .load(delicacy.getImageUrl())
                    .placeholder(R.drawable.sample_city)
                    .error(R.drawable.error_image)
                    .into(image);

            description = (TextView) delicacyView.findViewById(R.id.description);
            description.setText(delicacy.getDescription());

            bookmarkButton = (ImageView) delicacyView.findViewById(R.id.bookmarked_button);

            ratingBar = (RatingBar) delicacyView.findViewById(R.id.ratingBar);
            views.add(delicacyView);
        }

        pager.setAdapter(new DetailPagesAdapter(views));
    }

    public class DetailPagesAdapter extends PagerAdapter
    {

        private ArrayList<CustomDetailView> pages;

        public DetailPagesAdapter(ArrayList<CustomDetailView> pages)
        {
            this.pages = pages;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(pages.get(position));
            return pages.get(position);
        }

        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }
    }

}
