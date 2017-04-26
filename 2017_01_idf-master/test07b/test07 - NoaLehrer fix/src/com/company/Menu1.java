package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Menu1 {
    public static final String ENTERWORD = "1";
    public static final String PRINTWORDS = "2";
    public static final String EXIT = "3";
    public Map<String,Integer> map;

    public Menu1(Map<String,Integer> map) {
        this.map=map;
        while(menu());
    }

    private boolean menu() {
        String input;
        System.out.println("please choose:");
        System.out.println("1. enter a word");
        System.out.println("2. print all words");
        System.out.println("3. EXIT");
        input=scanChoose();
        switch (input) {
            case ENTERWORD:
                enterWord();
                break;
            case PRINTWORDS:
                printWords();
                break;
            case EXIT:
                System.out.println("c u later!");
                return false;
            default:
                System.out.println("invalid choice. try again");
                break;

        }
        return true;
    }

    private void printWords() {
        Set set=map.keySet();
        for (int i = 0; i <map.size() ; i++) {
            System.out.println(set.toArray()[i]);
        }
    }

    private void enterWord() {
        System.out.println("enter word");
        String input=scanChoose();
        if(map.get(input)!=null){
            System.out.println("the word is exist");
            return;
        }
        map.put(input,1);
    }


    public String scanChoose() {
        String sScanner = setMessage();
        if (sScanner.length() == 0) {
            System.out.println("your choose is invalid, try again");
            scanChoose();
        }
        return sScanner;
    }


    public String setMessage() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
