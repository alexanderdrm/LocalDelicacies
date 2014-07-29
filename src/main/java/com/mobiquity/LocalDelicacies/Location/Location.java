package com.mobiquity.LocalDelicacies.location;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.mobiquity.LocalDelicacies.BaseModel;
import com.mobiquity.LocalDelicacies.DataContract;
import com.mobiquity.LocalDelicacies.delicacies.Delicacy;
import com.mobiquity.LocalDelicacies.delicacies.Specality;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * @since 1.0
 */
public class Location extends BaseModel
{
    public Location(LocationData ld)
    {
        super(
                ld.getTitle(),
                ld.getImageUrl(),
                ld.getDescription(),
                false,
                -1
        );
    }

    public Location(Cursor cursor)
    {
        super(cursor);
    }

    public Location(Bundle bundle)
    {
        super(bundle);
    }

    public Location(String title, String imageUrl, String description, boolean bookmarked, int id)
    {
        super(title, imageUrl, description, bookmarked, id);
    }

    public void saveToDatabase(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        if(id >= 0) { //defaults to -1
            values.put(DataContract.LocationEntry._ID, id);
        }

        values.put(DataContract.LocationEntry.COLUMN_NAME_NAME, getTitle());
        values.put(DataContract.LocationEntry.COLUMN_NAME_DESCRIPTION, getDescription());
        values.put(DataContract.LocationEntry.COLUMN_NAME_IMAGE_URL, getImageUrl());
        values.put(DataContract.LocationEntry.COLUMN_NAME_BOOKMARKED, isBookmarked());

        long id = db.insertWithOnConflict(DataContract.LocationEntry.TABLE_NAME, "null", values, SQLiteDatabase.CONFLICT_REPLACE);

        if(id >= 0) {
            this.id = (int)id;
        }

    }

}
