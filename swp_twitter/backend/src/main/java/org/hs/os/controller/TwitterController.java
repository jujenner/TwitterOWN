package org.hs.os.controller;

import org.hs.os.model.Feed;
import org.hs.os.model.TwitterStatus;
import org.hs.os.service.FeedService;
import org.hs.os.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

import java.util.Date;
import java.util.List;

@RestController
public class TwitterController {

    private final TwitterService twitterService;
    private final FeedService feedService;

    @Autowired
    public TwitterController(TwitterService twitterService, FeedService feedService) {
        this.twitterService = twitterService;
        this.feedService = feedService;
    }

    //Keyword: Suchbegriff
    //Count: Anzahl der zu ladenden Seiten (Menge der gesuchten Tweets)
    //GetMappoing: Erreichbarkeit der URL mit Strings
    @GetMapping(path = "search/{keyword}/{count}")
    public List<TwitterStatus> fetch(@PathVariable String keyword, @PathVariable int count) throws TwitterException {
        //FetchTweets gibt Parameter an Query in Twitter Service weiter
        return twitterService.fetchTweets(keyword, count);
    }

    @PostMapping(path = "feed/{keyword}/{count}/{period}")
    public Feed createFeed(@PathVariable String keyword, @PathVariable int count, @PathVariable int period) {
        return feedService.createFeed(keyword, count, period, null);
    }

    @PostMapping(path = "feed/{keyword}/{count}/{period}/{end}")
    public Feed createFeedWithEnd(@PathVariable String keyword, @PathVariable int count, @PathVariable int period, @PathVariable long end) {
        return feedService.createFeed(keyword, count, period, new Date(end));
    }
}
