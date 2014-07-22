package com.mobiquity.LocalDelicacies.Delicacies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.mobiquity.LocalDelicacies.R;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/22/14.
 */
public class DelicacyListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Delicacy> delicacies;

    public DelicacyListAdapter(Context context, ArrayList<Delicacy> delicacies)
    {
        this.context = context;
        this.delicacies = delicacies;
    }
    @Override
    public int getCount() {
        return delicacies.size();
    }

    @Override
    public Object getItem(int position) {
        return delicacies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.delicacy_list_item, parent, false);
        return convertView;
    }
}
