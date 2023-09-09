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

        try {
            commanders = DeckUtils.extractCommandersFromJson((JSONObject) deckInfo.get("commanders"));
            hasPartner = commanders.size() == 2;
            colourIdentity = DeckUtils.extractColourIdentityFromJson((JSONArray) (metaData.get("colorIdentity")));
            colourPercentages = DeckUtils.extractColourPercentagesFromJson((JSONObject) metaData.get("colorPercentages"));
            mainboard = DeckUtils.extractCardsFromJson((JSONObject) deckInfo.get("mainboard"));
            sideboard = DeckUtils.extractCardsFromJson((JSONObject) deckInfo.get("sideboard"));
            if (hasCompanions(deckInfo)) {
                companion = DeckUtils.extractCompanionFromJson((JSONObject) deckInfo.get("companions"));
            }
        } catch (Exception e) {
            System.err.println("There was an error adding a deck: "+ deckInfo.toString(2) +"\n\n" + e);
        }
    }

    private boolean hasCompanions(JSONObject deckInfo) {
        return (Integer) deckInfo.get("companionsCount") != 0;
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
