package com.sivaram.udemy;

public class Lecture13_synchronized {
    static int counter;

    public static void main(String[] args) {
        process();

        System.out.println("counter value "+counter);

    }

    private static void process() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    increment();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10000;i++){
                    increment();
                }
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static synchronized void increment() {
        counter++;
    }
}
