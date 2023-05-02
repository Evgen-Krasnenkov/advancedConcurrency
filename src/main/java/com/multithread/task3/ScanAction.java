package com.multithread.task3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ScanAction extends RecursiveAction {
    private final File dir;

    private final Result result;

    private final List<ForkJoinTask<Void>> tasks;

    public List<ForkJoinTask<Void>> getTasks() {
        return tasks;
    }

    public Result getResult() {
        return result;
    }

    public ScanAction(File dir, Result result) {
        this(dir, result,  Collections.synchronizedList(new ArrayList<>()));
    }
    private ScanAction(File dir, Result result, List<ForkJoinTask<Void>> tasks) {
        this.dir = dir;
        this.result = result;
        this.tasks = tasks;
    }

    @Override
    protected void compute() {
        var files = dir.listFiles();
        for (File file : files) {
            System.out.print("\rworking on: " + file.getName());
            if (file.isDirectory()) {
                result.incrementFoldersNumber();
                ScanAction scanAction = new ScanAction(file, result, tasks);
                ForkJoinTask<Void> fork = scanAction.fork();
                tasks.add(fork);
            }
            if (file.isFile()){
                result.incrementFilesNumber();
                result.addToTotalFilesSize(file.length());
            }
        }
    }
}
