package com.multithread.task5;

public class Utils {
    public static void doAction(String action) throws InterruptedException {
        System.out.println((Thread.currentThread().getName() + " " + action));
        Thread.sleep((int) (Math.random() * 1000));
    }
}
