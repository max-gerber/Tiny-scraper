package com.example;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticsWriter {
    static String commanderCountFile = "commanderCount.json";
    static String mainboardCountFile = "mainboardCount.json";
    static String sideboardCountFile = "sideboardCount.json";
    static String colourCountFile = "colourCount.json";

    public static void writeStatisticsToFiles(List<Deck> decks) {
        Map<String, Integer> commanderCount = new HashMap<>();
        Map<String, Integer> mainboardCount = new HashMap<>();
        Map<String, Integer> sideboardCount = new HashMap<>();
        Map<String, Integer> colourCount = new HashMap<>();

        for (Deck deck : decks) {
            for (String card : deck.getCommanders()) {
                commanderCount.put(card, commanderCount.getOrDefault(card, 0) + 1);
            }
            for (String card : deck.getMainboard()) {
                mainboardCount.put(card, mainboardCount.getOrDefault(card, 0) + 1);
            }
            for (String card : deck.getSideboard()) {
                sideboardCount.put(card, sideboardCount.getOrDefault(card, 0) + 1);
            }
            for (String colour: deck.getColourIdentity()) {
                colourCount.put(colour, colourCount.getOrDefault(colour, 0) + 1);
            }
        }

        colourCount = sortMap(colourCount);
        commanderCount = sortMap(commanderCount);
        mainboardCount = sortMap(mainboardCount);
        sideboardCount = sortMap(sideboardCount);
        
        Writer.writeToFile(colourCount, colourCountFile);
        Writer.writeToFile(commanderCount, commanderCountFile);
        Writer.writeToFile(mainboardCount, mainboardCountFile);
        Writer.writeToFile(sideboardCount, sideboardCountFile);
    }

    private static Map<String, Integer> sortMap(Map<String, Integer> unsortedMap) {
        return unsortedMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // Merge function (if duplicate keys exist)
                        LinkedHashMap::new // Preserve insertion order
                ));
    }
}
