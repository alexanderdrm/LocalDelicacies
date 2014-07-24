package com.mobiquity.LocalDelicacies.http;

import android.app.Activity;
import com.mobiquity.LocalDelicacies.ApplicationBus;
import com.mobiquity.LocalDelicacies.TestModule;
import com.mobiquity.LocalDelicacies.location.LocationData;
import com.mobiquity.LocalDelicacies.support.BusHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.Transcript;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.mobiquity.LocalDelicacies.support.FragmentUtil.createActivity;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by jwashington on 7/24/14.
 */
@RunWith(RobolectricTestRunner.class)
public class DataFetchTaskTest {

    private Activity activity;
    private BusHelper busHelper;

    @Before
    public void setUp() throws Exception
    {
        activity = createActivity();

        DataFetchTask task = new DataFetchTask(activity)
        {
            @Override
            protected List<LocationData> doInBackground(String... params) {
                return new ArrayList<LocationData>();
            }
        };

        Robolectric.getBackgroundScheduler().runOneTask();
        Robolectric.getUiThreadScheduler().runOneTask();

        busHelper = new BusHelper();
        ApplicationBus.getInstance().register(busHelper);

        //task.execute();

    }
     @Test
    public void shouldPostDataUpdateEvent() throws Exception
    {
        new DataFetchTask(activity).onPostExecute(new ArrayList<LocationData>());
        assertTrue(busHelper.getLastEvent() instanceof DataUpdateEvent);
    }




}
