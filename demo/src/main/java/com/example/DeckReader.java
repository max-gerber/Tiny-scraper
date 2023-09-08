package com.example;

import java.io.FileReader;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DeckReader {
    public static List<Deck> readDecksFromFile(String filename) {
        List<Deck> decks = new ArrayList<>();

        try (FileReader reader = new FileReader(filename)) {
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray deckArray = new JSONArray(tokener);

            for (Object deckObj : deckArray) {
                Deck deck = createDeckFromJson((JSONObject) deckObj);
                decks.add(deck);
            }

            System.out.println("Successfully read decks from " + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decks;
    }

    private static Deck createDeckFromJson(JSONObject deckJSON){

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