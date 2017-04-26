package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Menu {

    Set<String> commonWords = new HashSet<>();


    public void menu() {
        System.out.println("please enter your choice \n 1:enter word \n 2: print screen the commonWords \n 3: enter path\n 4: exit  ");
        String choice = readInputFromUser();
        switch (choice) {
            case "1":
                System.out.println("please enter word");
                String word = readInputFromUser();
                checkWord(word);
                menu();
                break;
            case "2":
                printWord();
                menu();
                break;
            case "3":
                enterPath();
            case "exit":
                System.out.println("c u later!");
                return;
            default:
                System.out.println("invalid choice. try again");
                menu();


        }
    }

    private void enterPath() {
        boolean go = true;
        while (go) {
            System.out.println("enter path ");
            String path = readInputFromUser();
            File file = new File(path);
            if (!(cheackFile(file))) {
                System.out.println("your path not illegal ");
            } else {
                go = false;
                System.out.println("please enter word that you want to found in the file ");
                String word = readInputFromUser();
                Set<String> commonWords = checkWord(word);
                Description description = new Description(file, commonWords);
                description.readFromFile();
            }
        }
    }

    private boolean cheackFile(File file) {
        if (file.exists() && file.isFile())
            return true;
        else return false;
    }


    public Set<String> checkWord(String word) {
        String[] strings = word.split(",");
        for (int i = 0; i < strings.length; i++) {
            if (!commonWords.contains(strings[i])) {
                commonWords.add(strings[i]);
            } else {
                System.out.println("the word:" + strings[i] + " is exists ");
            }
        }
        return commonWords;
    }
    //iter

    private void printWord() {
        for (String word : commonWords) {
            System.out.println(word);
        }

    }

    public String readInputFromUser() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}
