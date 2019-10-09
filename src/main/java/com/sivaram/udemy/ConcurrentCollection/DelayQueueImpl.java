package com.sivaram.udemy.ConcurrentCollection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueImpl {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<DelayItems> blockingQueue = new DelayQueue<>();
        blockingQueue.offer(new DelayItems(1000,"first message"));
        blockingQueue.offer(new DelayItems(10000,"second message"));
        blockingQueue.offer(new DelayItems(4000,"third message"));

        while(!blockingQueue.isEmpty()){
            System.out.println(blockingQueue.take());
        }
    }
}

class DelayItems implements Delayed{
private long duration;
private String message;

    public DelayItems(long duration, String message) {
        this.duration = duration + System.currentTimeMillis();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     * Returns the remaining delay associated with this object, in the
     * given time unit.
     *
     * @param unit the time unit
     * @return the remaining delay; zero or negative values indicate
     * that the delay has already elapsed
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration - System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }


    @Override
    public int compareTo(Delayed otherDelayed) {
        DelayItems otherDelayItems = (DelayItems)otherDelayed;
        if(this.getDuration()<otherDelayItems.getDuration())
        return -1;
        else if(this.duration>otherDelayItems.getDuration()){
            return  +1;
        }
        else
            return 0;
    }

    @Override
    public String toString(){
        return this.getMessage();
    }
}
