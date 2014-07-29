package com.mobiquity.LocalDelicacies;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;


/**
 * Created by jwashington on 7/28/14.
 */
public class CustomDetailView extends LinearLayout {

    private TextView title;
    private ImageView image;
    private RatingBar ratingBar;
    private TextView description;
    private ImageView bookmarkedButton;


    public CustomDetailView(Context context) {
        super(context);
        init(context);
    }

    public CustomDetailView(Context context, AttributeSet attributeSet)
    {
        super(context, attributeSet);
        init(context);
        setupChildren();
        normalizePicture();
    }

    private void init(Context context)
    {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_detail, this, true);
        setOrientation(VERTICAL);
    }

    private void setupChildren()
    {
        title = (TextView) findViewById(R.id.name);
        image = (ImageView) findViewById(R.id.image);
        description = (TextView) findViewById(R.id.description);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        bookmarkedButton = (ImageView) findViewById(R.id.bookmarked_button);
    }

    private void normalizePicture()
    {

    }



}
