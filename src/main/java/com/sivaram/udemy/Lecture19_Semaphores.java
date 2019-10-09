package com.sivaram.udemy;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

enum Processor2{
    INSTANCE;
    Semaphore semaphore = new Semaphore(5,true);

    public void downloadData(){
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        download();
        semaphore.release();
    }

    private void download() {
        System.out.println("downloading data");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class Lecture19_Semaphores {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(12);
        for(int i=0;i<12;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    Processor2.INSTANCE.downloadData();
                }
            });
        }
    }
}
