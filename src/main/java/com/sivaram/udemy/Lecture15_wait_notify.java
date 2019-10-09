package com.sivaram.udemy;

public class Lecture15_wait_notify {
    public void producer() throws InterruptedException {
        System.out.println("Producer running");
        synchronized (this){
            wait(1000);
        }
        System.out.println("Producer running again");
    }

    public void consumer() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("consumer running");
        synchronized (this){
            notify();

        }
        Thread.sleep(3000);
    }

    public static void main(String[] args) throws InterruptedException{
        Lecture15_wait_notify wait_notify = new Lecture15_wait_notify();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wait_notify.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    wait_notify.consumer();
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
