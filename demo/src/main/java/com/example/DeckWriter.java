package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class DeckWriter {
    public static void writeDecksToFile(List<Deck> decks, String filename) {
        JSONArray decksJson = new JSONArray();

        for (Deck deck : decks) {
            decksJson.put(convertDeckToJSON(deck));
        }
        
        Writer.writeToFile(decksJson, filename);
    }

    private static JSONObject convertDeckToJSON(Deck deck) {
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
}