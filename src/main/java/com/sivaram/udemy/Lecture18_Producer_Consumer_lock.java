package com.sivaram.udemy;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Processor{
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void producer() throws InterruptedException {
        lock.lock();
        System.out.println("In producer");
        condition.await();
        System.out.println("Producer again");
        lock.unlock();
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        System.out.println("In consumer");
        Thread.sleep(2000);
        condition.signal();
        System.out.println("Consumer after signal");
        lock.unlock();
    }
}
public class Lecture18_Producer_Consumer_lock {
    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }



}
