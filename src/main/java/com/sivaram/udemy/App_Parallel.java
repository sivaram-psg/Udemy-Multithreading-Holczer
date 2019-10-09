package com.sivaram.udemy;
class Runner3 implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("Runner3 "+i);
        }
    }
}

class Runner4 implements Runnable{
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("Runner4 "+i);
        }
    }
}

public class App_Parallel {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner3());
        Thread t2 = new Thread(new Runner4());
        t1.start();
        t2.start();

    }
}
