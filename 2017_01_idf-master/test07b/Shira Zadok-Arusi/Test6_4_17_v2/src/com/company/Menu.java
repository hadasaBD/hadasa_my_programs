package com.company;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Menu {
    public static final String WORDS_FROM_USER = "1";
    public static final String PRINT_LIST = "2";
    public static final String EXIT = "0";
    public static final String WORDS_FROM_FILE = "3";
    static Output output=new UserOutput();
    static Input input=new UserInput();


    public static void start(){
        //int i;
        MyWords myWords=new MyWords();
        Decryptor decryptor=new Decryptor();
        while (true) {
            output.output("Hello");
            output.output("What do you want to do?");
            output.output("Enter 1 for enter word");
            output.output("Enter 2 for print the all word");
            output.output("Enter 3 for decrypt file");
            output.output("Enter 0 for exit in any time");
            String choice = input.input();
            switch (choice) {
                case WORDS_FROM_USER:
                    boolean[] isNotExist;
                    output.output("Enter list of words");
                    String words = input.input();
                   isNotExist=myWords.addWords(words);
                    for (int j=0;j<isNotExist.length;j++) {
                        if (!isNotExist[j])
                            output.output("The "+(j+1)+" word is exist");
                    }
                    break;

                case PRINT_LIST:
                    printList(myWords.getWords());
                    break;

                case WORDS_FROM_FILE:
                   String path=getFileFromUser();
                   if(path==null)
                       return;
                   Result result=new Result();
                   boolean isDecrypted=result.decrypt(myWords.getWords(),path);
                   if (isDecrypted)
                       output.output("The decrypt text is:");
                    output.output(result.getString());
                    output.output("The key is: "+result.getKey());


                    break;
                case EXIT:
                    output.output("bye bye");
                    return;
                default:
                    output.output("Your choice is incorrect please try again");
            }
        }



    }
    public static void printList(Set<String> words){
        for (String word:words) {
            output.output(word);
        }
    }

    public static String getFileFromUser() {
        String s=null;
        output.output("Enter a path of file");
        s = input.input();
        File file = new File(s);
        while (!file.exists()||!file.isFile()) {
            output.output("The file isn't exist, enter a correct path again or enter 0 for exit");
            s = input.input();
            if(s.equals("0"))
                return null;
            file = new File(s);
        }
        return s;
    }








}
