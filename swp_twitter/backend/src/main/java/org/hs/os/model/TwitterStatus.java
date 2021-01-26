package org.hs.os.model;

import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "twitter_status")
public class TwitterStatus {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "keyword")
    private String suchWort;

    @Column(name = "tweet")
    private long tweetId;

    @Column(name = "nutzer_name")
    private String nutzerNamen;

    @Column(name = "erstellt_am")
    private final Date erstelltAm = new Date();

    @JoinColumn(name = "ergebnis")
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Analyse ergebnis;

    public TwitterStatus() {
    }

    public TwitterStatus(String suchWort, String nutzerNamen, Analyse ergebnis, long tweetId) {
        this.suchWort = suchWort;
        this.nutzerNamen = nutzerNamen;
        this.ergebnis = ergebnis;
        this.tweetId = tweetId;
    }

    public long getId() {
        return id;
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

    public String getSuchWort() {
        return suchWort;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSuchWort(String suchWort) {
        this.suchWort = suchWort;
    }

    public long getTweetId() {
        return tweetId;
    }

    public void setTweetId(long tweetId) {
        this.tweetId = tweetId;
    }

    public void setNutzerNamen(String nutzerNamen) {
        this.nutzerNamen = nutzerNamen;
    }

    public void setErgebnis(Analyse ergebnis) {
        this.ergebnis = ergebnis;
    }
}
