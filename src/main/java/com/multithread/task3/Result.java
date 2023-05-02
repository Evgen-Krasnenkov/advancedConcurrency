package com.multithread.task3;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Result {
    private final AtomicInteger filesNumber;
    private final AtomicInteger foldersNumber;
    private final AtomicLong totalFilesSize;

    public Result() {
        filesNumber = new AtomicInteger(0);
        foldersNumber = new AtomicInteger(0);
        totalFilesSize = new AtomicLong(0L);
    }

    public void incrementFilesNumber() {
        filesNumber.getAndIncrement();
    }

    public void incrementFoldersNumber() {
        this.foldersNumber.getAndIncrement();
    }

    public void addToTotalFilesSize(long fileSize) {
        this.totalFilesSize.getAndAdd(fileSize);
    }
    @Override
    public String toString() {
        return "Result{" +
                "filesNumber=" + filesNumber +
                ", foldersNumber=" + foldersNumber +
                ", totalFilesSize=" + totalFilesSize +
                '}';
    }
}
