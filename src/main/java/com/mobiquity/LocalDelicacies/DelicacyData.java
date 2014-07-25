package com.mobiquity.LocalDelicacies;

import android.util.Log;
import com.mobiquity.LocalDelicacies.delicacies.Delicacy;
import com.mobiquity.LocalDelicacies.http.DataUpdateEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by dalexander on 7/24/14.
 */
public class DelicacyData {
    private static DelicacyData instance = new DelicacyData();

    private ArrayList<Delicacy> delicacies = TestModule.generateTestDelicacies();
    private Object listener = new Object() {
        @Subscribe
        public void onDelicacyInformationUpdated(DataUpdateEvent due) {
            delicacies = due.getDelicacies();
        }
    };

    private DelicacyData() {
        ApplicationBus.getInstance().register(listener);
    }

    public static ArrayList<Delicacy> getDelicacyData() {
        return instance.delicacies;
    }

    public void touchIt() {

    }

    public static void touch() {
        instance.touchIt();
    }
}
