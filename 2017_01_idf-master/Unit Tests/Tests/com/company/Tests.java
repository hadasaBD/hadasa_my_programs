package com.company;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by eladlavi on 20/04/2017.
 */
class Tests {

    int actualyKey;
    String actualMessage;
    boolean crackSuccess;

    @Test
    public void testQ1(){
        System.out.println("test question 1");
        fail("failed!!!!");
    }

    @Test
    public void testQ2(){
        System.out.println("test question 2");
    }

    @Test
    public void testCrack(){
        actualMessage = "this is the,original message";
        byte[] msgBytes = actualMessage.getBytes();
        this.actualyKey = 13;
        for (int i = 0; i < msgBytes.length; i++) {
            msgBytes[i] = (byte)((int)msgBytes[i] + actualyKey);
        }
        HashSet<String> commonWords = new HashSet<>();
        commonWords.add("this");
        commonWords.add("is");
        commonWords.add("the");
        crackSuccess = false;
        CrackCaesar.crack(msgBytes, commonWords,
                (key, crackedMessage) -> {
                    if(key == actualyKey &&
                            actualMessage.equals(crackedMessage))
                        crackSuccess = true;
                });
        if(!crackSuccess)
            fail("did not crack the message");
    }

}