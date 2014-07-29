package com.mobiquity.LocalDelicacies.support;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import org.robolectric.Robolectric;

/**
 * Created by jwashington on 7/18/14.
 */
public class FragmentUtil {
    public static void startFragment( Fragment fragment )
    {
        FragmentActivity activity = createActivity();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add( fragment, null )
                .commit();
    }

    public static FragmentActivity createActivity()
    {
        return Robolectric.buildActivity(FragmentActivity.class)
                .create()
                .start()
                .resume()
                .get();
    }
}
