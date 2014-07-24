package com.mobiquity.LocalDelicacies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import com.google.gson.Gson;
import com.mobiquity.LocalDelicacies.delicacies.Delicacy;
import com.mobiquity.LocalDelicacies.location.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
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

        Gson gson = new Gson();
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


            // Convert the InputStream into a string
            BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while(reader.ready()) {
                sb.append(reader.readLine());
            }

            String result = sb.toString();

            List<LocationData> locations = gson.fromJson(result, RemoteData.class).getLocations();

            return locations;
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

        for(LocationData ld: data) {
            locations.add(new Location(ld));

            for(Specality spec: ld.getSpecalities()) {
                delicacies.add(new Delicacy(spec));
            }

        }

        ApplicationBus.postEvent(new DataUpdateEvent(locations, delicacies));
    }
}
