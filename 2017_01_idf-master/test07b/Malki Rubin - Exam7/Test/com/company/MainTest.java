package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 4/19/2017.
 */
class MainTest {


    @Test
    void menu() {

    }

    @Test
    void insertValidWords() {
        String wordsFromUser="a,b,c,";
        String[]words=wordsFromUser.split(",");
        WordsList wordsList=new WordsList();
        PrintMsgToUserListener listener=new PrintMsgToUserListener() {
            @Override
            public void printMsgWordExist(String s) {

            }
        };
        wordsList.insertListWords(words,listener);
        Set<String> allWord=wordsList.getWords();
        Collection<String> values=allWord;
        if(values.size() != words.length)
            Assertions.fail("fail on insertWords");
        int j=0;
        for (String i : values) {
            if(!i.equals(words[j++]))
                Assertions.fail("fail on insertWords");
        }
    }

    @Test
    void findKeyOnFileWithCommonWords() {
        WordsList wordsList=createWordListObject();
        String dataFile=",a,b,c,r,w,";
        byte[] bytesToDecrypt=dataFile.getBytes();
        for (int i = 0; i < bytesToDecrypt.length; i++) {
            bytesToDecrypt[i]+=100;
        }

        FindKeyListener findKeyListener=new FindKeyListener() {
            @Override
            public void findKey(int key,String value) {
                if(key!=100)
                    Assertions.fail("invalid key");
                byte[] data=value.getBytes();
                for (int i = 0; i < data.length; i++) {
                    if((byte)(data[i]+100)!=(byte)bytesToDecrypt[i])
                        Assertions.fail("invalid decrypt");
                }
            }
        };

        EncryptionCrackingThread firstThread=new EncryptionCrackingThread(0,128, findKeyListener, bytesToDecrypt,wordsList);
        EncryptionCrackingThread secondThread=new EncryptionCrackingThread(129, 255, findKeyListener,bytesToDecrypt,wordsList);

        firstThread.start();
        secondThread.start();
        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    void findKeyOnFileWithOutCommonWords() {
        WordsList wordsList=createWordListObject();
        String dataFile="-a#(b#c-r-w-";
        byte[] bytesToDecrypt=dataFile.getBytes();

        for (int i = 0; i < bytesToDecrypt.length; i++) {
            bytesToDecrypt[i]+=1;
        }

        FindKeyListener findKeyListener=new FindKeyListener() {
            @Override
            public void findKey(int key,String value) {
                Assertions.fail("no key");
            }
        };

        EncryptionCrackingThread firstThread=new EncryptionCrackingThread(0,128, findKeyListener, bytesToDecrypt,wordsList);
        EncryptionCrackingThread secondThread=new EncryptionCrackingThread(129, 255, findKeyListener,bytesToDecrypt,wordsList);

        firstThread.start();
        secondThread.start();
        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    WordsList createWordListObject(){
        String data="a,b,c,";
        String[]words=data.split(",");
        WordsList wordsList=new WordsList();

        PrintMsgToUserListener printListener=new PrintMsgToUserListener() {
            @Override
            public void printMsgWordExist(String s) {

            }
        };
        wordsList.insertListWords(words,printListener);
        return wordsList;
    }

    @Test
    void getFile() {
    }

}