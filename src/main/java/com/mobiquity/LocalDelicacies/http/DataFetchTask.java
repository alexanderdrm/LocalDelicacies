package com.mobiquity.LocalDelicacies.http;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import com.mobiquity.LocalDelicacies.*;
import com.mobiquity.LocalDelicacies.delicacies.Delicacy;
import com.mobiquity.LocalDelicacies.delicacies.Specality;
import com.mobiquity.LocalDelicacies.location.Location;
import com.mobiquity.LocalDelicacies.location.LocationData;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dalexander on 7/24/14.
 */
public class DataFetchTask extends AsyncTask<String, Void, List<LocationData>>
{

    Context context;

    public DataFetchTask(Context context) {
        this.context = context;
    }

    @Override
    protected List<LocationData> doInBackground(String... params) {

        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            return null;
        }

        int len = 500;

        final String DEBUG_TAG = "Connection_attempt";

        try {
            URL url = new URL("http://107.170.189.86:8000/colabug/localfood.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);

            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while(reader.ready()) {
                sb.append(reader.readLine());
            }

            String result = sb.toString();
            return parseLocationJson(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

        return null;
    }

    @Override
    public void onPostExecute(List<LocationData> data) {

        ArrayList<Location> locations = new ArrayList<Location>();
        ArrayList<Delicacy> delicacies = new ArrayList<Delicacy>();

        HashMap<String, Location> locs = new HashMap<String, Location>();

        for(LocationData ld: data) {

            Location loc = new Location(ld);

            locations.add(loc);

            locs.put(loc.getTitle(), loc);

            for(Specality spec: ld.getSpecalities()) {
                spec.setCity(loc);
                delicacies.add(new Delicacy(spec));
            }

        }

        SQLiteDatabase db = new DatabaseHelper(context).getReadableDatabase();

        String[] projection = {
                DataContract.LocationEntry._ID,
                DataContract.LocationEntry.COLUMN_NAME_NAME,
                DataContract.LocationEntry.COLUMN_NAME_DESCRIPTION,
                DataContract.LocationEntry.COLUMN_NAME_IMAGE_URL,
                DataContract.LocationEntry.COLUMN_NAME_BOOKMARKED
        };

        Cursor c = db.query(
                DataContract.LocationEntry.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        ArrayList<Location> dbLocs = new ArrayList<Location>();
        for(int i = 0; i < c.getCount(); i++) {
            c.moveToPosition(i);
            Location dbLocation = Location.loadFromCursor(c);
            Location remoteLocation = locs.get(dbLocation.getTitle());
            if(remoteLocation != null) {
                remoteLocation.setBookmarked(dbLocation.isBookmarked());
                remoteLocation.setId(dbLocation.getId());
            } else {
                locs.put(dbLocation.getTitle(), dbLocation);
                locations.add(dbLocation);
            }
        }


        ApplicationBus.postEvent(new DataUpdateEvent(locations, delicacies));
    }

    protected List<LocationData> parseLocationJson(String result) throws IOException {
        // Convert the InputStream into a string
        Gson gson = new Gson();

        List<LocationData> locations = gson.fromJson(result, RemoteData.class).getLocations();
        return locations;
    }
}
