package com.sivaram.udemy;

class Runner implements Runnable{
     private volatile boolean isTerminated=false;
     public boolean getTerminated(){
         return this.isTerminated;
     }

     public void setTerminated(boolean isTerminated){
         this.isTerminated = isTerminated;
     }

    @Override
    public void run() {
while (!isTerminated){
    System.out.println("printing runnable");
    try {
        Thread.sleep(300);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}
    }
}
public class Lecture11_volatile {
    public static void main(String[] args) {
        Runner runner = new Runner();
        Thread t1 = new Thread(runner);
        t1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runner.setTerminated(true);

    }

}
