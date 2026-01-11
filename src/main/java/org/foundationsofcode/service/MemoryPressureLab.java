package org.foundationsofcode.service;

import java.util.ArrayList;
import java.util.List;

public class MemoryPressureLab {
    private static final List<byte[]> LEAK = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        boolean leak = args.length > 0 && args[0].equalsIgnoreCase("leak");

        for (int i = 0; i < 10_000; i++) {
            byte[] block = new byte[512 * 1024]; // 512 KB

            if (leak) {
                LEAK.add(block); // keep reference => old-gen grows over time
            }

            if (i % 100 == 0) {
                System.out.println("i=" + i + " leak=" + leak + " retained=" + LEAK.size());
                Thread.sleep(50);
            }
        }
    }
}
