package com.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Deck> decks = MoxfieldApi.getDecksByFormat("tinyLeaders");
        DeckWriter.writeDecksToFile(decks, "decks.json");
    }
}