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
import com.mobiquity.LocalDelicacies.delicacies.DelicacyPagesFragment;
import com.mobiquity.LocalDelicacies.delicacies.Specality;
import com.mobiquity.LocalDelicacies.location.Location;
import com.mobiquity.LocalDelicacies.location.LocationData;
import javafx.util.Pair;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        HashMap<String, Location> locationMap = new HashMap<String, Location>();
        HashMap<String, Delicacy> delicacyMap = new HashMap<String, Delicacy>();

        for(LocationData ld: data) {

            Location loc = new Location(ld);

            locations.add(loc);

            locationMap.put(loc.getTitle(), loc);

            for(Specality spec: ld.getSpecalities()) {
                spec.setCity(loc);
                Delicacy del = new Delicacy(spec);
                delicacies.add(del);
                delicacyMap.put(del.getTitle(), del);
            }
        }

        SQLiteDatabase db = DatabaseHelper.getInstance(context).getReadableDatabase();

        Cursor locCursor = DatabaseHelper.getLocationCursor(context);

        for(int i = 0; i < locCursor.getCount(); i++) {
            locCursor.moveToPosition(i);
            Location dbLocation = new Location(locCursor);
            Location remoteLocation = locationMap.get(dbLocation.getTitle());
            if(remoteLocation != null) {
                remoteLocation.setBookmarked(dbLocation.isBookmarked());
                remoteLocation.setId(dbLocation.getId());
            } else {
                locationMap.put(dbLocation.getTitle(), dbLocation);
                locations.add(dbLocation);
            }
            //simultaneously keep track of locations we do not have locally
            //by removing visited ones from the map
            locationMap.remove(dbLocation.getTitle());
        }

        SQLiteDatabase wdb = DatabaseHelper.getInstance(context).getReadableDatabase();
        for(Map.Entry<String, Location> pair : locationMap.entrySet()) {
            pair.getValue().saveToDatabase(wdb);
        }

        Cursor delCursor = DatabaseHelper.getDelicacyCursor(context);

        for(int i = 0; i < delCursor.getCount(); i++) {
            delCursor.moveToPosition(i);
            Delicacy dbDelicacy = new Delicacy(delCursor);
            Delicacy remoteDelicacy = delicacyMap.get(dbDelicacy.getTitle());
            if(remoteDelicacy != null) {
                remoteDelicacy.setBookmarked(dbDelicacy.isBookmarked());
                remoteDelicacy.setId(dbDelicacy.getId());
                remoteDelicacy.setRatingInHalfStars(dbDelicacy.getRatingInHalfStars());
                remoteDelicacy.setCityid(remoteDelicacy.getCity().getId());

            } else {
                delicacyMap.put(dbDelicacy.getTitle(), dbDelicacy);
                dbDelicacy.setCityid(dbDelicacy.getCity().getId());
                delicacies.add(dbDelicacy);
            }
        }

        locCursor.close();
        delCursor.close();

        ApplicationBus.postEvent(new DataUpdateEvent(locations, delicacies));
    }

    protected List<LocationData> parseLocationJson(String result) throws IOException {
        // Convert the InputStream into a string
        Gson gson = new Gson();

        List<LocationData> locations = gson.fromJson(result, RemoteData.class).getLocations();
        return locations;
    }
}
