package com.multithread.task3;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class App {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Yevgenii_Krasnenkov\\workspace\\");
        ScanAction scanAction = new ScanAction(file, new Result());
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        Thread deamonThread = new Thread(() -> {
            System.out.println("\rScanning...  press c to stop");
            Scanner scanner = new Scanner(System.in);
            while(true){
                String line = scanner.nextLine();
                if(line.equals("c")){
                    scanAction.cancel(true);
                }
            }
        });
        deamonThread.setDaemon(true);
        deamonThread.start();
        forkJoinPool.invoke(scanAction);
        List<ForkJoinTask<Void>> tasks = scanAction.getTasks();
        ForkJoinTask.invokeAll(tasks);
        System.out.println("\r" + scanAction.getResult());
    }
}
