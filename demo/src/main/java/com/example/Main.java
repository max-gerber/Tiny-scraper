package com.example;

import java.util.*;

public class Main {

    static boolean writeMetadata = true;
    static boolean performScrape = true;
    static String commander = "Bruvac the Grandiloquent";
    static String partner = null; //set to null if there is no partner

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
        Stats commanderStats = new Stats(decks, commander, partner);
        StatisticsWriter.writeStatisticsToFiles(commanderStats);
    }
}