package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DeckWriter {
    public static void writeDecksToFile(List<Deck> decks, String filename) {
        JSONArray deckList = new JSONArray();

        for (Deck deck : decks) {
            JSONObject deckObject = new JSONObject();

            deckObject.put("commanders", deck.commanders);
            deckObject.put("hasPartner", deck.hasPartner);
            deckObject.put("companion", deck.companion);
            deckObject.put("colourIdentity", deck.colourIdentity);
                
            JSONObject colorPercentages = new JSONObject();
            for (Map.Entry<String, Float> entry : deck.colourPercentages.entrySet()) {
                colorPercentages.put(entry.getKey(), entry.getValue());
            }
            deckObject.put("colourPercentages", colorPercentages);
            
            deckObject.put("mainboard", deck.mainboard);
            deckObject.put("sideboard", deck.sideboard);

            deckList.put(deckObject);
        }

        try (FileWriter file = new FileWriter(filename)) {
            file.write(deckList.toString());
            System.out.println("Successfully wrote decks to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}