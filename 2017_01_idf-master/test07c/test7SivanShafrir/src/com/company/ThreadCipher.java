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
        int max = 0;
        byte[] bytesStringFileTemp = new byte[bytesAllFile.length];
        for (int i = 0; i < bytesAllFile.length; i++)
            bytesStringFileTemp[i] = bytesAllFile[i];
        for (int i = 0; i < bytesStringFileTemp.length; i++) {
            bytesStringFileTemp[i] -= key;
        }
        String stringDecipher = new String(bytesStringFileTemp);
        for (String commonWord : commonWords) {
            int length = commonWord.length();
            int index;
            if ((index = stringDecipher.indexOf(commonWord)) != -1) {
                if (index == 0 || stringDecipher.charAt(index - 1) == ' ' || stringDecipher.charAt(index - 1) == '.' || stringDecipher.charAt(index - 1) == ',') {
                    if ((index + length >= stringDecipher.length()) || (stringDecipher.charAt(index + length) == '.') || (stringDecipher.charAt(index + length) == ',') || (stringDecipher.charAt(index + length) == ' '))
                        max++;
                }
            }
        }
        if (max >= 3)
            return true;
        else return false;
    }


    interface FoundListener
    {
        void keyFound(int key);
    }

}

