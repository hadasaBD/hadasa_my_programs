package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hackeru on 4/19/2017.
 */
public class Menu {

    public Set<String> w=new HashSet<>();

    public void printMenu(){
        System.out.println("please choose:\n 1 for Enter a word or word list  \n 2 for  Print words \n 3 Breakthrough  \n 4 for Exit");
        String choose = Input();
        switch (choose) {
            case "1":
                enterWords();
                break;
            case "2":
                printWords();
                break;
            case "3":
                breakthrough();
                break;
            case "4":
                System.out.println("bye bye");
                return;
            default:
                System.out.println("Incorrect input, please try again");
        }
        printMenu();
    }

    private void breakthrough() {
        Breakthrough breakthrough=new Breakthrough(w);
        breakthrough.getFileFromUser();
    }

    private void enterWords() {
        System.out.println("Insert a word\\s ");
        String input=Input();
        char[] words=input.toCharArray();
        String s="";
        for (int i = 0; i <words.length ; i++) {
            /*if (i<words.length &&words[i] == ' ') {//בהנחה שמילים שמופרדות ברווחים זה כמו 2 מילים ולא מילה מחוברת
                i++;
            }*/
            if (words[i] == ','||(words[i] == ' ')) {
                check(s);
                s = "";
            } else
                s += words[i];
        }
        check(s);

    }
    private void printWords() {
        for(String var : w)
            System.out.println(var);
    }

    //שאלה 1
    /*private void enterWord() {
        System.out.println("enter word:");
        String word=Input();
        check(word);

    }*/

    public void check(String word) {
        boolean b = false;
        for (String var : w) {
            if (var.equals(word)) {
                b = true;
                System.out.println("The word " + "'" + var + "'" + " is already on the list");
            }
        }
        if (b == false&& !(word.equals(' ')))
            w.add(word);

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
