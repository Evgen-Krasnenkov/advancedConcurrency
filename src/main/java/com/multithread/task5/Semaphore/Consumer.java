package com.multithread.task5.Semaphore;

import com.multithread.task5.Utils;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable{
    private final Semaphore semaphore;
    private final List<Integer> line;

    public Consumer(Semaphore semaphore, List<Integer> line) {
        this.semaphore = semaphore;
        this.line = line;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Utils.doAction("Start consuming...");
                Utils.doAction("it is nice...");
                if (line.size() < 1){
                    continue;
                }
                line.remove(0);
                System.out.println(semaphore.availablePermits() + " : Slots");
                Utils.doAction("Finished!!! Size of line: " + line.size());
                semaphore.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
