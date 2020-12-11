package org.hs.os.model;

import java.util.Date;
import java.util.UUID;

public class TwitterStatus {

    //Uhrzeit bestimmt ID
    private final long id = new Date().getTime();
    private final String tweet;
    private final String nutzerNamen;
    private final Date erstelltAm = new Date();
    private final Analyse ergebniss;

    public TwitterStatus(String tweet, String nutzerNamen, Analyse ergebniss) {
        this.tweet = tweet;
        this.nutzerNamen = nutzerNamen;
        this.ergebniss = ergebniss;
    }

    public long getId() {
        return id;
    }

    public String getTweet() {
        return tweet;
    }

    public String getNutzerNamen() {
        return nutzerNamen;
    }

    public Date getErstelltAm() {
        return erstelltAm;
    }

    public Analyse getErgebniss() {
        return ergebniss;
    }
}
