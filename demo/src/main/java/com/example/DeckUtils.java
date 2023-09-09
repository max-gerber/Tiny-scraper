package com.example;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class DeckUtils {

    public static List<String> extractCardsFromJson(JSONObject cardsJson) {
        List<String> cards = new ArrayList<>();
        Iterator<String> keys = cardsJson.keys();
        while(keys.hasNext()) {
            cards.add(keys.next());
        }
        return cards;
    }

    public static Map<String, Float> extractColourPercentagesFromJson(JSONObject colourPercentagesJson) {
        Map<String, Float> colourPercentages = new HashMap<>();
        Iterator<String> keys = colourPercentagesJson.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            Float value = Float.parseFloat(colourPercentagesJson.get(key).toString());
            colourPercentages.put(key, value);
        }
        return colourPercentages;
    }

    public static String extractCompanionFromJson(JSONObject companions) {
        return (String) (companions.keySet()).toArray()[0];
    }

    public static List<String> extractCommandersFromJson(JSONObject commanders) {
        return new ArrayList<String>(commanders.keySet());
    }

    public static List<String> extractColourIdentityFromJson(JSONArray jsonColourIdentity) {
        List<String> colourIdentity = new ArrayList<String>();
        for (Object colour : jsonColourIdentity) {
            colourIdentity.add((String) colour);
        }
        return colourIdentity;
    }
}
