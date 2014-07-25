package com.mobiquity.LocalDelicacies.delicacies;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.mobiquity.LocalDelicacies.R;
import com.mobiquity.LocalDelicacies.TestModule;
import com.mobiquity.LocalDelicacies.filters.PermissiveFilter;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/22/14.
 */
public class DelicacyListFragment extends Fragment {

    public static final String TAG = "DELICACY_FRAGMENT_TAG";

    protected View rootView;
    protected ListView delicacyListView;
    protected DelicacyListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_delicacy_list, container, false);
        delicacyListView = (ListView) rootView.findViewById(R.id.delicacy_list);
        adapter = createAdapter(inflater.getContext());
        delicacyListView.setAdapter(adapter);
        return rootView;
    }

    protected DelicacyListAdapter createAdapter(Context context)
    {
        return new DelicacyListAdapter(context, TestModule.generateTestDelicacies(), new PermissiveFilter());
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter.isEmpty())
        {
            delicacyListView.setVisibility(View.GONE);
        }
    }


}
