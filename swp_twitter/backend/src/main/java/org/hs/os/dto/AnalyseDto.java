package org.hs.os.dto;

import org.hs.os.model.Analyse;
import org.hs.os.model.SentimentTyp;

public class AnalyseDto {

    private final long id;
    private final SentimentTyp sentimentTyp;

    public AnalyseDto(long id, SentimentTyp sentimentTyp) {
        this.id = id;
        this.sentimentTyp = sentimentTyp;
    }

    public AnalyseDto(Analyse analyse) {
        this.id = analyse.getId();
        this.sentimentTyp = analyse.getSentimentTyp();
    }

    public long getId() {
        return id;
    }

    public SentimentTyp getSentimentTyp() {
        return sentimentTyp;
    }
}
