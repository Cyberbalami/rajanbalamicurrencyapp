package com.example.rajanbalamicurrencyapp.utilities;

import android.os.AsyncTask;

import com.example.rajanbalamicurrencyapp.MainActivity;
import com.example.rajanbalamicurrencyapp.parsers.JsonParser;

import java.util.ArrayList;

public class AsyncDataLoader extends AsyncTask<String, Void, ArrayList<String>> {

    private MainActivity activity;

    public AsyncDataLoader(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        try {
            String json = ApiDataReader.getValuesFromApi(params[0]);
            return JsonParser.parseRates(json);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    protected void onPostExecute(ArrayList<String> result) {
        activity.updateRates(result);
    }
}
