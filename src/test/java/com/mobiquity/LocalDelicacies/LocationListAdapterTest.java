package com.mobiquity.LocalDelicacies;

import android.app.Activity;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@RunWith (RobolectricTestRunner.class)
public class LocationListAdapterTest
{

    private LocationListAdapter locationListAdapter;
    private Activity            activity;
    private ArrayList<String>   testData;

    @Before
    public void setUp() throws Exception
    {
        activity = Robolectric.buildActivity( Activity.class )
                              .create()
                              .start()
                              .resume()
                              .get();

        testData = generateTestData();
        locationListAdapter = new LocationListAdapter( activity, testData );
    }

    @Test
    public void shouldNotBeNull()
    {
        assertNotNull( locationListAdapter );
    }

    @Test
    public void getCount_shouldReturnCorrectCount()
    {
        assertTrue( locationListAdapter.getCount() == testData.size() );
    }

    @Test
    public void getItem_shouldReturnCorrectItem()
    {
        for ( int index = 0; index < testData.size(); index++ )
        {
            assertThat( (String) locationListAdapter.getItem( index ),
                        equalTo( testData.get( index ) ) );
        }
    }

    @Test
    public void getItemId_shouldReturnCorrectItemId()
    {
        for ( int index = 0; index < testData.size(); index++ )
        {
            assertThat( (int) locationListAdapter.getItemId( index ),
                        equalTo( index ) );
        }
    }

    @Test
    public void getView_shouldNotBeNull() throws Exception
    {
        for ( int index = 0; index < testData.size(); index++ )
        {
            assertNotNull( locationListAdapter.getView( index,
                                                        new View( activity ),
                                                        null ) );
        }
    }

    private ArrayList<String> generateTestData()
    {
        return new ArrayList<String>();
    }
}
