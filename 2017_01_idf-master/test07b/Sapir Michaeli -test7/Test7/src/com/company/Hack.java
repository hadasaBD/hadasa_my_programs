package com.company;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Sapir on 06.04.2017.
 */
public class Hack extends Thread{

    private File file;
    private String arrayChars;
    private UserIO output;
    private MyDictionary myDictionary;
    private int from, until;
    private MatchFound matchFound;

    Set<String> commonWords;



    public Hack(File encryptedFile, int from, int until, MatchFound matchFound) {
        myDictionary = new MyDictionary();
        this.file = encryptedFile;
        this.from = from;
        this.until = until;
        this.matchFound = matchFound;
    }


    public Hack() {
        commonWords=new TreeSet<>();
        initializeCommWords();
    }

    private void initializeCommWords() {
        commonWords.add("be");
        commonWords.add("to");
        commonWords.add("of");
        commonWords.add("and");
        commonWords.add("a");
        commonWords.add("in");
    }


    @Override
    public void run() {
        try {
            int cipherKey = decode(file);
            if (matchFound != null)
                matchFound.found(cipherKey,"");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private int decode(File encryptedFile) throws IOException {
        int key = from;
        byte[] fileChars = FileManipulator.fileToCharArray(encryptedFile);
            if(key == until)
                return Integer.MAX_VALUE;
            key++;
        return key;
    }

    public byte[] tryDecrypt(byte[] fileChars, int key) {
        for (int i = 0; i < fileChars.length; i++)
            fileChars[i] += key;
        return fileChars;
    }

       public void hack(File file){
        try {
            byte[] fileChars = FileManipulator.fileToCharArray(file);
            hack(fileChars,commonWords ,matchFound);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void hack (byte[] fromFile, Set<String> commonWords, Hack.MatchFound matchFound){
        for (int i = 0; i <256 ; i++) {
            for (int j = 0; j <fromFile.length ; j++) {
                fromFile[j]++;
            }
            int count=0;
            String text=new String(fromFile);
            for (String commonWord:  commonWords) {
                if (containWord(commonWord,text)) {
                    count++;
                    if (count==3)
                        matchFound.found(i,text);
                }
            }
        }
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
        void found(int key, String text);// המפוצח
    }


}
