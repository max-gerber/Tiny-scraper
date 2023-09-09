package com.example;

import java.util.List;

import org.json.JSONArray;

public class MetadataWriter {

    public static void writeMetadata(List<JSONArray> pages) {
        JSONArray allMetadata = new JSONArray();
        for (JSONArray page : pages) {
            for (Object deckMetadata : page) {
                allMetadata.put(deckMetadata);
            }
        }
        Writer.writeJSONArrayToFile(allMetadata, "metadata.json");
    }
}
