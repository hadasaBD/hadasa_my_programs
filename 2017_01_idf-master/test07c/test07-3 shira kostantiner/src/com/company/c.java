/*
package com.company;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

*/
/**
 * Created by hackeru on 4/19/2017.
 *//*

class DescriptionTest {
    boolean crackSuccess=false;
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

        ThreadCaesar.keyFoundListener listener1 = new ThreadCaesar.keyFoundListener() {

            @Override
            public void keyFound(int key, String file) {
                    */
/*Description description = new Description();
                    description.printFile(file.getBytes(), key);*//*

                if (key == 7 && file.equals(words))
                    crackSuccess = true;
            }
        };
        for (int key = 0; key < 256; key++) {
            for (int j = 0; j < bytesWords.length; j++) {
                bytesWords[key] += 7;
                start(commonWords,bytesWords,listener1);
            }
        */
/*for (int i = 0; i < bytesWords.length; i++) {
            bytesWords[i] += 7;
        }*//*




            //start(commonWords, bytesWords, listener1);
            if (!crackSuccess)
                fail("did not crack the message");
        }
    }

    void start(Set<String> commonWords, byte[] bytesWords, ThreadCaesar.keyFoundListener listener1) {
        ThreadCaesar threadCipher1 = new ThreadCaesar(bytesWords, commonWords, 0, 127, listener1);
        threadCipher1.run();
        ThreadCaesar threadCipher2 = new ThreadCaesar(bytesWords, commonWords, 128, 256, listener1);
        threadCipher2.run();
    }

    @Test
    void crackWithNotCorrectKey() {
        Set<String> commonWords = a();
        description.insertWord();
        ;
        String stringWords = "the is in to be ";
        byte[] bytesWords = stringWords.getBytes();
        for (int i = 0; i < bytesWords.length; i++) {
            bytesWords[i] += 7;
        }
        ThreadCaesar.keyFoundListener listener1 = new ThreadCaesar.keyFoundListener() {
            @Override
            public void keyFound(int key, String file) {
                    */
/*Description description = new Description();
                    description.printFile(file.getBytes(), key);*//*

                if (key == 4 && file.equals(stringWords))
                    crackSuccess = true;
            }
        };
        start(commonWords, bytesWords, listener1);
        if (!crackSuccess)
            fail("did not crack the message");
    }

    @Test
    void crackWithCorrectKey2() {
        Set<String> commonWords = a();
        description.insertWord();
        String stringWords = "i am the is be in ";
        byte[] bytesWords = stringWords.getBytes();
        for (int i = 0; i < bytesWords.length; i++) {
            bytesWords[i] += 215;
        }
        ThreadCaesar.keyFoundListener listener1 = new ThreadCaesar.keyFoundListener() {
            @Override
            public void keyFound(int key, String file) {
                    */
/*Description description = new Description();
                    description.printFile(file.getBytes(), key);*//*

                if (key == 215 && file.equals(stringWords))
                    crackSuccess = true;
            }
        };
        start(commonWords, bytesWords, listener1);
        if (!crackSuccess)
            fail("did not crack the message");
    }


}*/
