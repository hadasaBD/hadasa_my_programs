package com.company;

import java.util.HashSet;
import java.util.Set;


public class ThreadCipher extends Thread {

    private byte[] bytesAllFile;
    private static Set<String> commonWords = new HashSet<>();
    private int from, to;
    private FoundListener listener;


    public ThreadCipher(byte[] bytesAllFile, Set<String> commonWords, int from, int to, FoundListener listener) {
        this.bytesAllFile = bytesAllFile;
        this.commonWords = commonWords;
        this.from = from;
        this.to = to;
        this.listener = listener;
    }

    @Override
    public void run() {
        int key ;
        for ( key = from; key < to; key++) {
            boolean keyFound = Decipher(key);
            if (keyFound){
                listener.keyFound(key);
                break;
            }
        }
    }

    private boolean Decipher(int key) {
        int max=0;
        byte[]bytesStringFile = bytesAllFile;
        for(int i=0;i<bytesStringFile.length;i++){
            bytesStringFile[i]-=key;
        }
        String stringDecipher = new String(bytesStringFile);
        for (String commonWord : commonWords) {
            if( stringDecipher.indexOf(commonWord)!= -1) {
                max++;
                if (max >=3)
                    return true;
            }
        }
        return false;
    }

    interface FoundListener
    {
        void keyFound(int key);
    }

}

