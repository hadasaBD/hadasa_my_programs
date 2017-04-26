package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Menu {



    private static Set<String> words = new HashSet<>();
    public static void menu() {
      System.out.println("please enter your choice \n 1:enter word \n 2: print screen the words \n 3: enter path\n 4: exit  ");
      String choice  = getInputFromUser();
      switch (choice) {
          case "1":
              cheackWord();
              break;
          case "2":
              printWord();
              break;
          case "3":
              enterPath();
          case "exit":
              System.out.println("c u later!");
              return ;
          default:
              System.out.println("invalid choice. try again");
              menu();


      }
  }

    private static void enterPath() {
        System.out.println("enter path ");
        String path = getInputFromUser();
        File file  = new File(path);
        Description description = new Description(file);
        description.getFile();

    }


    private static void cheackWord() {
        System.out.println("please enter word ");
        char[] chars = new char[50];
        String word = getInputFromUser();
        //todo:change array
        for (int i = 0; i < word.length(); i++) {
            chars[i] = word.charAt(i);
        }
        int index = 0;
        for (int i = 0; i < word.length(); i++) {
            if ((word.charAt(i) == ',')) {
                index = cheackExits(chars,index,i);
            }
        }
        cheackExits(chars,index,word.length()-1);
        menu();

    }



    private static int cheackExits(char[] chars,int index,int i) {
        String w = new String(chars, index, i);
        index += i + 1;
        if (!words.contains(w)) {
            words.add(w);
        } else {
            System.out.println("the word:" + w +" is exists ");
        }
        return index;
    }


    private static void printWord() {
        for (String word : words) {
            System.out.println(word);
        }

    }




    static String getInputFromUser(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
