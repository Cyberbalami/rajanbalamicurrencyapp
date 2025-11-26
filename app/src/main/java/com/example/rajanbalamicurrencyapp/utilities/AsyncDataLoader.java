package com.example.rajanbalamicurrencyapp.utilities;

import android.os.AsyncTask;

import com.example.rajanbalamicurrencyapp.parsers.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class AsyncDataLoader extends AsyncTask<String, Void, Map<String, Double>> {

    public interface Listener {
        void onDataLoaded(Map<String, Double> data);
    }

    private Listener listener;

    public AsyncDataLoader(Listener listener) {
        this.listener = listener;
    }

    @Override
    protected Map<String, Double> doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );

            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            return JsonParser.parseRates(builder.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Map<String, Double> result) {
        if (listener != null && result != null) {
            listener.onDataLoaded(result);
        }
    }
}