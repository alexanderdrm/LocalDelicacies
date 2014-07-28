package com.mobiquity.LocalDelicacies.delicacies;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.mobiquity.LocalDelicacies.DataContract;
import com.mobiquity.LocalDelicacies.location.Location;

/**
 * Created by jwashington on 7/22/14.
 */
public class Delicacy {
    private String name;
    private String imageUrl;
    private boolean bookmarked;

    private int ratingInHalfStars;

    private String description;

    private int id;
    private int cityid;

    private Location city = null; //this may become a many-to-many relationship at some point

    public Delicacy(Specality spec) {
        this(spec.getTitle(), spec.getImageUrl(), false, 0, spec.getDescription(), -1, spec.getCity());
    }

    public Delicacy(String name, String imageUrl, boolean bookmarked, int ratingInHalfStars, String description, int id, Location city)
    {
        this(name, imageUrl, bookmarked, ratingInHalfStars, description, id, city.getId());
        this.city = city;
    }

    public Delicacy(String name, String imageUrl, boolean bookmarked, int ratingInHalfStars, String description, int id, int cityid) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.bookmarked = bookmarked;
        this.ratingInHalfStars = ratingInHalfStars;
        this.description = description;
        this.id = id;
        this.cityid = cityid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public int getRatingInHalfStars()
    {
        return ratingInHalfStars;
    }

    public void setRatingInHalfStars(int ratingInHalfStars)
    {
        this.ratingInHalfStars = ratingInHalfStars;
    }

    //could move this and the reverse method from being static to just being members or a constructor
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static Bundle createBundleFromDelicacy(Delicacy delicacy)
    {
        Bundle bundle = new Bundle();
        bundle.putString("name", delicacy.name);
        bundle.putString("imageURL", delicacy.imageUrl);
        bundle.putBoolean("bookmarked", delicacy.bookmarked);
        bundle.putInt("rating", delicacy.ratingInHalfStars);
        bundle.putString("description", delicacy.description);
        bundle.putInt("id", delicacy.id);
        bundle.putInt("cityid", delicacy.id);
        return bundle;
    }

    public static Delicacy createDelicacyFromBundle(Bundle bundle)
    {
        return new Delicacy(
                bundle.getString("name"),
                bundle.getString("imageURL"),
                bundle.getBoolean("bookmarked"),
                bundle.getInt("rating"),
                bundle.getString("description"),
                bundle.getInt("id"),
                bundle.getInt("cityid")
        );
    }

    public static Delicacy loadFromCursor(Cursor cursor) {

        String name = cursor.getString(cursor.getColumnIndex(DataContract.DelicacyEntry.COLUMN_NAME_NAME));
        String desc = cursor.getString(cursor.getColumnIndex(DataContract.DelicacyEntry.COLUMN_NAME_DESCRIPTION));
        String url = cursor.getString(cursor.getColumnIndex(DataContract.DelicacyEntry.COLUMN_NAME_IMAGE_URL));
        boolean bookmarked = (cursor.getInt(cursor.getColumnIndex(DataContract.DelicacyEntry.COLUMN_NAME_BOOKMARKED)) == 1);
        int id = cursor.getInt(cursor.getColumnIndex(DataContract.DelicacyEntry._ID));
        int rating = cursor.getInt(cursor.getColumnIndex(DataContract.DelicacyEntry.COLUMN_NAME_RATING));
        int cityid = cursor.getInt(cursor.getColumnIndex(DataContract.DelicacyEntry.COLUMN_NAME_CITY_ID));

        Delicacy del = new Delicacy(name, url, bookmarked, rating, desc, id, cityid);

        return del;
    }

    public void saveToDatabase(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        if(id >= 0) { //defaults to -1
            values.put(DataContract.DelicacyEntry._ID, id);
        }

        values.put(DataContract.DelicacyEntry.COLUMN_NAME_NAME, name);
        values.put(DataContract.DelicacyEntry.COLUMN_NAME_DESCRIPTION, description);
        values.put(DataContract.DelicacyEntry.COLUMN_NAME_IMAGE_URL, getImageUrl());
        values.put(DataContract.DelicacyEntry.COLUMN_NAME_BOOKMARKED, isBookmarked());
        values.put(DataContract.DelicacyEntry.COLUMN_NAME_RATING, ratingInHalfStars);
        values.put(DataContract.DelicacyEntry.COLUMN_NAME_CITY_ID, cityid);

        long id = db.insertWithOnConflict(DataContract.DelicacyEntry.TABLE_NAME, "null", values, SQLiteDatabase.CONFLICT_REPLACE);

        if(id >= 0) {
            this.id = (int)id;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Delicacy delicacy = (Delicacy) o;

        if (bookmarked != delicacy.bookmarked) return false;
        if (ratingInHalfStars != delicacy.ratingInHalfStars) return false;
        if (imageUrl != null ? !imageUrl.equals(delicacy.imageUrl) : delicacy.imageUrl != null) return false;
        if (!name.equals(delicacy.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (bookmarked ? 1 : 0);
        result = 31 * result + ratingInHalfStars;
        return result;
    }
}
