package com.example.joanna.fin;

/**
 * Created by jerry on 02/04/16.
 */
public class RunningTask {
    private long epoch_start;
    private long epoch_end;
    private Task task;

    public RunningTask(Task task) {
        this.task = task;
    }

    public void start() {
        epoch_start = System.currentTimeMillis() / 1000;
    }

    public void end() {
        epoch_end = System.currentTimeMillis() / 1000;
    }

    public long getStart() {
        return epoch_start;
    }

    public long getEnd() {
        return epoch_end;
    }

    public Task getTask() {
        return task;
    }

}
