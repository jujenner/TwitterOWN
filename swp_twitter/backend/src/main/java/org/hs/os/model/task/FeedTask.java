package org.hs.os.model.task;

import org.hs.os.model.Feed;
import org.hs.os.model.TwitterStatus;
import org.hs.os.repositories.FeedRepository;
import org.hs.os.service.TwitterService;
import twitter4j.TwitterException;

import java.util.List;

public class FeedTask implements Runnable {

    private final TwitterService twitterService;
    private final FeedRepository feedRepository;
    private Feed feed;

    public FeedTask(TwitterService twitterService, FeedRepository feedRepository, Feed feed) {
        this.twitterService = twitterService;
        this.feedRepository = feedRepository;
        this.feed = feed;
    }


    // ????
    // Feeds in DB speichern?
    @Override
    public void run() {
        try {
            List<TwitterStatus> fetchTweets = twitterService.fetchTweets(feed.getKeyword(), feed.getCount());
            List<TwitterStatus> twitterStatus = feed.getTwitterStatus();
            if (twitterStatus.isEmpty()){
                twitterStatus = fetchTweets;
            } else {
                twitterStatus.addAll(fetchTweets);
            }
            feed.setTwitterStatus(twitterStatus);
            feedRepository.save(feed);
        } catch (TwitterException e) {
            System.out.println("Failed to load tweets");
            e.printStackTrace();
        }
        System.out.println(
                "Running task to find Feeds about - " + feed.getKeyword());
    }
}
