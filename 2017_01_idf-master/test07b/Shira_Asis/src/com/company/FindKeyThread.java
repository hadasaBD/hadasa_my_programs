package com.company;

import java.util.HashSet;

/**
 * Created by hackeru on 4/19/2017.
 */
public class FindKeyThread extends Thread {

    private int to, from;
    private HashSet<String> set;
    private byte[] fileContent;
    private KeyFoundListener listener;

    public FindKeyThread(int from, int to, byte[] fileContent, HashSet<String> set, KeyFoundListener listener) {
        this.from = from;
        this.to = to;
        this.fileContent = fileContent;
        this.set = set;
        this.listener = listener;
    }

    @Override
    public void run() {

        byte[] temp = new byte[fileContent.length];
        for (int i = from; i < to; i++) {
            for (int j = 0; j < temp.length; j++) {
                temp[j] = (byte) (i + fileContent[j]);
            }
            String string = new String(temp);
            if (search(string)) {
                if (listener != null)
                    listener.keyFound("The Key: " + i + "\n" + string);
            }
        }

    }

    public boolean search(String text) {

        HashSet<String> words = new HashSet<>();
        for (String var : set) {
            int index = text.indexOf(var);
            char tav = '*';
            if (index != -1) {
                if (index != 0)
                    tav = text.charAt(index - 1);
                if ((index == 0) || (tav == ' ') || (tav == ',') || (tav == '.')) {
                    index += var.length();
                    if (index != text.length())
                        tav = text.charAt(index);
                    if ((index == text.length()) || (tav == ' ') || (tav == ',') || (tav == '.'))
                        words.add(var);
                }
                if (words.size() == 3)
                    return true;

            }
        }
        return false;

    }

    interface KeyFoundListener {
        void keyFound(String string);
    }

}