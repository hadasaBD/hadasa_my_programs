package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Rubin on 14/04/2017.
 */
public class EncryptionCrackingThread extends Thread {
    private int from,to;
    private byte[] fileData;
    private FindKeyListener listener;
    private WordsList wordsList;
    
    public EncryptionCrackingThread(int from, int to, FindKeyListener listener, byte[] fileData, WordsList wordsList) {
        this.from = from;
        this.to=to;
        this.listener=listener;
        this.fileData=fileData;
        this.wordsList=wordsList;
    }

    @Override
    public void run() {
        decode(fileData);
    }

    public void decode(byte[] fileData){
        byte[] tempData=new byte[fileData.length];
        for (int j = 0; j < fileData.length; j++) {
            tempData[j]=fileData[j];
        }

        for (int i = from; i <= to ; i++) {
            String stringFromFile = createStringFromFile(tempData,i,from);
            int counterWords=0;
            Set<String> allWords=wordsList.getWords();
            Collection<String> values=allWords;
            for (String w : values) {
                if(containsWord(w, stringFromFile))
                    counterWords++;
            }
            if(counterWords==3){
                if(listener!=null)
                    listener.findKey(i,stringFromFile);
            }
        }
    }

    public static String createStringFromFile(byte[] data,int i,int from){
        for (int j = 0; j < data.length; j++) {
            if(i==from)
                data[j]-= from;
            else
                data[j]-=1;
        }
        return new String(data);
    }

    public static boolean containsWord(String word, String test){
        int index=test.indexOf(word);
        if(index>=0){
            int length=word.length();
            if(index-1>=0 && index+length<test.length())
                if(isCharacterSeparator(test.charAt(index-1)) && isCharacterSeparator(test.charAt(index+length)))
                    return true;
        }
        return false;
    }

    public static boolean isCharacterSeparator(char c){
        if(c==' ' || c=='.' || c==',')
            return true;
        return false;
    }
}

interface FindKeyListener {
    void findKey(int key,String value);
}
