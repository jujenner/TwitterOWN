package org.hs.os.controller;

import org.hs.os.dto.FeedDto;
import org.hs.os.dto.TwitterStatusDto;
import org.hs.os.model.Feed;
import org.hs.os.repositories.FeedRepository;
import org.hs.os.service.FeedService;
import org.hs.os.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import twitter4j.TwitterException;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TwitterController {

    private final TwitterService twitterService;
    private final FeedService feedService;
    private final FeedRepository feedRepository;

    @Autowired
    public TwitterController(TwitterService twitterService, FeedService feedService, FeedRepository feedRepository) {
        this.twitterService = twitterService;
        this.feedService = feedService;
        this.feedRepository = feedRepository;
    }

    //Keyword: Suchbegriff
    //Count: Anzahl der zu ladenden Seiten (Menge der gesuchten Tweets)
    //GetMappoing: Erreichbarkeit der URL mit Strings
    @GetMapping(path = "search/{keyword}/{count}")
    public List<TwitterStatusDto> fetch(@PathVariable String keyword, @PathVariable int count) throws TwitterException {
        //FetchTweets gibt Parameter an Query in Twitter Service weiter
        return twitterService.fetchTweets(keyword, count).stream().map(TwitterStatusDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "feeds")
    public List<FeedDto> getFeeds() {
        return feedRepository.findAll().stream().map(FeedDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "feed/{id}")
    public FeedDto getFeed(@PathVariable Long id) {
        Feed feed = feedRepository.getOne(id);
        return new FeedDto(feed);
    }

    @PostMapping(path = "feed/{keyword}/{count}/{period}")
    public Feed createFeed(@PathVariable String keyword, @PathVariable int count, @PathVariable int period) {
        return feedService.createFeed(keyword, count, period, null);
    }

    @PostMapping(path = "feed/{keyword}/{count}/{period}/{end}")
    public Feed createFeedWithEnd(@PathVariable String keyword, @PathVariable int count, @PathVariable int period, @PathVariable long end) {
        return feedService.createFeed(keyword, count, period, new Date(end));
    }

    @DeleteMapping(path = "feed/{id}")
    public void deleteFeed(@PathVariable long id) {
        feedService.stopFeed(id);
        feedService.deleteFeed(id);
    }
}
