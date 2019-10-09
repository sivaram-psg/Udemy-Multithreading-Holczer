package com.sivaram.udemy;

public class Lecture14_synchronized_block {
    static int value1=0;
    static int value2=0;
    static Object lock1 = new Object();
    static Object lock2 = new Object();
    public static void increment1() {
        synchronized(lock1) {
                 value1++;
        }
    }
    public static void increment2(){
        synchronized (lock2) {
                value2++;
          }
    }

    public static void process(){
        for (int i=0;i<100;i++) {
            increment1();
            increment2();
        }
    }
    public static void main(String[] args) {
            Thread t1=new Thread(new Runnable() {
                @Override
                public void run() {
                    process();
                }
            });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                process();
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

        System.out.println("value1 "+value1);
        System.out.println("value2: "+value2);
    }
}
