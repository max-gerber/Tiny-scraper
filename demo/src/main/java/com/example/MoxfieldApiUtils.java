package com.example;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class MoxfieldApiUtils {

    static String baseUrl = "https://api2.moxfield.com/v2/decks";

    public static List<JSONArray> getAllPageData(String format) {
        List<JSONArray> pages = new ArrayList<JSONArray>();

        int pageCount = 1;
        int totalPages = 1;
        JSONObject currentPage;

        System.out.println("Scraping page metadata...");

        while(pageCount <= totalPages) {
            String endpoint = "/search?pageNumber=" + pageCount + "&pageSize=100&sortType=updated&sortDirection=Descending&fmt=" + format;
            currentPage = doRequest(endpoint);
            System.out.println("Scraped page " + pageCount + " out of " + totalPages);

            if (isPageValid(currentPage)) {
                totalPages = (Integer) currentPage.get("totalPages");
                pages.add((JSONArray) currentPage.get("data"));
                pageCount++;
            } else {
                break;
            }
        }

        return pages;
    }

    public static List<Deck> parsePageData(List<JSONArray> pages) {
        List<Deck> decks = new ArrayList<Deck>();
        for (JSONArray page : pages) {
            System.out.println("Adding decks (~100) from Page " + pages.indexOf(page) + " of " + pages.size());
            for (Object deck : page) {
                if (isDeckLegal(deck)) {
                    decks.add(new Deck((JSONObject) deck));
                }
            }
        }
        return decks;
    }

    public static JSONObject doRequest(String endpoint) {
        try {
            return Unirest.get(baseUrl + endpoint).asJson().getBody().getObject();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        System.err.println("Something went wrong performing the request on this endpoint:\n" + endpoint);
        return new JSONObject();
    }

    private static boolean isPageValid(JSONObject currentPage) {
        return ((JSONArray)((JSONObject) currentPage).get("data")).length() > 0;
    }

    private static boolean isDeckLegal(Object deck) {
        return (Boolean) ((JSONObject) deck).get("isLegal") &&
        (Integer) ((JSONObject) deck).get("mainboardCount") == 50 &&
        (Integer) ((JSONObject) deck).get("sideboardCount") <= 10
        ;
    }
}
