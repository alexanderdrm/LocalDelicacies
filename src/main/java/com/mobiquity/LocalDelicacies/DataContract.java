package com.mobiquity.LocalDelicacies;

import android.provider.BaseColumns;

/**
 * Created by dalexander on 7/25/14.
 */
public class DataContract {
    public static abstract class LocationEntry implements BaseColumns {
        public static final String TABLE_NAME = "locations";
        public static final String COLUMN_NAME_NAME = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_IMAGE_URL = "imageurl";
        public static final String COLUMN_NAME_BOOKMARKED = "bookmarked";
    }

    public static final String TEXT_TYPE = " TEXT";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + LocationEntry.TABLE_NAME + " (" +
                    LocationEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    LocationEntry.COLUMN_NAME_NAME + " TEXT," +
                    LocationEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    LocationEntry.COLUMN_NAME_IMAGE_URL + " TEXT," +
                    LocationEntry.COLUMN_NAME_BOOKMARKED + " INTEGER" +
            " )";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LocationEntry.TABLE_NAME;

}
