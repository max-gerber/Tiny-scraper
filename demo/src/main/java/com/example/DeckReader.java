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
                Deck deck = ReadWriteUtils.createDeckFromJson((JSONObject) deckObj);
                decks.add(deck);
            }

            System.out.println("Successfully read decks from " + filename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return decks;
    }
}