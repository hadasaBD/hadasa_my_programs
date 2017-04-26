package com.company;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Dictionary {
    private Set<String> listWord;
    private OutputInterface output;

    public Set<String> getListWord() {
        return listWord;
    }

    public void setListWord(Set<String> listWord) {
        this.listWord = listWord;
    }

    public Dictionary(OutputInterface output) {
        this.output = output;
        this.listWord = new HashSet<>();
    }

    public void printWords() {
        for (String word : listWord) {
            output.getOutput(word);
        }
    }

    public void addWords(String word) {
        int start = 0, length = word.length(), end, counter = 0;
        while ((end = word.indexOf(',', start)) != -1) {
            counter++;
            addWord(word.substring(start, end), counter);
            start = end + 1;
        }
        addWord(word.substring(start, length), counter);
    }

    private void addWord(String word, int counter) {
        boolean find = listWord.add(word);
        if (!find)
            output.getOutput("the word:" + word + " is find");
        else
            output.getOutput("success");
    }
}
