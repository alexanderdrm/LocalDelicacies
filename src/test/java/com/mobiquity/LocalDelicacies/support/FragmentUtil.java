package com.mobiquity.LocalDelicacies.support;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import org.robolectric.Robolectric;

/**
 * Created by jwashington on 7/18/14.
 */
public class FragmentUtil {
    public static void startFragment( Fragment fragment )
    {
        Activity activity = createActivity();

        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction()
                .add( fragment, null )
                .commit();
    }

    public static Activity createActivity()
    {
        return Robolectric.buildActivity(Activity.class)
                .create()
                .start()
                .resume()
                .get();
    }
}
