package com.sivaram.udemy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lecture24_latch {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i=0;i<5;i++){
            executorService.execute(new Processor5(i+1,latch));
        }
        latch.await();
        System.out.println("All threads are completed");
        executorService.shutdown();
    }

}

class Processor5 implements Runnable{
private int id;
private CountDownLatch latch;

public Processor5(int id,CountDownLatch latch){
    this.id = id;
    this.latch = latch;
}

    @Override
    public void run() {
        System.out.println("Task "+id+" starts working");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task "+id+" completed");
        latch.countDown();
    }
}