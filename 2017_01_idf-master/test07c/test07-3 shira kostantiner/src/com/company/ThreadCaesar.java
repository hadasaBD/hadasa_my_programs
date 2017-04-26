package com.company;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by This_user on 16/04/2017.
 */
public class ThreadCaesar extends Thread {

    private int from, to;
    private keyFoundListener listener;
    private static Set<String> commonWords = new HashSet<>();
    private byte[] fileEncrypt;


    public ThreadCaesar(byte[] fileEncrypt, Set<String> commonWords, int from, int to, keyFoundListener listener) {
        this.fileEncrypt = fileEncrypt;
        this.commonWords = commonWords;
        this.from = from;
        this.to = to;
        this.listener = listener;
    }

    @Override
    public void run() {
        int key;
        boolean go = true;
        byte []fileDecrypt = new byte[fileEncrypt.length];
        for (key = from; key < to && go; key++) {
            for (int i = 0; i < fileEncrypt.length; i++) {
                fileDecrypt[i] = (byte) (fileEncrypt[i] - key);
            }
            String decrypt = new String(fileDecrypt);
            boolean keyFound = crack(decrypt);
            if (keyFound) {
                go = false;
                listener.keyFound(key,decrypt);
                break;
            }
        }
    }

    interface keyFoundListener {
        void keyFound(int key,String file);
    }



 /*   private boolean crack(String fileDecrypt) {
        int max = 0;
        int index = 0;
        for (String commonWord : commonWords) {
            int length = commonWord.length();
            if ((index = fileDecrypt.indexOf(commonWord)) != -1) {
                if (index == 0 || fileDecrypt.charAt(index - 1) == ' ' || fileDecrypt.charAt(index - 1) == '.' || fileDecrypt.charAt(index - 1) == ',') {
                    if (((index + length) >= fileDecrypt.length()) || (fileDecrypt.charAt(index + length) == '.') || (fileDecrypt.charAt(index + length) == ',') || (fileDecrypt.charAt(index + length) == ' ' ))
                        max++;
                }
            }
        }
        if (max >= 3)
            return true;
        else
            return false;
    }*/
    private boolean crack(String fileDecrypt) {
        int max = 0;
        int index = 0;
        for (String commonWord : commonWords) {
            index = 0;
            int length = commonWord.length();
            while ((index = fileDecrypt.indexOf(commonWord,index)) != -1) {
                if(checkIfWord(index,length,fileDecrypt)) {
                    max++;
                    break;
                }
                index++;
            }
        }
        if (max >= 3)
            return true;
        else
            return false;
    }


    private boolean checkIfWord(int where, int wordLength, String fileDecrypt ) {
        if (where == 0 || fileDecrypt.charAt(where - 1) == ' ' || fileDecrypt.charAt(where - 1) == '.' || fileDecrypt.charAt(where - 1) == ',') {
            if (where + wordLength >= fileDecrypt.length() || fileDecrypt.charAt(where + wordLength) == '.' ||
                    fileDecrypt.charAt(where + wordLength) == ',' || fileDecrypt.charAt(where + wordLength) == ' ')
                return true;
        }
        return false;
    }


}

