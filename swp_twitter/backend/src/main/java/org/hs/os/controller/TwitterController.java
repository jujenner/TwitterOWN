package org.hs.os.controller;

import org.hs.os.model.TwitterStatus;
import org.hs.os.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import twitter4j.TwitterException;

import java.util.List;

@RestController
public class TwitterController {

    private TwitterService twitterService;

    @Autowired
    public TwitterController(TwitterService twitterService) {
        this.twitterService = twitterService;
    }

    //Keyword: Suchbegriff
    //Count: Anzahl der zu ladenden Seiten (Menge der gesuchten Tweets)
    //GetMappoing: Erreichbarkeit der URL mit Strings
    @GetMapping(path = "search/{keyword}/{count}")
    public List<TwitterStatus> fetch(@PathVariable String keyword, @PathVariable int count) throws TwitterException {
        //FetchTweets gibt Parameter an Query in Twitter Service weiter
        return twitterService.fetchTweets(keyword, count);
    }
}
