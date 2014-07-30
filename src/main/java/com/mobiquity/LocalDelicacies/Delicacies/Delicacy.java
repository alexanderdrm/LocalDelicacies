package com.mobiquity.LocalDelicacies.delicacies;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.mobiquity.LocalDelicacies.BaseModel;
import com.mobiquity.LocalDelicacies.DataContract;
import com.mobiquity.LocalDelicacies.location.Location;

/**
 * Created by jwashington on 7/22/14.
 */
public class Delicacy extends BaseModel {

    private int ratingInHalfStars;
    private int cityid;
    private Location city = null;

    public Delicacy(Specality spec) {
        this(spec.getTitle(), spec.getImageUrl(), false, 0, spec.getDescription(), -1, spec.getCity());
    }

    public Delicacy(Bundle bundle)
    {
        super(bundle);
        ratingInHalfStars = bundle.getInt("rating");
        cityid = bundle.getInt("cityid");
    }

    public Delicacy(Cursor cursor) {
        super(cursor);
        ratingInHalfStars = cursor.getInt(cursor.getColumnIndex(DataContract.DelicacyEntry.COLUMN_NAME_RATING));
        cityid = cursor.getInt(cursor.getColumnIndex(DataContract.DelicacyEntry.COLUMN_NAME_CITY_ID));
    }

    public Delicacy(String name, String imageUrl, boolean bookmarked, int ratingInHalfStars, String description, int id, Location city)
    {
        this(name, imageUrl, description, bookmarked, id, ratingInHalfStars, city.getId());
        this.city = city;
    }

    public Delicacy(String title, String imageUrl, String description, boolean bookmarked, int id, int ratingInHalfStars, int cityid) {
        super(title, imageUrl, description, bookmarked, id );
        this.ratingInHalfStars = ratingInHalfStars;
        this.cityid = cityid;
    }

    public int getRatingInHalfStars()
    {
        return ratingInHalfStars;
    }

    public void setRatingInHalfStars(int ratingInHalfStars)
    {
        changed = true;
        this.ratingInHalfStars = ratingInHalfStars;
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        changed = true;
        this.cityid = cityid;
    }

    public Location getCity() {
        return city;
    }

    public void setCity(Location city) {
        this.city = city;
    }

    @Override
    public Bundle createBundleFromModel() {
        Bundle bundle = super.createBundleFromModel();
        bundle.putInt("rating", ratingInHalfStars);
        bundle.putInt("cityid", cityid);
        return bundle;
    }



    public void saveToDatabase(SQLiteDatabase db) {
        ContentValues values = new ContentValues();

        if(id >= 0) { //defaults to -1
            values.put(DataContract.DelicacyEntry._ID, id);
        }

        values.put(DataContract.DelicacyEntry.COLUMN_NAME_NAME, title);
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
        if (!(o instanceof Delicacy)) return false;
        if (!super.equals(o)) return false;

        Delicacy delicacy = (Delicacy) o;

        if (cityid != delicacy.cityid) return false;
        if (ratingInHalfStars != delicacy.ratingInHalfStars) return false;
        if (city != null ? !city.equals(delicacy.city) : delicacy.city != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + ratingInHalfStars;
        result = 31 * result + cityid;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
