package com.company;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Menu
{
    public static final int FILE_WORDS = 102;
    public static final int ENTER_WORD = 100;
    public static final int PRINT = 101;
    private Set<String> words;

    public Menu(Set<String> words) {
        this.words = words;
    }

    public void start() {

        MenuChoice choice = menu();
        switch (choice) {
            case ENTER_WORD:
                enterWord();
                break;
            case PRINT:
                printWord();
                break;
            case FILE_WORDS:
                fileWords();
                break;
        }
    }

    public void  enterWord() {

       //Set<String> words = new HashSet<>();
        //Map<Integer,String> words=new HashMap<>();
        String[] strings2;
        String input;
        StringBuilder stringBuilder = new StringBuilder();

        System.out.println("enter words with ,..");
        if ((input = getInputFromUser()) != null)
            stringBuilder.append(new String(input));
        strings2 = stringBuilder.toString().split(",");
        for (String s: strings2)
        {
            if (!words.contains(s)) {
                words.add(s);
                System.out.println(s);
            } else

            {
                System.out.println(s +" "+ "exist");
            }
        }

    }


    public  void printWord() {
        for (String s : words) {
            System.out.println(s);
        }
    }

    public void fileWords() {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        System.out.println("enter path");
        String input = getInputFromUser();
        File fileWords = new File(input);
        char[] chars = new char[50];
        char[] chars1 = new char[50];
        if (!(fileWords.exists() && fileWords.isFile()))
            return;
        try {
            inputStream = new FileInputStream(fileWords);
            int actuallyRead;
            int i = 0;
            while ((actuallyRead = inputStream.read()) != -1) {
                byte b = (byte) actuallyRead;
                char c = (char) b;
                chars[i] = c;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < 255; i++) {
            for (int j = 0; j < chars.length; j++) {
                chars1[i] = chars[j - i];
                String string = new String();
               /* if () {
                }*/
            }
        }

    }

    static MenuChoice menu(){
        while(true) {
            System.out.println("please choose:");
            System.out.println("1. entet word");
            System.out.println("2. print word");
            System.out.println("3. enter file with words");
            System.out.println("type 'exit' at any time to exit this program.");
            String choiceString = getInputFromUser();
            switch (choiceString){
                case "1":
                    return MenuChoice.ENTER_WORD;
                case "2":
                    return MenuChoice.PRINT;
                case "3":
                    return MenuChoice.FILE_WORDS;
                case "exit":
                    exit();
                    break;
            }
        }
    }

    static void exit(){
        System.out.println("bye bye");
        System.exit(0);
    }

    private static String getInputFromUser(){
        //System.out.println("enter word");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}



enum MenuChoice{
    ENTER_WORD, PRINT,FILE_WORDS
}













