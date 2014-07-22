package com.mobiquity.LocalDelicacies.Delicacies;

import android.app.Activity;
import com.mobiquity.LocalDelicacies.Location.Location;
import com.mobiquity.LocalDelicacies.support.FragmentUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;


/**
 * Created by jwashington on 7/22/14.
 */
@RunWith(RobolectricTestRunner.class)
public class DelicacyListAdapterTest
{

    private DelicacyListAdapter delicacyListAdapter;
    private Activity activity;
    private ArrayList<Delicacy> testData;

    @Before
    public void setUp()
    {
        activity = FragmentUtil.createActivity();
        testData = generateTestData();
        delicacyListAdapter = new DelicacyListAdapter(activity, testData);
    }

    @Test
    public void shouldNotBeNull()
    {
        assertNotNull(delicacyListAdapter);
    }

    @Test
    public void getCount_shouldReturnCorrectCount()
    {
        assertTrue(delicacyListAdapter.getCount() == testData.size());
    }

    @Test
    public void getItem_shouldReturnCorrectItem()
    {
        for ( int index = 0; index < testData.size(); index++ )
        {
            assertThat( (Delicacy) delicacyListAdapter.getItem( index ),
                    equalTo( testData.get( index ) ) );
        }
    }

    @Test
    public void getItemId_shouldReturnCorrectItemId()
    {
        for ( int index = 0; index < testData.size(); index++ )
        {
            assertThat( (int) delicacyListAdapter.getItemId(index),
                    equalTo( index ) );
        }
    }

    @Test
    public void getView_shouldNotBeNull() throws Exception
    {
        for ( int index = 0; index < testData.size(); index++ )
        {
            assertNotNull(delicacyListAdapter.getView(index, null, null));
        }
    }

    private ArrayList<Delicacy> generateTestData()
    {
        ArrayList<Delicacy> data = new ArrayList<Delicacy>();
        data.add(new Delicacy());
        data.add(new Delicacy());
        return data;
    }
}
