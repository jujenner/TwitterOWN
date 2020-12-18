package org.hs.os.service;

import org.hs.os.model.Feed;
import org.hs.os.model.task.FeedTask;
import org.hs.os.model.task.StopTask;
import org.hs.os.repositories.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.util.annotation.Nullable;

import java.util.Date;
import java.util.List;

@Service
public class FeedService {

    private final TaskService taskService;
    private final FeedRepository feedRepository;

    @Autowired
    public FeedService(TaskService taskService, FeedRepository feedRepository) {
        this.taskService = taskService;
        this.feedRepository = feedRepository;
    }

    // Feed anlegen
    public Feed createFeed(String keyword, int count, int period, @Nullable Date end) {
        Feed feed = new Feed(keyword, count);
        taskService.scheduleTask(new FeedTask(feed), feed.getId(), period);

        // Wenn keine Endzeit angegeben, dann stoppt der Feed
        if (end != null) {
            taskService.getScheduler().schedule(new StopTask(feed.getId(), taskService), end);
        }

        feedRepository.save(feed);

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

    public void deleteAll() {
        feedRepository.deleteAll();
    }
}
