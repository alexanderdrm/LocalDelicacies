package com.mobiquity.LocalDelicacies.location;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.mobiquity.LocalDelicacies.DataContract;
import com.mobiquity.LocalDelicacies.delicacies.Delicacy;
import com.mobiquity.LocalDelicacies.delicacies.Specality;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * @since 1.0
 */
public class Location
{
    private String title;
    private String imageUrl;
    private boolean bookmarked;
    private String description;
    private ArrayList<Delicacy> delicacies;

    private int id;

    public Location(LocationData ld) {
        ArrayList<Delicacy> delicacies = new ArrayList<Delicacy>();
        for(Specality specality : ld.getSpecalities())
        {
            delicacies.add(new Delicacy(specality));
        }

        this.title = ld.getTitle();
        this.imageUrl = ld.getImageUrl();
        this.bookmarked = false;
        this.description = ld.getDescription();
        this.id = -1;
        this.delicacies = delicacies;
    }
    public Location( String title, String imageUrl)
    {
        this(title, imageUrl, "",  false, -1, new ArrayList<Delicacy>());
    }

    public Location(String title, String imageUrl, String description, boolean bookmarked, int id, ArrayList<Delicacy> delicacies)
    {
        this.title = title;
        this.imageUrl = imageUrl;
        this.bookmarked = bookmarked;
        this.description = description;
        this.id = id;
        this.delicacies = delicacies;
    }

    public String getTitle()
    {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Delicacy> getDelicacies() {
        return delicacies;
    }

    public void setDelicacies(ArrayList<Delicacy> delicacies) {
        this.delicacies = delicacies;
    }

    public static Bundle createBundleFromLocation(Location location)
    {
        Bundle bundle = new Bundle();
        bundle.putString("title", location.title);
        bundle.putString("imageUrl", location.imageUrl);
        bundle.putBoolean("bookmarked", location.bookmarked);
        bundle.putString("description", location.description);
        bundle.putInt("id", location.id);
        bundle.putString("delicacies", new Gson().toJson(location.delicacies));
        return bundle;
    }

    public static Location createLocationFromBundle(Bundle bundle)
    {
        Delicacy[] delicacyList = new Gson().fromJson(bundle.getString("delicacies", ""), Delicacy[].class);
        return new Location(
                bundle.getString("title"),
                bundle.getString("imageUrl"),
                bundle.getString("description"),
                bundle.getBoolean("bookmarked"),
                bundle.getInt("id"),
                new ArrayList<Delicacy>(Arrays.asList(delicacyList))
        );
    }

    //TODO: implement this
    public static Location loadFromCursor(Cursor cursor) {

        String name = cursor.getString(cursor.getColumnIndex(DataContract.LocationEntry.COLUMN_NAME_NAME));
        String desc = cursor.getString(cursor.getColumnIndex(DataContract.LocationEntry.COLUMN_NAME_DESCRIPTION));
        String url = cursor.getString(cursor.getColumnIndex(DataContract.LocationEntry.COLUMN_NAME_IMAGE_URL));
        boolean bookmarked = (cursor.getInt(cursor.getColumnIndex(DataContract.LocationEntry.COLUMN_NAME_BOOKMARKED)) == 1);
        int id = cursor.getInt(cursor.getColumnIndex(DataContract.LocationEntry._ID));

        Location l = new Location(name, url, desc, bookmarked, id, new ArrayList<Delicacy>());

        return l;
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

        db.insertWithOnConflict(DataContract.LocationEntry.TABLE_NAME, "null", values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (bookmarked != location.bookmarked) return false;
        if (imageUrl != null ? !imageUrl.equals(location.imageUrl) : location.imageUrl != null) return false;
        if (!title.equals(location.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (bookmarked ? 1 : 0);
        return result;
    }
}
