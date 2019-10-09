package com.sivaram.udemy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lecture20_Executorservice {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<5;i++){
            executorService.execute(new Processor3());
        }
        executorService.shutdown();
    }
}

class Processor3 implements Runnable{

    @Override
    public void run() {
    for (int i=0;i<10;i++){
        System.out.println(i);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    }
}