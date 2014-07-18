package com.mobiquity.LocalDelicacies;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;

import static com.mobiquity.LocalDelicacies.support.Assert.assertViewIsVisible;
import static com.mobiquity.LocalDelicacies.support.ResourceLocator.getDrawable;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

@RunWith (RobolectricTestRunner.class)
public class LocationListAdapterTest
{

    private LocationListAdapter locationListAdapter;
    private Activity            activity;
    private ArrayList<Location> testData;

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
            assertThat( (Location) locationListAdapter.getItem( index ),
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
            assertNotNull( getViewById( index ) );
        }
    }

    private View createRecycledView()
    {
        View theView = View.inflate( activity, R.layout.list_location, null );
        LocationListAdapter.ViewHolder holder = LocationListAdapter.ViewHolder.createViewHolder( theView );
        theView.setTag( holder );
        return theView;
    }

    @Test
    public void getView_shouldBeConfigured() throws Exception
    {
        for ( int index = 0; index < testData.size(); index++ )
        {
            View view = getViewById( index );
            TextView locationName = (TextView) view.findViewById( R.id.location_name );
            assertViewIsVisible( locationName );
            assertThat( locationName.getText().toString(),
                        equalTo( testData.get( index ).getName() ) );
            assertViewIsVisible( view.findViewById( R.id.location_image ) );
        }
    }

    @Test
    public void getView_shouldHaveLoveButton() throws Exception
    {
        for ( int index = 0; index < testData.size(); index++ )
        {
            View view = getViewById( index );
            ImageView lovedButton = (ImageView) view.findViewById( R.id.loved_button );
            assertViewIsVisible( lovedButton );
            Location currentLocation = testData.get( index );
            if ( currentLocation.isLoved() )
            {
                assertThat( lovedButton.getDrawable(),
                            equalTo( getDrawable( R.drawable.love ) ) );
            }
            else
            {
                assertThat( lovedButton.getDrawable(),
                            equalTo( getDrawable( R.drawable.no_love ) ) );
            }
        }
    }

    @Test
    public void getView_loveButtonShouldSwapImages() throws Exception
    {
        for ( int index = 0; index < testData.size(); index++ )
        {
            View view = getViewById( index );
            ImageView lovedButton = (ImageView) view.findViewById( R.id.loved_button );
            Location currentLocation = testData.get( index );
            boolean originalLovedValue = currentLocation.isLoved();
            lovedButton.performClick();
            if ( originalLovedValue)
            {
                assertThat( lovedButton.getDrawable(),
                            equalTo( getDrawable( R.drawable.no_love ) ) );
            }
            else
            {
                assertThat( lovedButton.getDrawable(),
                            equalTo( getDrawable( R.drawable.love ) ) );
            }
        }
    }

    @Test
    public void getView_loveButtonShouldSwapLoveValue() throws Exception
    {
        for ( int index = 0; index < testData.size(); index++ )
        {
            View view = getViewById( index );
            ImageView lovedButton = (ImageView) view.findViewById( R.id.loved_button );
            Location currentLocation = testData.get( index );
            boolean originalLovedValue = currentLocation.isLoved();
            lovedButton.performClick();
            assertNotSame( originalLovedValue, currentLocation.isLoved() );
        }
    }

    private View getViewById( int index )
    {
        return locationListAdapter.getView( index,
                                            createRecycledView(),
                                            null );
    }

    @Test
    public void createHolder_shouldReturnViewHolder() throws Exception
    {
        LocationListAdapter.ViewHolder holder = LocationListAdapter.ViewHolder
                                                                   .createViewHolder( createRecycledView() );
        assertNotNull( holder );
    }

    private ArrayList<Location> generateTestData()
    {
        testData = new ArrayList<Location>();
        testData.add( new Location( "hello", "goodbye" ) );
        testData.add( new Location( "yo", "lo", true ) );

        return testData;
    }
}

