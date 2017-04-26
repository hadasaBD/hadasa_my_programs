package com.company;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */
public class WordsList {
    Set<String> wordList;

    public WordsList() {
        this.wordList = new HashSet<>();
    }


    public void insertListWords(String[] words, PrintMsgToUserListener printToUserListener){
        for (String word:words) {
            if(!wordList.contains(word)){
                wordList.add(word);
            }
            else
                printToUserListener.printMsgWordExist(word);
        }
    }

    public Set<String> getWords (){
        return this.wordList;
    }
}
