package com.company;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by eladlavi on 29/03/2017.
 */
public class UploadedFile extends File {
    private int version;
    private byte[] fileNameBytes;
    private boolean lock;
    public AtomicInteger concurrentDownloaded;

    public UploadedFile(String path){
        super(path);
        this.version = 0;
        this.concurrentDownloaded = new AtomicInteger(0);
    }

    public byte[] getFileNameBytes() {
        return fileNameBytes;
    }

    public void setFileNameBytes(byte[] fileNameBytes) {
        this.fileNameBytes = fileNameBytes;
    }

    public void increaseVersion(){
        version++;
    }

    public int getVersion(){
        return version;
    }

    public void lock(){
        lock = true;
    }

    public void unlock(){
        lock = false;
    }

    public boolean isLocked(){
        return lock;
    }
}
