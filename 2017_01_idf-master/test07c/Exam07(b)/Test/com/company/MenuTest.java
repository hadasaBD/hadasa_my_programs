package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 4/20/2017.
 */
class MenuTest {

    int actualKey=10;
    String dataFile=null;
    boolean arrivedListener =false;
    int currentKey;
    CommonWords commonWords =createWordListObject();

    @Test
    void insertValidWords() {
        String wordsFromUser="a,b,c,";
        String[]words=wordsFromUser.split(",");
        CommonWords commonWords =new CommonWords();
        PrintMsgToUserListener listener=new PrintMsgToUserListener() {
            @Override
            public void printMsgWordExist(String s) {
                arrivedListener=true;
            }
        };
        commonWords.insertListWords(words,listener);
        Set<String> allWord= commonWords.getWords();
        Collection<String> values=allWord;
        if(values.size() != words.length)
            Assertions.fail("fail on insertWords");
        int j=0;
        for (String i : values) {
            if(!i.equals(words[j++]))
                Assertions.fail("fail on insertWords");
        }
        String ExistWord="a,";
        String[]word=ExistWord.split(",");
        commonWords.insertListWords(word,listener);
        if(!arrivedListener)
            fail("enter exist word");
    }

    @Test
    void findKeyFileWithCommonWords() {
        dataFile=",a,s,w,b,c,";
        byte[] bytesToDecrypt=dataFile.getBytes();
        for (currentKey = 0; currentKey < 256 ; currentKey++) {
            if(currentKey!=0){
                for (int i = 0; i < bytesToDecrypt.length; i++) {
                    bytesToDecrypt[i]++;
                }
            }
            FindKeyListener findKeyListener=new FindKeyListener() {
                @Override
                public void findKey(int key,String value) {
                    if(key!=currentKey){
                        Assertions.fail("invalid key");
                        System.out.println("currentKey:"+currentKey);
                        System.out.println("key:"+key);
                    }
                    if(!dataFile.equals(value))
                        Assertions.fail("invalid decrypt");
                }
            };
            Menu menu=new Menu(new InputFromScreen(),new OutputToScreen());
            menu.findKeyFileQuickly(commonWords,bytesToDecrypt,findKeyListener);
        }
    }

    @Test
    void findKeyFileWithOutCommonWords() {
        String dataFile="-a#(b#c-r-w-";
        byte[] bytesToDecrypt=dataFile.getBytes();
        for (int i = 0; i < bytesToDecrypt.length; i++) {
            bytesToDecrypt[i]+=actualKey;
        }
        FindKeyListener findKeyListener=new FindKeyListener() {
            @Override
            public void findKey(int key,String value) {
                Assertions.fail("no key");
            }
        };
        Menu menu=new Menu(new InputFromScreen(),new OutputToScreen());
        menu.findKeyFileQuickly(commonWords,bytesToDecrypt,findKeyListener);
    }

    @Test
    void findKeyFileWith1CommonWords() {
        dataFile=",a,s,w,";
        byte[] bytesToDecrypt=dataFile.getBytes();
        for (currentKey = 0; currentKey <256 ; currentKey++) {
            for (int i = 0; i < bytesToDecrypt.length; i++) {
                bytesToDecrypt[i]++;
            }
            FindKeyListener findKeyListener=new FindKeyListener() {
                @Override
                public void findKey(int key,String value) {
                    fail("no key");
                }
            };
            Menu menu=new Menu(new InputFromScreen(),new OutputToScreen());
            menu.findKeyFileQuickly(commonWords,bytesToDecrypt,findKeyListener);
        }
    }

    CommonWords createWordListObject(){
        String data="a,b,c,";
        String[]words=data.split(",");
        CommonWords commonWords =new CommonWords();
        PrintMsgToUserListener printListener=new PrintMsgToUserListener() {
            @Override
            public void printMsgWordExist(String s) {
            }
        };
        commonWords.insertListWords(words,printListener);
        return commonWords;
    }

}