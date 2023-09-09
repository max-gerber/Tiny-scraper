package com.example;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class ReadWriteUtils {

    public static JSONObject convertDeckToJSON(Deck deck) {
        JSONObject deckObject = new JSONObject();

        deckObject.put("commanders", deck.getCommanders());
        deckObject.put("hasPartner", deck.hasPartner());
        deckObject.put("companion", deck.getCompanion());
        deckObject.put("colourIdentity", deck.getColourIdentity());
        deckObject.put("mainboard", deck.getMainboard());
        deckObject.put("sideboard", deck.getSideboard());
        
        JSONObject colorPercentages = new JSONObject();
        for (Map.Entry<String, Float> entry : deck.getColourPercentages().entrySet()) {
            colorPercentages.put(entry.getKey(), entry.getValue());
        }
        deckObject.put("colourPercentages", colorPercentages);

        return deckObject;
    }

    public static Deck createDeckFromJson(JSONObject deckJSON){
        List<String> commanders = jsonToList(deckJSON.getJSONArray("commanders"));
        boolean hasPartner = deckJSON.getBoolean("hasPartner");
        List<String> colourIdentity = jsonToList(deckJSON.getJSONArray("colourIdentity"));
        Map<String, Float> colorPercentages = jsonToMap(deckJSON.getJSONObject("colourPercentages"));
        List<String> mainboard = jsonToList(deckJSON.getJSONArray("mainboard"));
        List<String> sideboard = jsonToList(deckJSON.getJSONArray("sideboard"));
        String companion = deckJSON.optString("companion");

        return new Deck(commanders, hasPartner, colourIdentity, colorPercentages, mainboard, sideboard, companion);
    }

    private static List<String> jsonToList(JSONArray jsonArray) {
        List<String> list = new ArrayList<>();
        for (Object obj : jsonArray) {
            list.add(obj.toString());
        }
        return list;
    }

    private static Map<String, Float> jsonToMap(JSONObject jsonObject) {
        Map<String, Float> map = new HashMap<>();
        for (Object key : jsonObject.keySet()) {
            String keyStr = (String) key;
            Float value = Float.parseFloat(jsonObject.get(keyStr).toString());
            map.put(keyStr, value);
        }
        return map;
    }
}
