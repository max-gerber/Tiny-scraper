package com.example;

import java.util.*;

public class Main {

    static boolean writeMetadata = true;
    static boolean performScrape = true;
    static String commander;
    static String partner;

    public static void main(String[] args) {
        List<Deck> decks = new ArrayList<>();

        if (performScrape) {
            decks = MoxfieldApi.getDecksByFormat("tinyLeaders", writeMetadata); 
            DeckWriter.writeDecksToFile(decks, "decks.json");
        } else {
            decks = DeckReader.readDecksFromFile("decks.json");
        }

        Stats decksStats = new Stats(decks);
        StatisticsWriter.writeStatisticsToFiles(decksStats);
        Stats commanderStats = new Stats(decks, "Bruvac the Grandiloquent");
        StatisticsWriter.writeStatisticsToFiles(commanderStats);
        // commanderStats = new Stats(decks, "Jeska, Thrice Reborn", "Yoshimaru, Ever Faithful");
        // StatisticsWriter.writeStatisticsToFiles(commanderStats);
    }
}