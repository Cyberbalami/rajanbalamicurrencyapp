package com.example.rajanbalamicurrencyapp.parsers;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class JsonParser {

    public static ArrayList<String> parseRates(String jsonString) throws Exception {
        ArrayList<String> list = new ArrayList<>();

        JSONObject root = new JSONObject(jsonString);
        JSONObject rates = root.getJSONObject("rates");

        Iterator<String> keys = rates.keys();

        while (keys.hasNext()) {
            String currency = keys.next();
            double value = rates.getDouble(currency);

            list.add(currency + " – " + value);
        }

        return list;
    }
}
