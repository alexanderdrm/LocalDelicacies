package com.mobiquity.LocalDelicacies;

import com.mobiquity.LocalDelicacies.delicacies.Delicacy;
import com.mobiquity.LocalDelicacies.location.Location;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/23/14.
 */
public class TestModule {

    private static ArrayList<Location> locationList = new ArrayList<Location>();

    private static ArrayList<Delicacy> delicacyList = new ArrayList<Delicacy>();

    static {
        locationList.add( new Location( "Las Vegas", null, "default" , true, -1) );
        locationList.add( new Location( "Nevada", null,"default" , false, -2 ) );
        locationList.add( new Location( "Philly", null,"default" , true, -3 ) );
        locationList.add( new Location( "Gainseville","default" , null, false, -4 ) );
        locationList.add( new Location( "Tallahassee", null,"default" , true, -5 ) );
        locationList.add( new Location( "Amsterdam", null,"default" , false, -6 ) );
        locationList.add( new Location( "Oaxaca", null,"default" , true, -7 ) );
        locationList.add( new Location( "Portland", null,"default" , false, -8 ) );
        locationList.add( new Location( "London", null,"default" , true, -9 ) );

        //(String name, String imageUrl, boolean bookmarked, int ratingInHalfStars, String description, int id, Location city)

        delicacyList.add(new Delicacy("Apple", null, "it's an apple.", false, -1, 9, -2));
        delicacyList.add(new Delicacy("Chocolate", null, "it's chocolate.", true, -2, 7, -3));
    }

    public static ArrayList<Location> generateTestLocations()
    {
        return locationList;
    }

    public static ArrayList<Delicacy> generateTestDelicacies()
    {
        return  delicacyList;
    }


}
