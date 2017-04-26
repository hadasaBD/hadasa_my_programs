package com.company;

import java.util.HashSet;

/**
 * Created by eladlavi on 20/04/2017.
 */
public class CrackCaesar {

    public static void crack(byte[] encrypted, HashSet<String> commonWords, CrackResultListener listener){


        for (int i = 0; i < encrypted.length; i++) {
            encrypted[i] -= 13;
        }

        if(listener != null){
            listener.cracked(13, new String(encrypted));
        }
    }
    public interface CrackResultListener{
        void cracked(int key, String crackedMessage);
    }
}
