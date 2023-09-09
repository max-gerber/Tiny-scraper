package com.example;

import org.json.JSONArray;

import java.util.*;

public class DeckWriter {
    public static void writeDecksToFile(List<Deck> decks, String filename) {
        JSONArray decksJson = new JSONArray();
        for (Deck deck : decks) {
            decksJson.put(ReadWriteUtils.convertDeckToJSON(deck));
        }
        Writer.writeJSONArrayToFile(decksJson, filename);
    }
}