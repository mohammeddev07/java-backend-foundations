package org.foundationsofcode.service;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicityFixedAtomicInteger {
    private static final AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            for(int i=0; i<1_000_000; i++){
                count.incrementAndGet();
            }
        });

        Thread t2 = new Thread(() -> {
            for(int i=0; i<1_000_000; i++){
                count.incrementAndGet();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("count=" + count.get());
    }
}
