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

import java.util.Collections;
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
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "search/{keyword}/{count}")
    public List<TwitterStatusDto> fetch(@PathVariable String keyword, @PathVariable int count) throws TwitterException {
        //FetchTweets gibt Parameter an Query in Twitter Service weiter
        return twitterService.fetchTweets(keyword, count).stream().map(TwitterStatusDto::new).collect(Collectors.toList());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "feeds")
    public List<FeedDto> getFeeds() {
        return feedRepository.findAll().stream().map(FeedDto::new).collect(Collectors.toList());

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "feed/{id}")
    public FeedDto getFeed(@PathVariable Long id) {
        Feed feed = feedRepository.getOne(id);
        return new FeedDto(feed);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "feed/{keyword}/{count}/{period}")
    public Feed createFeed(@PathVariable String keyword, @PathVariable int count, @PathVariable long period) {
        return feedService.createFeed(keyword, count, new Date(period), null);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "feed/{keyword}/{count}/{period}/{end}")
    public Feed createFeedWithEnd(@PathVariable String keyword, @PathVariable int count, @PathVariable long period, @PathVariable long end) {
        return feedService.createFeed(keyword, count, new Date(period), new Date(end));
    }


    // Ausblick: evtl Feed loeschen
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping(path = "feed/{id}")
    public void deleteFeed(@PathVariable long id) {
        feedService.stopFeed(id);
        feedService.deleteFeed(id);
    }
}
