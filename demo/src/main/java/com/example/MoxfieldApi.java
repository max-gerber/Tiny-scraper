package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class MoxfieldApi {

    public static JSONObject getDecklist(String deckId) {
        String endpoint = "/all/" + deckId;
        return MoxfieldApiUtils.doRequest(endpoint);
    }

    public static List<Deck> getDecksByFormat(String format, boolean writeMetadata) {
        List<JSONArray> pageData = MoxfieldApiUtils.getAllPageData(format);
        if (writeMetadata) {
            MetadataWriter.writeMetadata(pageData);
        }
        return MoxfieldApiUtils.parsePageData(pageData);
    }
}
