package org.hs.os.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
public class TaskService {

    //Quelle: https://stackoverflow.com/questions/44644141/how-to-stop-a-scheduled-task-that-was-started-using-scheduled-annotation

    public ThreadPoolTaskScheduler getScheduler() {
        return scheduler;
    }

    final ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();

    private final Map<Long, ScheduledFuture<?>> scheduledTasks = new HashMap<>();

    public TaskService() {
        scheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        scheduler.setPoolSize(50);
        scheduler.initialize();
    }

    public void scheduleTask(Runnable runnable, long identifier, long period) {
        ScheduledFuture<?> schedule = scheduler.scheduleAtFixedRate(runnable, period);
        scheduledTasks.put(identifier, schedule);
    }

    public void stopTask(long identifier) {
        ScheduledFuture<?> scheduledFuture = scheduledTasks.getOrDefault(identifier, null);
        if (scheduledFuture == null) return;
        scheduledFuture.cancel(true);
        scheduledTasks.remove(identifier, scheduledFuture);
    }
}

