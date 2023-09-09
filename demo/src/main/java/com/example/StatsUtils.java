package com.example;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatsUtils {


    public static boolean isDeckValid(Deck deck, String commander, String partner) {
        List<String> commanders = deck.getCommanders();
        return commanders.contains(commander) && ((partner != null && commanders.contains(partner)) || partner == null);
    }

    public static Map<String, Integer> sortMap(Map<String, Integer> unsortedMap) {
        if (unsortedMap == null){
            return null;
        }
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
