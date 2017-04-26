package com.company;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 4/20/2017.
 */
class MenuTest {
    String actualMess;
    boolean crackSuccses;
    int actualKey;
    @Test
    public void testQ1(){
        System.out.println("test question 1");
        fail("failed!!!");
    }
    @Test
    public void testQ2(){
        System.out.println("test question 2");
    }
    /*
    @Test
    public void testHack(){
        actualMess="this is the original message";
        byte[] msgBytes=actualMess.getBytes();
        int key=13;
        for (int i = 0; i <msgBytes.length ; i++) {
            msgBytes[i]=(byte)((int)msgBytes[i]+ actualKey);
        }
        HashSet<String>commonWords=new HashSet<>();
        commonWords.add("is");
        commonWords.add("this");
        commonWords.add("the");
        crackSuccses=false;
        Menu.decryptKey(msgBytes, commonWords, new Menu.MatchFound() {
            @Override
            public void found(int key, String crackedText) {
                if (key == actualKey && actualMess.equals(crackedText))
                    crackSuccses = true;
            }
        });
        if (!crackSuccses)
            fail("did not crack the message");
    }‏
*/


}