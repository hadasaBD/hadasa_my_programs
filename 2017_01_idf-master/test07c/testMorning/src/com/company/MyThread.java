package com.company;

import java.util.Set;

/**
 * Created by MyUser on 18/04/2017.
 */
public class MyThread extends Thread {
    private byte[] arr;
    private int from, to;
    Set<String> listOfWords;
    Listener listener;

    public MyThread(byte[] byteArray, Set<String> listOfWords, int from, int to, Listener listener) {
        this.from = from;
        this.to = to;
        this.arr = byteArray;
        this.listOfWords = listOfWords;
        this.listener = listener;
    }

    public void run() {

        byte[] temp = new byte[arr.length];
        for (int i = from; i < to; i++) {
            for (int j = 0; j < arr.length; j++) {
                temp[j] = (byte) (arr[j] - i);

            }
            int cnt = 0;
            String text = new String(temp);
            for (String s : listOfWords) {
                if (DecryptionCaesar.containWord(s, text)) {
                    cnt++;
                    if (cnt == 3) {
                        listener.found(i, text);
                        break;
                    }


                }
            }
        }
    }


    public interface Listener {
        void found(int key, String text);
    }


}
