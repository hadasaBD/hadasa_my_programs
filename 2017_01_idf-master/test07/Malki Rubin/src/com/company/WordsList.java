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

    public InsertResult insert(String word){
        if(wordList.contains(word))
            return InsertResult.WORD_EXIST;
        wordList.add(word);
        return InsertResult.SUCCESS;
    }

    public Set<String> insert2(String[] words){
        Set<String> wordsExist=new HashSet<>();
        for (String word:words) {
            if(!wordList.contains(word))
                wordList.add(word);
            else
                wordsExist.add(word);
        }
        return wordsExist;
    }

    public Set<String> getWords (){
        return this.wordList;
    }


}
