package org.hs.os.dto;

import org.hs.os.model.Feed;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FeedDto {

    private final String keyword;
    private final int count;
    private long id;
    private List<TwitterStatusDto> twitterStatus;
    private long erstelltAm;
    private long suchDauer;
    private long suchIntervall;

    public FeedDto(String keyword, int count, long id, List<TwitterStatusDto> twitterStatus, long erstelltAm, long suchDauer, long suchIntervall) {
        this.keyword = keyword;
        this.count = count;
        this.id = id;
        this.twitterStatus = twitterStatus;
        this.erstelltAm = erstelltAm;
        this.suchDauer = suchDauer;
        this.suchIntervall = suchIntervall;
    }

    public FeedDto(Feed feed) {
        this.count = feed.getCount();
        this.keyword = feed.getKeyword();
        this.id = feed.getId();
        this.twitterStatus = feed.getTwitterStatus().stream().map(TwitterStatusDto::new).collect(Collectors.toList());
        this.erstelltAm = feed.getErstelltAm().getTime();
        this.suchDauer = feed.getSuchDauer().getTime();
        this.suchIntervall = feed.getSuchIntervall().getTime();
    }

    public String getKeyword() {
        return keyword;
    }

    public int getCount() {
        return count;
    }

    public long getId() {
        return id;
    }

    public List<TwitterStatusDto> getTwitterStatus() {
        return twitterStatus;
    }

    public long getErstelltAm() {
        return erstelltAm;
    }

    public long getSuchDauer() {
        return suchDauer;
    }

    public long getSuchIntervall() {
        return suchIntervall;
    }
}
