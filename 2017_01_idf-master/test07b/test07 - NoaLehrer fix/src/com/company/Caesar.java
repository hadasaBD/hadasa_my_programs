package com.company;

import java.util.Map;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Caesar{
    public static final int SIZE = 1000;
    public Map<String,Integer> map;
    byte[] text;

    public Caesar(byte[] text,Map<String,Integer> map) {
        this.map=map;
        this.text=text;
    }
    public void decryptWithThread(){
        FindIndexListener findIndexListener=new FindIndexListener() {
            @Override
            public void find(byte index) {
                System.out.println(index);
                for (int i = 0; text[i] != 0 && i < SIZE ; i++) {
                    text[i]=(byte)(text[i] - index);
                    System.out.print((char)(text[i]));
                }
                System.out.println();
            }
        };
        byte from=0,to= (byte) (255/2);
        DecryptionThread firstDecryptionThread1=new DecryptionThread(map,text,from,to,findIndexListener);
        firstDecryptionThread1.start();
        from= (byte) (255/2);
        to= (byte) 255;
        DecryptionThread secondDecryptionThread2=new DecryptionThread(map,text,from,to,findIndexListener);
        secondDecryptionThread2.start();
    }


}
