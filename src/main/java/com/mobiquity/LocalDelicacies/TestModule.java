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
        list.add( new Location( "Las Vegas", null, "default" , true) );
        list.add( new Location( "Nevada", null,"default" , false ) );
        list.add( new Location( "Philly", null,"default" , true ) );
        list.add( new Location( "Gainseville","default" , null, false ) );
        list.add( new Location( "Tallahassee", null,"default" , true ) );
        list.add( new Location( "Amsterdam", null,"default" , false ) );
        list.add( new Location( "Oaxaca", null,"default" , true ) );
        list.add( new Location( "Portland", null,"default" , false ) );
        list.add( new Location( "London", null,"default" , true ) );
        return list;
    }


}
