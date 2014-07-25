package com.mobiquity.LocalDelicacies;

import com.mobiquity.LocalDelicacies.delicacies.Delicacy;
import com.mobiquity.LocalDelicacies.location.Location;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/23/14.
 */
public class TestModule {

    public static ArrayList<Location> generateTestLocations()
    {
        ArrayList<Location> list = new ArrayList<Location>();
        list.add( new Location( "Las Vegas", null, "default" , true, -1) );
        list.add( new Location( "Nevada", null,"default" , false, -1 ) );
        list.add( new Location( "Philly", null,"default" , true, -1 ) );
        list.add( new Location( "Gainseville","default" , null, false, -1 ) );
        list.add( new Location( "Tallahassee", null,"default" , true, -1 ) );
        list.add( new Location( "Amsterdam", null,"default" , false, -1 ) );
        list.add( new Location( "Oaxaca", null,"default" , true, -1 ) );
        list.add( new Location( "Portland", null,"default" , false, -1 ) );
        list.add( new Location( "London", null,"default" , true, -1 ) );
        return list;
    }

    public static ArrayList<Delicacy> generateTestDelicacies()
    {
        ArrayList<Delicacy> list = new ArrayList<Delicacy>();
        list.add(new Delicacy("Apple", null, false));
        list.add(new Delicacy("Chocolate", null, true));
        return  list;
    }


}
