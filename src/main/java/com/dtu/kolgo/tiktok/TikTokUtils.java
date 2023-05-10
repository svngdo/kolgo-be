package com.dtu.kolgo.tiktok;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class TikTokUtils {

    public static String getFollowersCount(String profileUrl) {
        // URL of the TikTok user's profile page

        // Send a GET request to the profile page and parse the HTML using JSoup
        Document doc = null;
        try {
            doc = Jsoup.connect(profileUrl).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Extract the follower count from the HTML
        Elements followerCountElement = doc.select("strong[data-e2e=\"followers-count\"]");

        return followerCountElement.text();
    }
}
