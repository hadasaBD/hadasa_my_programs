package com.company;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by hackeru on 19.04.2017.
 */
public class MyDictionary {

    Set<String> commonWords;
    private Set<String> words;


    public MyDictionary() {
        commonWords=new TreeSet<>();
         words=new TreeSet<>();
    }

    public Set<String> getWords() {
        return words;
    }

     void addWord(String word) {
        int count = 0;
        int begin = 0;
        int end ;
        int length=word.length();
        while ((end=word.indexOf(',',begin))!=-1) {
            count++;
            addWord(word.substring(begin,end),count);
            begin=end+1;
        }
        addWord(word.substring(begin,length), count);
        words.add(word);
    }

    private void addWord(String word, int count) {
        boolean find=words.add(word);
        if(!find)
            System.out.println("the word already found");
        else
            System.out.println("success word ");
    }

}
