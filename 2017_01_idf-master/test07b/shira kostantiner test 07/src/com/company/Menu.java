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

    static Input input = new OnScreen();
    static Output output = new OnScreen();
    private static Set<String> words = new HashSet<>();


    public static void menu() {
      output.output("please enter your choice \n 1:enter word \n 2: print screen the words \n 3: enter path\n 4: exit  ");
      String choice  = input.input();
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
              output.output("c u later!");
              return ;
          default:
              output.output("invalid choice. try again");
              menu();


      }
  }

  private static void enterPath() {
      boolean go = true;
      while (go) {
          output.output("enter path ");
          String path = input.input();
          File file = new File(path);
          if (!(cheackFile(file))) {
              System.out.println("your path not illegal ");
          }
          else {
              go = false;
              Description description = new Description(file);
              description.readFromFile();
          }
      }
  }



    private static boolean cheackFile(File file) {
        if(file.exists()&&file.isFile())
            return true;
        else return false;
    }


    private static void cheackWord() {
        output.output("please enter word ");
        String word = input.input();
        String[] strings = word.split(",");
        for (int i = 0; i <strings.length ; i++) {
            if(!words.contains(strings[i])){
                words.add(strings[i]);}
                else {
                    output.output("the word:" + strings[i] +" is exists ");
                }
            }
            menu();
        }
       //iter

    private static void printWord() {
        for (String word : words) {
            output.output(word);
        }
        menu();
    }



}
