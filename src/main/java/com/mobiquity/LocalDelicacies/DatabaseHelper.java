package com.mobiquity.LocalDelicacies;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.mobiquity.LocalDelicacies.delicacies.Delicacy;
import com.mobiquity.LocalDelicacies.location.Location;

import java.util.ArrayList;

/**
 * Created by dalexander on 7/25/14.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "LocalDelicacies.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DataContract.SQL_CREATE_LOCATION_TABLE);
        db.execSQL(DataContract.SQL_CREATE_DELICACY_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(DataContract.SQL_DELETE_DELICACY_TABLE);
        db.execSQL(DataContract.SQL_DELETE_LOCATION_TABLE);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static Cursor getLocationCursor(Context context) {
        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();

        return getLocationCursor(db);
    }

    public static Cursor getLocationCursor(SQLiteDatabase db){
        String[] projection = {
                DataContract.LocationEntry._ID,
                DataContract.LocationEntry.COLUMN_NAME_NAME,
                DataContract.LocationEntry.COLUMN_NAME_DESCRIPTION,
                DataContract.LocationEntry.COLUMN_NAME_IMAGE_URL,
                DataContract.LocationEntry.COLUMN_NAME_BOOKMARKED
        };

        Cursor locCursor = db.query(
                DataContract.LocationEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        return locCursor;
    }

    public static Cursor getDelicacyCursor(Context context) {

        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();

        return getDelicacyCursor(db);
    }

    public static Cursor getDelicacyCursor(SQLiteDatabase db){

        String[] delicacyColumns = {
                DataContract.DelicacyEntry._ID,
                DataContract.DelicacyEntry.COLUMN_NAME_NAME,
                DataContract.DelicacyEntry.COLUMN_NAME_DESCRIPTION,
                DataContract.DelicacyEntry.COLUMN_NAME_IMAGE_URL,
                DataContract.DelicacyEntry.COLUMN_NAME_BOOKMARKED,
                DataContract.DelicacyEntry.COLUMN_NAME_CITY_ID,
                DataContract.DelicacyEntry.COLUMN_NAME_RATING
        };

        Cursor delCursor = db.query(
                DataContract.DelicacyEntry.TABLE_NAME,  // The table to query
                delicacyColumns,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        return delCursor;
    }

    public static ArrayList<Location> getLocations(Context context) {
        Cursor cursor = getLocationCursor(context);

        ArrayList<Location> data = new ArrayList<Location>();

        for(int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            Location loc = Location.loadFromCursor(cursor);

            data.add(loc);
        }

        return data;
    }

    public static ArrayList<Delicacy> getDelicacies(Context context) {
        Cursor cursor = getDelicacyCursor(context);

        ArrayList<Delicacy> data = new ArrayList<Delicacy>();

        for(int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            Delicacy del = Delicacy.loadFromCursor(cursor);

            data.add(del);
        }

        return data;
    }

}