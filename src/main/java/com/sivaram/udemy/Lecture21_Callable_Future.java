package com.sivaram.udemy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Lecture21_Callable_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> futures = new ArrayList<>();

        for(int i=0;i<5;i++){
            Future<String> future =executorService.submit(new Processor4(i+1));
            futures.add(future);
        }
        for(Future<String> stringFuture:futures){
            System.out.println(stringFuture.get());
        }
        executorService.shutdown();
    }
}

class Processor4 implements Callable<String>{
 int i;
 public Processor4(int i){
     this.i =i;
 }
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "this: "+i;
    }
}