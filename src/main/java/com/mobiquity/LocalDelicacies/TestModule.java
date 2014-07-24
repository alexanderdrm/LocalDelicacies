package com.mobiquity.LocalDelicacies;

import com.mobiquity.LocalDelicacies.location.Location;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/23/14.
 */
public class TestModule {

    public static ArrayList<Location> generateTestLocations()
    {
        ArrayList<Location> list = new ArrayList<Location>();
        list.add( new Location( "Las Vegas", null, true ) );
        list.add( new Location( "Nevada", null, false ) );
        list.add( new Location( "Philly", null, true ) );
        list.add( new Location( "Gainseville", null, false ) );
        list.add( new Location( "Tallahassee", null, true ) );
        list.add( new Location( "Amsterdam", null, false ) );
        list.add( new Location( "Oaxaca", null, true ) );
        list.add( new Location( "Portland", null, false ) );
        list.add( new Location( "London", null, true ) );
        return list;
    }
}
