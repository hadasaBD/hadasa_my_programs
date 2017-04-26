package com.company;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 4/19/2017.
 */
class DescriptionTest {
    boolean crackSuccess=false;
    int currentKey = 0;
    Description description = new Description() ;

    private Set<String> a() {
        Set<String> commonWords = new HashSet<>();
        commonWords.add("the");
        commonWords.add("be");
        commonWords.add("to");
        commonWords.add("of");
        commonWords.add("and");
        commonWords.add("a");
        commonWords.add("in");
        return commonWords;
    }


    @Test
    void crackWithCorrectKey() {
        Set<String> commonWords = a();
        String words = "the is in to be ";
        byte[] bytesWords = words.getBytes();

        for (currentKey = 0; currentKey <256 ; currentKey++) {
            for (int i = 0; i < bytesWords.length; i++) {
                bytesWords[i] += currentKey;
            }
            ThreadCaesar.keyFoundListener listener1 = new ThreadCaesar.keyFoundListener() {
                @Override
                public void keyFound(int key, String file) {
                    /*Description description = new Description();
                    description.printFile(file.getBytes(), key);*/
                    if (key == currentKey && file.equals(words))
                        crackSuccess = true;
                }
            };
            ThreadCaesar threadCipher1 = new ThreadCaesar(bytesWords, commonWords, 0, 127, listener1);
            threadCipher1.run();
            ThreadCaesar threadCipher2 = new ThreadCaesar(bytesWords, commonWords, 128, 256, listener1);
            threadCipher2.run();
            if (!crackSuccess)
                fail("did not crack the message");
        }
    }

    @Test
    void crackWithNotCorrectKey() {
        Set<String> commonWords = a();
        String stringWords = "the is in to be ";
        byte[] bytesWords = stringWords.getBytes();
        for (int i = 0; i < bytesWords.length; i++) {
            bytesWords[i] += 7;
        }
        ThreadCaesar.keyFoundListener listener1 = new ThreadCaesar.keyFoundListener() {
            @Override
            public void keyFound(int key, String file) {
                    /*Description description = new Description();
                    description.printFile(file.getBytes(), key);*/
                if (key == 4 && file.equals(stringWords))
                    crackSuccess = true;
            }
        };
        ThreadCaesar threadCipher1 = new ThreadCaesar(bytesWords, commonWords, 0, 127, listener1);
        threadCipher1.run();
        ThreadCaesar threadCipher2 = new ThreadCaesar(bytesWords, commonWords, 128, 256, listener1);
        threadCipher2.run();
        if (!crackSuccess)
            fail("did not crack the message");
    }



    @Test
    void crackWithCorrectKeyWhenTheFileHaveAWordAndAInWord() {
        Set<String> commonWords = a();
        a();
        String stringWords = "shira be a  the to ahj";
        byte[] bytesWords = stringWords.getBytes();
        for (int i = 0; i < bytesWords.length; i++) {
            bytesWords[i] += 145;
        }
        ThreadCaesar.keyFoundListener listener1 = new ThreadCaesar.keyFoundListener() {
            @Override
            public void keyFound(int key, String file) {
                    /*Description description = new Description();
                    description.printFile(file.getBytes(), key);*/
                if (key == 145 && file.equals(stringWords))
                    crackSuccess = true;
            }
        };
        ThreadCaesar threadCipher1 = new ThreadCaesar(bytesWords, commonWords, 0, 127, listener1);
        threadCipher1.run();
        ThreadCaesar threadCipher2 = new ThreadCaesar(bytesWords, commonWords, 128, 256, listener1);
        threadCipher2.run();
        if (!crackSuccess)
            fail("did not crack the message");
    }



}