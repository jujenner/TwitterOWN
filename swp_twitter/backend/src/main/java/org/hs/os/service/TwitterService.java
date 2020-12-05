package org.hs.os.service;

import org.hs.os.config.TwitterConfig;
import org.hs.os.model.Analyse;
import org.hs.os.model.SentimentTyp;
import org.hs.os.model.TwitterStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TwitterService {

    private final TwitterConfig twitterConfig;
    private final SentimentAnalyseService analyseService;

    @Autowired
    public TwitterService(TwitterConfig twitterConfig, SentimentAnalyseService analyseService) {
        this.twitterConfig = twitterConfig;
        this.analyseService = analyseService;
    }

    public List<TwitterStatus> fetchTweets(String keyword, int count) throws TwitterException {
        Query query = new Query(keyword.concat(" -filter:retweets -filter:replies"));
        query.setCount(count);
        query.setLocale("en");
        query.setLang("en");
        query.setResultType(Query.ResultType.recent);
        QueryResult result = twitterConfig.getTwitter().search(query);
        List<Status> tweets = result.getTweets();
        return tweets.stream()
                .map(item -> new TwitterStatus(item.getText().trim(), item.getUser().getName(), analyseTweet(item.getText(), keyword))
                ).collect(Collectors.toList());

    }

    private Analyse analyseTweet(String text, String keyword) {
        SentimentTyp result = analyseService.analyse(cleanTweet(text));
        return new Analyse(result, keyword);
    }

    private String cleanTweet(String tweet) {
        return tweet.trim()
                // remove links
                .replaceAll("http.*?[\\S]+", "")
                // remove hashtags
                .replaceAll("#", "")
                // correct all multiple white spaces to a single white space
                .replaceAll("[\\s]+", " ");
    }
}
