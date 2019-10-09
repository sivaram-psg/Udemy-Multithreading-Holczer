package com.sivaram.udemy;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker5 implements Runnable{
private int id;
private CyclicBarrier cyclicBarrier;
private Random random;
public Worker5(int id,CyclicBarrier cyclicBarrier){
    this.id = id;
    this.cyclicBarrier = cyclicBarrier;
    this.random = new Random();

}
    @Override
    public void run() {
        System.out.println("Thread with id: "+id+" starting");
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread with id "+id+" completed");

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("After await");

    }
}
public class Lecture25_Cyclic_Barrier {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("All the tasks are finished");
            }
        });
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i =0;i<5;i++){
            executorService.execute(new Worker5(i+1,cyclicBarrier));
        }
        executorService.shutdown();
    }
}
