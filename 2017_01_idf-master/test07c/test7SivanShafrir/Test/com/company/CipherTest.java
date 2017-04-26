package com.company;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 4/20/2017.
 */
class CipherTest {
    boolean crackSuccess = false;
    int selectedKey1 = 0;
    @Test
    void checkCipher() {
        Set<String> commonWords = new HashSet<>();
        commonWords.add("the");
        commonWords.add("be");
        commonWords.add("to");
        commonWords.add("of");
        commonWords.add("and");
        commonWords.add("a");
        commonWords.add("in");

        String stringWords = "the, and, to, sivan, This";
        byte [] bytesWords1 = stringWords.getBytes();
        byte [] bytesWords2 = stringWords.getBytes();
        int selectedKey = 3;
        for (int i=0; i< bytesWords1.length;i++){
            bytesWords1[i] +=selectedKey;
            bytesWords2[i] +=selectedKey;
        }


        ThreadCipher.FoundListener listener1 = key -> {
            for(int i=0; i<bytesWords1.length;i++)
                bytesWords1[i]-=key;
            String s =new String(bytesWords1);
            for(int i=0; i<bytesWords2.length;i++)
                bytesWords2[i]-=key;
            String s1 =new String(bytesWords2);
            if(key == selectedKey && (s.equals(stringWords)||s1.equals(stringWords)))
                crackSuccess = true;
        };
        ThreadCipher threadCipher1=new ThreadCipher(bytesWords1,commonWords,1,5,listener1);
        threadCipher1.run();
        ThreadCipher threadCipher2=new ThreadCipher(bytesWords2,commonWords,5,10,listener1);
        threadCipher2.run();
        if(!crackSuccess)
            fail("did not crack the message");

    }

    @Test
    void checkCipher2() {
        Set<String> commonWords = new HashSet<>();
        commonWords.add("the");
        commonWords.add("be");
        commonWords.add("to");
        commonWords.add("of");
        commonWords.add("and");
        commonWords.add("a");
        commonWords.add("in");

        String stringWords = "the,bbbhgkhg in. hghgh and, to, sivan, This";
        byte [] bytesWords1 = stringWords.getBytes();
        byte [] bytesWords2 = stringWords.getBytes();
        int selectedKey = 200;
        for (int i=0; i< bytesWords1.length;i++){
            bytesWords1[i] +=selectedKey;
            bytesWords2[i] +=selectedKey;
        }

        ThreadCipher.FoundListener listener1 = key -> {
            for(int i=0; i<bytesWords1.length;i++)
                bytesWords1[i]-=key;
            String s =new String(bytesWords1);
            for(int i=0; i<bytesWords2.length;i++)
                bytesWords2[i]-=key;
            String s1 =new String(bytesWords2);
            if(key == selectedKey && (s.equals(stringWords)||s1.equals(stringWords)))
                crackSuccess = true;
        };
        ThreadCipher threadCipher1=new ThreadCipher(bytesWords1,commonWords,1,150,listener1);
        threadCipher1.run();
        ThreadCipher threadCipher2=new ThreadCipher(bytesWords2,commonWords,150,256,listener1);
        threadCipher2.run();
        if(!crackSuccess)
            fail("did not crack the message");

    }

    @Test
    void checkCipher3() {

        Set<String> commonWords = new HashSet<>();
        commonWords.add("the");
        commonWords.add("be");
        commonWords.add("to");
        commonWords.add("of");
        commonWords.add("and");
        commonWords.add("a");
        commonWords.add("in");

        String stringWords = ",bbbhgkhg . hghgh and,the . sivan, This, of and";
        byte[] bytesWords1 = stringWords.getBytes();
        byte[] bytesWords2 = stringWords.getBytes();
        for (int j = 0; j <= 256; j++) {
            selectedKey1 = j;
            for (int i = 0; i < bytesWords1.length; i++) {
                bytesWords1[i] += selectedKey1;
                bytesWords2[i] += selectedKey1;
            }

            ThreadCipher.FoundListener listener1 = key -> {
                for (int i = 0; i < bytesWords1.length; i++)
                    bytesWords1[i] -= key;
                String s = new String(bytesWords1);
                for (int i = 0; i < bytesWords2.length; i++)
                    bytesWords2[i] -= key;
                String s1 = new String(bytesWords2);
                if (key == selectedKey1 && (s.equals(stringWords) || s1.equals(stringWords)))
                    crackSuccess = true;
            };
            ThreadCipher threadCipher1 = new ThreadCipher(bytesWords1, commonWords, 0, 127, listener1);
            threadCipher1.run();
            ThreadCipher threadCipher2 = new ThreadCipher(bytesWords2, commonWords, 128, 256, listener1);
            threadCipher2.run();
            if (!crackSuccess)
                fail("did not crack the message");


        }
    }

}