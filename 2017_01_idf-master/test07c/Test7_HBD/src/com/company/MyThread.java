package com.company;

import java.util.Set;

/**
 * Created by hackeru on 4/20/2017.
 */
public class MyThread extends Thread {
    public Set<String> commonWordList;
    private byte[] tempOriginalFile1;
    private byte[] tempOriginalFile2;
    private int key1;
    private int key2;
    private boolean go = true;

    public void setTempOriginalFile1(byte[] tempOriginalFile1) {
        this.tempOriginalFile1 = tempOriginalFile1;
    }

    public void setTempOriginalFile2(byte[] tempOriginalFile2) {
        this.tempOriginalFile2 = tempOriginalFile2;
    }

    @Override
    public void run() {
        int  count =0;
        if (this.getName()== "myThread1") {
            for (int i = 0; i < 127; i++) {
                count = 0;
                for (int j = 0; j < tempOriginalFile1.length; j++) {
                    tempOriginalFile1[j]++;
                }
                String fileString = new String(tempOriginalFile1);

            }
        }
        if (this.getName() == "myThread2"){
            for (int i = 127; i < 256; i++) {
                count = 0;
                for (int j = 0; j < tempOriginalFile2.length; j++) {
                    tempOriginalFile2[j]++;
                }
                String fileString = new String(tempOriginalFile2);

            }
        }

    }

    public interface KeyFoundListener{
        void printWordsFromFile(int key , MyThread myThread);
    }
}
