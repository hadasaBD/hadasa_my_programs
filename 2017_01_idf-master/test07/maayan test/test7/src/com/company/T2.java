package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */
public class T2 {
    Set<String> listWord;
    Output output;

    public T2(Set<String> listWord) {
        this.listWord = listWord;
        output=new Output();
    }
    public void menu(){
        String action;
        while ( !(action=getInputFromUser()).equals("3")) {
            output.getOutput("print 1.add words by , 2.print words 3.exit");
            switch (action) {
                case "1":
                    output.getOutput("enter words");
                    addWords(getInputFromUser());
                    break;
                case "2":
                    printWords();
                    break;
                default:
                    output.getOutput("error choice");

            }
            output.getOutput("print 1.add words by , 2.print words 3.exit");
        }


    }

    private void printWords() {
        for (String word:listWord) {
            output.getOutput(word);
        }
    }

    private void addWords(String word) {
        int start=0,length=word.length(),end,counter=0;
        while ((end=word.indexOf(',',start))!=-1) {
            counter++;
            addWord(word.substring(start, end),counter);
            start = end + 1;
        }
        addWord(word.substring(start,length), counter);
    }
    private void addWord(String word, int counter) {
        boolean find=listWord.add(word);
        if(!find)
            output.getOutput("the word  "+counter+" is find");
        else
            output.getOutput("success word "+counter);
    }

    private static String getInputFromUser(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
