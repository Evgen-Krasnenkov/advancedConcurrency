package com.multithread.task5.BlockingQueue;

import com.multithread.task5.Utils;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    private final BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Utils.doAction("Up to put item...");
                int random = (int) (Math.random() * 100);
                Utils.doAction("Adding " + random);
                queue.put(random);
                Utils.doAction("Completed -  Size: " + queue.size());
            } catch (InterruptedException e) {
                throw new RuntimeException("Queue is blocked" + e);
            }
        }
    }
}
