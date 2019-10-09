package com.sivaram.udemy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

class FirstWorker implements Runnable{
BlockingQueue<Integer> blockingQueue;
private int counter;
public FirstWorker(BlockingQueue<Integer> blockingQueue){
    this.blockingQueue = blockingQueue;
}
    @Override
    public void run() {



                try {
                    while (true) {
                        blockingQueue.put(counter);
                        System.out.println("putting items to the queue " + counter);
                        counter++;
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }


class SecondWorker implements Runnable{
    BlockingQueue<Integer> blockingQueue;
    public SecondWorker(BlockingQueue<Integer> blockingQueue){
        this.blockingQueue = blockingQueue;
    }
    @Override
    public void run() {
        while (true) {
            try {
                int itemTaken = blockingQueue.take();
                System.out.println("Taking item from the queue " + itemTaken);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class Lecture26_Blocking_queue {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        Thread firstThread = new Thread(new FirstWorker(blockingQueue));
        Thread secondThread = new Thread(new SecondWorker(blockingQueue));
        firstThread.start();
        secondThread.start();

    }
}
