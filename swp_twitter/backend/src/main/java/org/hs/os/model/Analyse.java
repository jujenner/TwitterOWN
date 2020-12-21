package org.hs.os.model;

import javax.persistence.*;

@Entity
@Table(name = "analyse_ergebnis")
public class Analyse {

    @Id
    @Column(name = "analyse_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "sentiment_typ")
    private SentimentTyp sentimentTyp;

    public Analyse() {
    }

    public Analyse(SentimentTyp sentimentTyp) {
        this.sentimentTyp = sentimentTyp;
    }

    public long getId() {
        return id;
    }

    public SentimentTyp getSentimentTyp() {
        return sentimentTyp;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSentimentTyp(SentimentTyp sentimentTyp) {
        this.sentimentTyp = sentimentTyp;
    }
}
