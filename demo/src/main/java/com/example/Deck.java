package com.example;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Deck {
    List<String> commanders;
    boolean hasPartner;
    String companion;
    List<String> colourIdentity;
    Map<String, Float> colourPercentages;
    List<String> mainboard;
    List<String> sideboard;

    public Deck(JSONObject metaData) {
        JSONObject deckInfo = MoxfieldApi.getDecklist((String) metaData.get("publicId"));
        commanders = extractCommanders((JSONObject) deckInfo.get("commanders"));
        hasPartner = commanders.size() == 2;
        if (hasCompanions(deckInfo)) {
            companion = extractCompanion((JSONObject) deckInfo.get("companions"));
        }
        colourIdentity = extractColourIdentity((JSONArray) (metaData.get("colorIdentity")));
        colourPercentages = extractColourPercentages((JSONObject) metaData.get("colorPercentages"));
        mainboard = extractCards((JSONObject) deckInfo.get("mainboard"));
        sideboard = extractCards((JSONObject) deckInfo.get("sideboard"));
    }

    private boolean hasCompanions(JSONObject deckInfo) {
        return (Integer) deckInfo.get("companionsCount") != 0;
    }

    private List<String> extractCards(JSONObject cardsJson) {
        List<String> cards = new ArrayList<>();
        Iterator<String> keys = cardsJson.keys();
        while(keys.hasNext()) {
            cards.add(keys.next());
        }
        return cards;
    }

    private Map<String, Float> extractColourPercentages(JSONObject colourPercentagesJson) {
        Map<String, Float> colourPercentages = new HashMap<>();
        Iterator<String> keys = colourPercentagesJson.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            Float value = Float.parseFloat(colourPercentagesJson.get(key).toString());
            colourPercentages.put(key, value);
        }
        return colourPercentages;
    }

    private String extractCompanion(JSONObject companions) {
        return (String) (companions.keySet()).toArray()[0];
    }

    private List<String> extractCommanders(JSONObject commanders) {
        return new ArrayList<String>(commanders.keySet());
    }

    private List<String> extractColourIdentity(JSONArray jsonColourIdentity) {
        List<String> colourIdentity = new ArrayList<String>();

        for (Object colour : jsonColourIdentity) {
            colourIdentity.add((String) colour);
        }

        return colourIdentity;
    }
}
