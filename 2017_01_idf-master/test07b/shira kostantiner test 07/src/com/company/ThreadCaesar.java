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
        for (key = from; key < to; key++) {
            boolean keyFound = caesar(key);
            if (keyFound) {
                listener.keyFound(key);
                break;
            }
        }
    }

    interface keyFoundListener {
        void keyFound(int max);
    }



    private boolean caesar(int key) {
        int max = 0;
        for (int i = 0; i < fileEncrypt.length; i++) {
            fileEncrypt[i] = (byte) (fileEncrypt[i] - key);
        }
        String fileDecrypt = new String(fileEncrypt);
        int num = 0 ;
        for (String commonWord : commonWords) {
            num = fileDecrypt.indexOf(commonWord);
            /*if ((num != -1) &&((fileDecrypt.charAt(num-1)==' ')
                    ||(fileDecrypt.charAt(num-1)==',')||(fileDecrypt.charAt(num-1)=='.'))
                    &&((fileDecrypt.charAt(num+commonWord.length()+1)==' ')||(fileDecrypt.charAt(num+commonWord.length()+1))==',')||(fileDecrypt.charAt(num+commonWord.length()+1)=='.')){*/
                if(fileDecrypt.indexOf(commonWord) != -1){
                max++;
                if (max >= 3)
                    return true;
            }

        }
        return false;
    }
}

