package com.company;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rubin on 19/04/2017.
 */
public class CommonWords {
    Set<String> wordList;

    public CommonWords() {
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
