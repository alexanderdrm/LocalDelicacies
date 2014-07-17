package com.mobiquity.LocalDelicacies;

import android.app.Activity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class LocationListAdapterTest {

    private LocationListAdapter locationListAdapter;
    private Activity activity;
    private ArrayList<String> testData;

    @Before
    public void setUp() throws Exception {
        activity = Robolectric.buildActivity(Activity.class)
                .create()
                .start()
                .resume()
                .get();

        testData = generateTestData();
        locationListAdapter = new LocationListAdapter(activity, testData);

    }

    @Test
    public void shouldNotBeNull()
    {
        assertNotNull(locationListAdapter);
    }

    @Test
    public void getCount_shouldReturnCorrectCount()
    {
        assertTrue(locationListAdapter.getCount() == testData.size());
    }

    @Test
    public void getItem_shouldReturnCorrectItem()
    {
        for(int i = 0; i < testData.size(); i++)
        {
            assertThat((String) locationListAdapter.getItem(i), equalTo(testData.get(i)));
        }
    }

    @Test
    public void getItemId_shouldReturnCorrectItemId()
    {
        for(int i = 0; i < testData.size(); i++)
        {
            assertThat((int) locationListAdapter.getItemId(i), equalTo(i));
        }
    }

    private ArrayList<String> generateTestData()
    {
        return new ArrayList<String>();
    }

}