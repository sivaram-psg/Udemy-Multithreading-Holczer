package com.sivaram.udemy;
class Runner5 extends Thread{
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("Runner5 "+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Runner6 extends Thread{
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("Runner6 "+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class App_Thread {
    public static void main(String[] args) {
        Runner5 t1 = new Runner5();
        Runner6 t2 = new Runner6();
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finished task");

    }
}
