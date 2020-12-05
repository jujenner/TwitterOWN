package org.hs.os.model;

public enum SentimentTyp {
    SEHR_POSITIV(0),
    POSITIV(1),
    NEUTRAL(2),
    NEGATIV(3),
    SEHR_NEGATIV(4);

    private int value;

    public int getValue() {
        return value;
    }

    SentimentTyp(int value) {
        this.value = value;
    }
}
