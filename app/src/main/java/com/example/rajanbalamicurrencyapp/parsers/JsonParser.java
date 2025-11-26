package com.example.rajanbalamicurrencyapp.parsers;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonParser {

    public static Map<String, Double> parseRates(String jsonString) throws Exception {
        JSONObject root = new JSONObject(jsonString);
        JSONObject rates = root.getJSONObject("rates");

        Map<String, Double> map = new HashMap<>();

        Iterator<String> keys = rates.keys();
        while (keys.hasNext()) {
            String currency = keys.next();
            double value = rates.getDouble(currency);
            map.put(currency, value);
        }
        return map;
    }
}