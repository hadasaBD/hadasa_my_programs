package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */
public class T1 {
    Set<String> listWord;
    OutputInterface output;

    public T1(Set<String> listWord,OutputInterface output) {
        this.listWord = listWord;
        this.output=output;
    }
    public void menu(){
       output.getOutput("print 1.add word 2.print words 3.exit");
        String choice;
        while ( !(choice=getInputFromUser()).equals("3")) {
            switch (choice) {
                case "1":
                    output.getOutput("enter word");
                    addWord(getInputFromUser());
                    break;
                case "2":
                    printWords();
                    break;
                default:
                    output.getOutput("error choice");

            }
            output.getOutput("print 1.add word 2.print words 3.exit");
        }

    }

    private void printWords() {
        for (String word:listWord) {
            output.getOutput(word);
        }
    }

    public void addWord(String word) {
        boolean find=listWord.add(word);
        if(!find)
            output.getOutput("word is find");
        else
            output.getOutput("success");
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
