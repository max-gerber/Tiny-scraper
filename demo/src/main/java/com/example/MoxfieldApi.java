package com.example;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.*;

public class MoxfieldApi {

    static String url = "https://api2.moxfield.com/v2/decks";

    public static JSONObject getDecklist(String deckId) {

        String endpoint = "/all/" + deckId;

        return doRequest(endpoint);
    }

    public static List<Deck> getDecksByFormat(String format) {

        List<JSONArray> pages = new ArrayList<JSONArray>();

        int pageCount = 1;
        int totalPages = 1;
        JSONObject currentPage;

        while(pageCount <= totalPages) {
            String endpoint = "/search?pageNumber=" + pageCount +
                                "&pageSize=100&sortType=updated&sortDirection=Descending&fmt=" + format;
            
            currentPage = doRequest(endpoint);
            if (isValid(currentPage)) {
                totalPages = (Integer) currentPage.get("totalPages");
                pages.add((JSONArray) currentPage.get("data"));
                pageCount++;
            } else {
                break;
            }
        }
        return parsePages(pages);
    }

    private static boolean isValid(JSONObject currentPage) {
        return ((JSONArray)((JSONObject) currentPage).get("data")).length() > 0;
    }

    private static List<Deck> parsePages(List<JSONArray> pages) {
        List<Deck> decks = new ArrayList<Deck>();

        for (JSONArray page : pages) {
            for (Object deck : page) {
                if (isLegal(deck)) {
                    decks.add(new Deck((JSONObject) deck));
                }
            }
        }

        return decks;
    }

    private static boolean isLegal(Object deck) {
        return (Boolean) ((JSONObject) deck).get("isLegal") &&
        (Integer) ((JSONObject) deck).get("mainboardCount") == 50 &&
        (Integer) ((JSONObject) deck).get("sideboardCount") <= 10;
    }

    private static JSONObject doRequest(String endpoint) {
        try {
            return Unirest.get(url + endpoint).asJson().getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }

        System.err.println("Something went wrong performing the request on this endpoint:\n" + endpoint);

        return new JSONObject();
    }
}
