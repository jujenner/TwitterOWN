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

    //automatische Injektion der Dependencies
    @Autowired
    public TwitterService(TwitterConfig twitterConfig, SentimentAnalyseService analyseService) {
        this.twitterConfig = twitterConfig;
        this.analyseService = analyseService;
    }

    //Quelle: https://aboullaite.me/stanford-corenlp-java/
    //Keyword und Count werden aus Twitter Controller (also der URL) übergeben
    public List<TwitterStatus> fetchTweets(String keyword, int count) throws TwitterException {
        //Concat: Query zusammenfügen
        //Re-Tweets und Antworten herausfiltern
        Query query = new Query(keyword.concat(" -filter:retweets -filter:replies"));
        query.setCount(count);
        //Sprache der Query
        query.setLocale("en");
        //Sprache nach der gesucht wird
        query.setLang("en");
        //Suche nach den neusten Tweets
        query.setResultType(Query.ResultType.recent);
        //Zugangsdaten (getTwitter) holen und Übergabe der Query
        QueryResult result = twitterConfig.getTwitter().search(query);
        //Auslesen der Tweets
        List<Status> tweets = result.getTweets();

        //Durchiterieren der Tweets-Liste und Übergabe
        return tweets.stream()
                .map(item -> new TwitterStatus(keyword, item.getUser().getName(), analyseTweet(item.getText()), item.getId())
                ).collect(Collectors.toList());

    }


    //https://aboullaite.me/stanford-corenlp-java/
    //Bereinigung der Tweets
    private String cleanTweet(String tweet) {

        return tweet.trim()
                .replaceAll("http.*?[\\S]+", "")
                .replaceAll("#", "")
                .replaceAll("[\\s]+", " ");
    }

    private Analyse analyseTweet(String text) {
        SentimentTyp result = analyseService.analyse(cleanTweet(text));
        return new Analyse(result);
    }
}


