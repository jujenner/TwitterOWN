package org.hs.os.dto;

import org.hs.os.model.Analyse;

public class AnalyseDto {

    private final long id;
    private final int sentimentTyp;

    public AnalyseDto(long id, int sentimentTyp) {
        this.id = id;
        this.sentimentTyp = sentimentTyp;
    }

    public AnalyseDto(Analyse analyse) {
        this.id = analyse.getId();
        this.sentimentTyp = analyse.getSentimentTyp().getValue();
    }

    public long getId() {
        return id;
    }

    public int getSentimentTyp() {
        return sentimentTyp;
    }
}
