package com.company;

import java.io.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Sapir on 06.04.2017.
 */
public class Hack extends Thread{

    private byte [] decryptFileBytes;
    private String arrayChars;
    private MyDictionary myDictionary;
    private int from, until;
    private MatchFound matchFound;
    private byte[] encryptedFileBytes;



    public Hack(byte [] encryptedFile, int from, int until, MatchFound matchFound,MyDictionary myDictionary) {
        this.myDictionary =myDictionary ;
        this.encryptedFileBytes = encryptedFile;
        this.from = from;
        this.until = until;
        this.matchFound = matchFound;
        decryptFileBytes=new byte[encryptedFile.length];
    }

    public void run() {
        int count;
        for (int i =from; i <until; i++) {
            count=0;
            arrayChars=new String(getDecryptFile(i)) ;
            for (String word : myDictionary.getWords()) {
                if (containWord(word,arrayChars )) {
                    count++;
                }
            }
            if (count >= 3)
                matchFound.found(i,decryptFileBytes);
        }

    }

    private byte[] getDecryptFile(int key) {
        for (int i = 0; i <encryptedFileBytes.length ; i++) {
            decryptFileBytes[i]= (byte) (encryptedFileBytes[i]-key);
        }
        return decryptFileBytes;
    }


    public static boolean containWord(String word, String text){
        int index=text.indexOf(word);
        if (index==-1)
            return false;
        if (index!=0 ) {
            char charBefore = text.charAt(index - 1);
            if (charBefore!='.' &&charBefore!=' ' && charBefore!=',')
                return false;
        }
        return true;
    }

    public interface MatchFound{
        void found(int key, byte[] text);// המפוצח
    }


}
