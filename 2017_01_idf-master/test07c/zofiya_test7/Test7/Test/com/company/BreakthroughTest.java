package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 4/19/2017.
 */
class BreakthroughTest {
    String actualMess;
    int actualKey;
    boolean crackSuccses;


    /*@Test
    void hack() {
        actualMess="this is the original message";
        byte[] msgBytes=actualMess.getBytes();
        int key=13;
        for (int i = 0; i <msgBytes.length ; i++) {
            msgBytes[i]=(byte)((int)msgBytes[i]+ key);
        }
        HashSet<String> commonWords=new HashSet<>();
        commonWords.add("is");
        commonWords.add("this");
        commonWords.add("the");
        Breakthrough b=new Breakthrough(commonWords);
        crackSuccses=false;
        String s=b.hack(msgBytes);
        if(!(s.equals(actualMess)))
            fail("did not crack the message");


    }*/


}


