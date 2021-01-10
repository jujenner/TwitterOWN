package org.hs.os.service;

import org.hs.os.model.Feed;
import org.hs.os.model.task.FeedTask;
import org.hs.os.model.task.StopTask;
import org.hs.os.repositories.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.util.annotation.Nullable;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class FeedService {

    private final TaskService taskService;
    private final TwitterService twitterService;
    private final FeedRepository feedRepository;

    @Autowired
    public FeedService(TaskService taskService, TwitterService twitterService, FeedRepository feedRepository) {
        this.taskService = taskService;
        this.twitterService = twitterService;
        this.feedRepository = feedRepository;
    }

    // Feed anlegen
    public Feed createFeed(String keyword, int count, Date period, @Nullable Date end) {
        Feed feed = new Feed(keyword, count, new Date(), end, period,Collections.emptyList());
        feedRepository.save(feed);

        long time = TimeUnit.MINUTES.toMillis(period.getMinutes());
        if (time == 0){
            time = TimeUnit.HOURS.toMillis(period.getHours());
        }

        taskService.scheduleTask(new FeedTask(twitterService, feedRepository, feed), feed.getId(), time);

        // Wenn keine Endzeit angegeben, dann stoppt der Feed
        if (end != null) {
            taskService.getScheduler().schedule(new StopTask(feed.getId(), taskService), end);
        }

        return feed;
    }

    //Alle Feed aus DB holen
    public List<Feed> getAll() {
        return feedRepository.findAll();
    }

    @Nullable
    public Feed findById(Long id) {
        return feedRepository.findById(id).orElse(null);
    }

    public void deleteFeed(Long id) {
        Feed feed = findById(id);
        if (feed == null) return;
        feedRepository.delete(feed);
    }

    public void stopFeed(long id) {
        taskService.stopTask(id);
    }

    public void deleteAll() {
        feedRepository.deleteAll();
    }
}
