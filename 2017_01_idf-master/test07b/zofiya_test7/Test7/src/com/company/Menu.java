package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hackeru on 4/19/2017.
 */
public class Menu {

    public Map<String,Integer> words= new HashMap<>();
    public int mone=0;



    public void printMenu(){
        System.out.println("please choose:\n 1 for Enter a word \n 2 Insert a word list \n 3 for  Print words  \n 4 Breakthrough\n 5 for Exit");
        String choose = Input();
        switch (choose) {
            case "1":
                enterWord();
                break;
            case "2":
                enterWords();
                break;
            case "3":
                printWords();
                break;
            case "4":
                breakthrough();
                break;

            case "5":
                System.out.println("bye bye");
                return;
        }
        printMenu();
    }

    private void breakthrough() {
        Breakthrough breakthrough=new Breakthrough(words);
        breakthrough.getFileFromUser();
    }

    private void enterWords() {
        System.out.println("Insert a word list");
        String input=Input();
        char[] words=input.toCharArray();
        String s="";
        for (int i = 0; i <words.length ; i++) {
            if (words[i] == ',') {
                check(s);
                s="";
            } else
                s += words[i];
        }
        check(s);

    }
    private void printWords() {
        Set set=words.keySet();
        for (int i = 0; i <words.size() ; i++) {
            System.out.println(set.toArray()[i]);
        }
    }
    private void enterWord() {
        System.out.println("enter word:");
        String word=Input();
        check(word);

    }

    public void check(String word) {
        Set set = words.keySet();
        boolean b = false;
        for (int i = 0; i < words.size(); i++) {
            if (set.toArray()[i].equals(word)) {
                b = true;
                System.out.println("The word is already on the list");
            }
        }
        if(b==false)
           words.put(word, mone++);

    }


    public static String Input() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {

        }
        return "error";
    }
}
