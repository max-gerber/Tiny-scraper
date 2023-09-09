package com.example;

import java.util.*;

public class Stats {
    private String commanders;
    private Map<String, Integer> colourCount;
    private Map<String, Integer> commanderCount;
    private Map<String, Integer> companionCount;
    private Map<String, Integer> mainboardCount;
    private Map<String, Integer> sideboardCount;
    private Map<String, Integer> partnerPairCount;

    public Stats (List<Deck> decks) {
        colourCount = new HashMap<>();
        commanderCount = new HashMap<>();
        companionCount = new HashMap<>();
        mainboardCount = new HashMap<>();
        sideboardCount = new HashMap<>();
        partnerPairCount = new HashMap<>();
        populateStatistics(decks);
        sortStatistics();
    }

    public Stats (List<Deck> decks, String commander) {
        mainboardCount = new HashMap<>();
        sideboardCount = new HashMap<>();
        populateStatistics(decks, commander, null);
        sortStatistics();
    }

    public Stats (List<Deck> decks, String commander, String partner) {
        mainboardCount = new HashMap<>();
        sideboardCount = new HashMap<>();
        populateStatistics(decks, commander, partner);
        sortStatistics();
    }

    private void populateStatistics(List<Deck> decks) {
        for (Deck deck : decks) {
            if (deck.hasPartner()) {
                String partnerPair = deck.getCommanders().get(0) + " + " + deck.getCommanders().get(1);
                partnerPairCount.put(partnerPair, partnerPairCount.getOrDefault(partnerPair, 0) + 1);
            } else {
                for (String card : deck.getCommanders()) {
                    commanderCount.put(card, commanderCount.getOrDefault(card, 0) + 1);
                }
            }
            String companion = deck.getCompanion();
            if (companion != null) {
                companionCount.put(companion, companionCount.getOrDefault(companion, 0) + 1);
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
    }

    private void sortStatistics() {
        colourCount = StatsUtils.sortMap(colourCount);
        commanderCount = StatsUtils.sortMap(commanderCount);
        companionCount = StatsUtils.sortMap(companionCount);
        mainboardCount = StatsUtils.sortMap(mainboardCount);
        sideboardCount = StatsUtils.sortMap(sideboardCount);
        partnerPairCount = StatsUtils.sortMap(partnerPairCount);
    }

    private void populateStatistics(List<Deck> decks, String commander, String partner) {
        if (partner != null) {
            commanders = commander + " + " + partner;
        } else {
            commanders = commander;
        }
        for (Deck deck : decks) {
            if (StatsUtils.isDeckValid(deck, commander, partner)){
                for (String card : deck.getMainboard()) {
                    mainboardCount.put(card, mainboardCount.getOrDefault(card, 0) + 1);
                }
                for (String card : deck.getSideboard()) {
                    sideboardCount.put(card, sideboardCount.getOrDefault(card, 0) + 1);
                }
            }
        }
    }

    public String getCommanders() {
        return commanders;
    }

    public Map<String, Integer> getCommanderCount() {
        return commanderCount;
    }

    public Map<String, Integer> getPartnerPairCount() {
        return partnerPairCount;
    }

    public Map<String, Integer> getCompanionCount() {
        return companionCount;
    }

    public Map<String, Integer> getMainboardCount() {
        return mainboardCount;
    }

    public Map<String, Integer> getSideboardCount() {
        return sideboardCount;
    }

    public Map<String, Integer> getColourCount() {
        return colourCount;
    }
}
