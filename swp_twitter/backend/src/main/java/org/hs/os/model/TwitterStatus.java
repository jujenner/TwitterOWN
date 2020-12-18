package org.hs.os.model;

import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "twitter_status")
public class TwitterStatus {

    //Uhrzeit bestimmt ID
    @Id
    @Column(name = "id")
    private final long id = new Date().getTime();

    @Column(name = "tweet")
    private final String tweet;

    @Column(name = "nutzer_name")
    private final String nutzerNamen;

    @Column(name = "erstellt_am")
    private final Date erstelltAm = new Date();

    @Column(name = "ergebnis")
    private final Analyse ergebnis;

    public TwitterStatus(String tweet, String nutzerNamen, Analyse ergebnis) {
        this.tweet = tweet;
        this.nutzerNamen = nutzerNamen;
        this.ergebnis = ergebnis;
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

    public Analyse getErgebnis() {
        return ergebnis;
    }
}
