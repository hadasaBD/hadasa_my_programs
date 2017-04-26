package com.company;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static final String MAIN = "please choose:\n 1. add word \n 2. print the words\n 3. decode \n type 4 at any point to exit this program";
    private static final String ADD_WORD = "1";
    private static final String PRINT_WORDS = "2";
    private static final String EXIT = "0";
    private static final String DECODE = "3";
    private static IO userIO = new UserIO();
    private static MyDictionary myDictionary = new MyDictionary();
    private static Hack hack = new Hack();


    public static void main(String[] args) {
       menu();
    }

    static public void menu() {
        userIO.output(MAIN);
        String action;
        while (!(action=userIO.input()).equals("4")) {
            switch (action) {
                case ADD_WORD:
                    userIO.output("Enter a word");
                    myDictionary.addWord(userIO.input());
                    userIO.output("Would you like to add more words?  \n press YES Or NO");
                    String answer;
                    while (!(answer = userIO.input()).equals("YES") && !answer.equals("NO")) {
                        userIO.output("Please answer YES or NO");
                    }
                    if (answer.equals("YES")) {
                        userIO.output("Enter words separated by ,  \n To finish press enter");
                        myDictionary.addWord(userIO.input());

                    }
                    userIO.output(MAIN);
                    break;

                case PRINT_WORDS:
                    userIO.output("List of words:");
                    Set<String> tmpWords =myDictionary.getWords();
                    for (String word:tmpWords)  {
                        userIO.output(word);
                    }
                    userIO.output(MAIN);
                    break;

                case DECODE:
                    try {
                        userIO.output("enter path");
                        String path = userIO.input();
                        File file = new File(path);
                        if (!file.exists() && file.isFile())
                            userIO.output("error input");
                        //hack.hack(file);
                        HackListener listener = new HackListener();
                        Hack first = new Hack(file, Integer.MIN_VALUE, Integer.MAX_VALUE / 2, listener);
                        Hack second = new Hack(file, (Integer.MAX_VALUE / 2) + 1, Integer.MAX_VALUE, listener);
                        first.start();
                        second.start();
                        try {
                            first.join();
                            second.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (listener.realKey != Integer.MIN_VALUE) {
                            String decrypted = new String(new Hack().tryDecrypt(FileManipulator.fileToCharArray(file), listener.realKey));
                            userIO.output("key " + listener.realKey);
                            userIO.output("Decrypted file:");
                            userIO.output(decrypted); // ידפיס את ההצפנה
                        } else userIO.output("Couldn't crack the code");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("It is not a correct choice");
            }
        }
    }

}
