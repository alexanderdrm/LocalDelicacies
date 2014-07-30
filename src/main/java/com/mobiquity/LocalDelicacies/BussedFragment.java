package com.mobiquity.LocalDelicacies;

import android.support.v4.app.Fragment;

/**
 * Created by dalexander on 7/30/14.
 */
public class BussedFragment extends Fragment {
    @Override
    public void onResume() {
        super.onResume();

        ApplicationBus.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        ApplicationBus.getInstance().unregister(this);
    }
}
