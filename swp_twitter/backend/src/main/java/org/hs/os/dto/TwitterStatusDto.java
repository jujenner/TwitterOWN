package org.hs.os.dto;

import org.hs.os.model.TwitterStatus;

import java.util.Date;

public class TwitterStatusDto {

    private long id;
    private final String suchWort;
    private final long tweetId;
    private final String nutzerNamen;
    private final Date erstelltAm;
    private final AnalyseDto ergebnis;

    public TwitterStatusDto(long id, String suchWort, long tweetId, String nutzerNamen, Date erstelltAm, AnalyseDto ergebnis) {
        this.id = id;
        this.suchWort = suchWort;
        this.tweetId = tweetId;
        this.nutzerNamen = nutzerNamen;
        this.erstelltAm = erstelltAm;
        this.ergebnis = ergebnis;
    }

    public TwitterStatusDto(TwitterStatus twitterStatus) {
        this.id = twitterStatus.getId();
        this.suchWort = twitterStatus.getSuchWort();
        this.tweetId = twitterStatus.getTweetId();
        this.nutzerNamen = twitterStatus.getNutzerNamen();
        this.erstelltAm = twitterStatus.getErstelltAm();
        this.ergebnis = new AnalyseDto(twitterStatus.getErgebnis());

    }

    public long getId() {
        return id;
    }

    public String getSuchWort() {
        return suchWort;
    }

    public long getTweetId() {
        return tweetId;
    }

    public String getNutzerNamen() {
        return nutzerNamen;
    }

    public Date getErstelltAm() {
        return erstelltAm;
    }

    public AnalyseDto getErgebnis() {
        return ergebnis;
    }
}
