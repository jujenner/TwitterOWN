package org.hs.os.model.task;

import org.hs.os.service.TaskService;

public class StopTask implements Runnable {
    private final long identifier;
    private final TaskService taskService;

    public StopTask(long identifier, TaskService taskService) {
        this.identifier = identifier;
        this.taskService = taskService;
    }

    @Override
    public void run() {
        taskService.stopTask(identifier);
    }
}
