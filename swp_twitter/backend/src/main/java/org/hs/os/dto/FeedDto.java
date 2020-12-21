package org.hs.os.dto;

import org.hs.os.model.Feed;

import java.util.List;
import java.util.stream.Collectors;

public class FeedDto {

    private final String keyword;
    private final int count;
    private long id;
    private List<TwitterStatusDto> twitterStatus;

    public FeedDto(String keyword, int count, long id, List<TwitterStatusDto> twitterStatus) {
        this.keyword = keyword;
        this.count = count;
        this.id = id;
        this.twitterStatus = twitterStatus;
    }

    public FeedDto(Feed feed) {
        this.count = feed.getCount();
        this.keyword = feed.getKeyword();
        this.id = feed.getId();
        this.twitterStatus = feed.getTwitterStatus().stream().map(TwitterStatusDto::new).collect(Collectors.toList());
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
}
