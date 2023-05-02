package com.multithread.task5.BlockingQueue;

import com.multithread.task5.Utils;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Utils.doAction("Up to pick item...");
                Utils.doAction("Picking...");
                Integer poll = queue.take();
                Utils.doAction("Completed number - " + poll);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
