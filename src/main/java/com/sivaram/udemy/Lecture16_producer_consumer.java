package com.sivaram.udemy;

import java.util.ArrayList;
import java.util.List;

public class Lecture16_producer_consumer {
    static int LIMIT = 5;
    static int BOTTOM = 0;
    static int value=0;
    static List<Integer> values = new ArrayList<>();
    static Object lock = new Object();
    static class Processor{


        public void produce() throws InterruptedException {
            synchronized (lock) {
                while (true) {
                    if (value >= LIMIT) {
                        System.out.println("The list is full. Allowing consumers to extract values");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        System.out.println("Adding values "+value);
                        values.add(value);
                        value++;
                        lock.notify();
                        Thread.sleep(1000);
                    }
                }
            }
        }

        public void consume() throws InterruptedException {
            synchronized (lock){
                while (true){
                    if(value == BOTTOM){
                        System.out.println("List is empty. Waiting for producer to add values");
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        System.out.println("Removing values " +values.remove(--value));
                        lock.notify();
                        Thread.sleep(1000);
                    }
                }
            }
        }
    }



    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.start();
        consumer.start();
    }
}
