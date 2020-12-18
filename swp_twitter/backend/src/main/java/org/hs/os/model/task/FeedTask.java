package org.hs.os.model.task;

import org.hs.os.model.Feed;
import org.hs.os.model.TwitterStatus;
import org.hs.os.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import twitter4j.TwitterException;

import java.util.List;

public class FeedTask implements Runnable {

    @Autowired
    private TwitterService twitterService;
    private Feed feed;

    public FeedTask(Feed feed) {
        this.feed = feed;
    }


    // ????
    // Feeds in DB speichern?
    @Override
    public void run() {
        try {
            List<TwitterStatus> fetchTweets = twitterService.fetchTweets(feed.getKeyword(), feed.getCount());
            // todo store in database with the feed id
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        System.out.println(
                "Running task to find Feeds about - " + feed.getKeyword());
    }
}
