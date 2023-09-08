package com.example;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Deck {
    private List<String> commanders;
    private boolean hasPartner;
    private List<String> colourIdentity;
    private Map<String, Float> colourPercentages;
    private List<String> mainboard;
    private List<String> sideboard;
    private String companion;

    public Deck(List<String> commanders, boolean hasPartner, List<String> colourIdentity,
                Map<String, Float> colourPercentages, List<String> mainboard, List<String> sideboard, String companion) {
        this.commanders = commanders;
        this.hasPartner = hasPartner;
        this.colourIdentity = colourIdentity;
        this.colourPercentages = colourPercentages;
        this.mainboard = mainboard;
        this.sideboard = sideboard;
        this.companion = companion;
    }

    public Deck(JSONObject metaData) {
        JSONObject deckInfo = MoxfieldApi.getDecklist((String) metaData.get("publicId"));

        commanders = extractCommanders((JSONObject) deckInfo.get("commanders"));
        hasPartner = commanders.size() == 2;
        colourIdentity = extractColourIdentity((JSONArray) (metaData.get("colorIdentity")));
        colourPercentages = extractColourPercentages((JSONObject) metaData.get("colorPercentages"));
        mainboard = extractCards((JSONObject) deckInfo.get("mainboard"));
        sideboard = extractCards((JSONObject) deckInfo.get("sideboard"));
        if (hasCompanions(deckInfo)) {
            companion = extractCompanion((JSONObject) deckInfo.get("companions"));
        }
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

    public List<String> getCommanders() {
        return commanders;
    }

    public boolean hasPartner() {
        return hasPartner;
    }

    public String getCompanion() {
        return companion;
    }

    public List<String> getColourIdentity() {
        return colourIdentity;
    }

    public Map<String, Float> getColourPercentages() {
        return colourPercentages;
    }

    public List<String> getMainboard() {
        return mainboard;
    }

    public List<String> getSideboard() {
        return sideboard;
    }
}
