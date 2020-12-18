package org.hs.os.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "analyse_ergebnis")
public class Analyse {

    @Id
    @Column(name = "analyse_id")
    private final long id = new Date().getTime();

    @Column(name = "sentiment_typ")
    private final SentimentTyp sentimentTyp;

    @Column(name = "keyword")
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
