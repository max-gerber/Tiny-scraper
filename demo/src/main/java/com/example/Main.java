package com.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Deck> decks = new ArrayList<>();
        String deckFile = "decks.json";
        // decks = MoxfieldApi.getDecksByFormat("tinyLeaders");
        // DeckWriter.writeDecksToFile(decks, deckFile);
        decks = DeckReader.readDecksFromFile(deckFile);
        StatisticsWriter.writeStatisticsToFiles(decks);
    }
}