package org.foundationsofcode.service;

public class VisibilityDemo {
    private static volatile boolean running = true;

    public static void main(String[] args) throws Exception {
        Thread t = new Thread(() -> {
            while(running){
                // busy-wait
            }
            System.out.println("Stopped");
        });

        t.start();
        Thread.sleep(1000);
        running = false;
        System.out.println("Main set running=false");
    }
}
