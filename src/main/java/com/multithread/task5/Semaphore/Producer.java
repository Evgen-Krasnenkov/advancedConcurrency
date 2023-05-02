package com.multithread.task5.Semaphore;

import com.multithread.task5.Utils;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Producer implements Runnable{
    private final Semaphore semaphore;
    private final List<Integer> line;

    public Producer(Semaphore semaphore, List<Integer> line) {
        this.semaphore = semaphore;
        this.line = line;
    }

    @Override
    public void run() {
        while(true) {
            try {
                semaphore.acquire();
                Utils.doAction("Start production...");
                Utils.doAction("Working...");
                line.add(new Random().nextInt(1000));
                Utils.doAction("Completed!!! Size of line: " +  line.size());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
