package com.mobiquity.LocalDelicacies.Delicacies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
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

    //Todo: View Holder
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Delicacy delicacy = delicacies.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.layout_text_image, parent, false);

        TextView delicacyName = (TextView) convertView.findViewById(R.id.name);
        delicacyName.setText(delicacy.getName());
        return convertView;
    }
}
