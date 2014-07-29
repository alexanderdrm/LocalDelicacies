package com.mobiquity.LocalDelicacies;

import android.database.Cursor;
import android.os.Bundle;

/**
 * Created by jwashington on 7/29/14.
 */
public class BaseModel {

    protected String title;
    protected String imageUrl;
    protected boolean bookmarked;
    protected String description;
    protected int id;

    public BaseModel(String title, String imageUrl, String description, boolean bookmarked, int id) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.bookmarked = bookmarked;
        this.description = description;
        this.id = id;
    }

    public BaseModel(Bundle bundle)
    {
        this(bundle.getString("title"),
                bundle.getString("imageUrl"),
                bundle.getString("description"),
                bundle.getBoolean("bookmarked"),
                bundle.getInt("id"));
    }

    public BaseModel(Cursor cursor)
    {
        title = cursor.getString(cursor.getColumnIndex(DataContract.LocationEntry.COLUMN_NAME_NAME));
        description = cursor.getString(cursor.getColumnIndex(DataContract.LocationEntry.COLUMN_NAME_DESCRIPTION));
        imageUrl = cursor.getString(cursor.getColumnIndex(DataContract.LocationEntry.COLUMN_NAME_IMAGE_URL));
        bookmarked = (cursor.getInt(cursor.getColumnIndex(DataContract.LocationEntry.COLUMN_NAME_BOOKMARKED)) == 1);
        id = cursor.getInt(cursor.getColumnIndex(DataContract.LocationEntry._ID));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bundle createBundleFromModel()
    {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("imageUrl", imageUrl);
        bundle.putBoolean("bookmarked", bookmarked);
        bundle.putString("description", description);
        bundle.putInt("id", id);
        return bundle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseModel)) return false;

        BaseModel baseModel = (BaseModel) o;

        if (bookmarked != baseModel.bookmarked) return false;
        if (description != null ? !description.equals(baseModel.description) : baseModel.description != null)
            return false;
        if (imageUrl != null ? !imageUrl.equals(baseModel.imageUrl) : baseModel.imageUrl != null) return false;
        if (!title.equals(baseModel.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (bookmarked ? 1 : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
