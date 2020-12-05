package org.hs.os.model;

import java.util.Date;

public class Analyse {

    private final long id = new Date().getTime();
    private final SentimentTyp sentimentTyp;
    private final String suchWort;

    public Analyse(SentimentTyp sentimentTyp, String suchWort) {
        this.sentimentTyp = sentimentTyp;
        this.suchWort = suchWort;
    }

    public long getId() {
        return id;
    }

    public SentimentTyp getSentimentTyp() {
        return sentimentTyp;
    }

    public String getSuchWort() {
        return suchWort;
    }
}
