package com.mobiquity.LocalDelicacies;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import javax.swing.text.html.ImageView;

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
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_detail, this, true);
    }


}
