package org.hs.os.service;

import org.hs.os.config.TwitterConfig;
import org.hs.os.model.Analyse;
import org.hs.os.model.SentimentTyp;
import org.hs.os.model.TwitterStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TwitterService {

    private final TwitterConfig twitterConfig;

    @Autowired
    public TwitterService(TwitterConfig twitterConfig) {
        this.twitterConfig = twitterConfig;
    }

    public List<TwitterStatus> fetchTweets(String keyword, int count) throws TwitterException {
        Query query = new Query(keyword.concat(" -filter:retweets -filter:replies"));
        query.setCount(count);
        query.setLocale("de");
        query.setLang("de");
        QueryResult result = twitterConfig.getTwitter().search(query);
        List<Status> tweets = result.getTweets();
        return tweets.stream()
                .map(item -> new TwitterStatus(item.getText().trim(), item.getUser().getName(), new Analyse(SentimentTyp.NEUTRAL, keyword))
                ).collect(Collectors.toList());

    }
}
