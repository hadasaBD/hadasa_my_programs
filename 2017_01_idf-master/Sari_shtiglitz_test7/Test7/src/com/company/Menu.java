package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Menu implements Listener {
    public static Set<String> listOfWords = new HashSet<>();

    public Menu(Listener listener) {
        this.listener = listener;
    }

    public Listener listener;
    int index = 0;

    public Menu() {
    }

    public void printMenu() {
        OutPut forOutPut = new OutPut();
        Input forInput = new Input();

        System.out.println();
        System.out.println("please choose:");
        System.out.println("1. enter word");
        System.out.println("2. print all the words");
        System.out.println("3. enter string:");
        System.out.println("0. exit");
        System.out.println("your choice: ");
        String input = readInput();
        if (input.length() != 0) {
            switch (input) {
                case "1":
                    enterWordFuncFromUser();
                    break;
                case "2":
                    printAllTheWord();
                    break;
                case "3":
                    enterStringFuncFromUser();
                    break;
                case "4":
                    forOutPut.output("please enter path of file");
                    String path = forInput.input();
                    Decryption decryption=new Decryption(path);
                    File file1 = decryption.getPathFromInput(path , listener);
                    break;
                case "0":
                    System.out.println("bye bye.");
                    return;
                default:
                    System.out.println("invalid option. try again.");
            }
        }
        printMenu();
    }

    private void enterStringFuncFromUser() {

        char[] chars;
        char[] chars2;
        int k = 0;
        int m = 0;
        int i = 0;
        OutPut forOutPut = new OutPut();
        Input forInput = new Input();
        forOutPut.output("please enter string with ',' after evert word");
        String wordArr = forInput.input();
        int lengthArr = wordArr.length();
        chars = new char[lengthArr];
        chars2 = new char[chars.length - i];
        chars = wordArr.toCharArray();
        for (i = i; i < chars.length; i++) {
            if (chars[i] != ',') {
                chars2[k++] = chars[i];
                continue;
            } else {
                k = 0;

                String tempWord = String.valueOf(chars2);
                insertToList(tempWord);
                chars2 = new char[chars.length - i];
                continue;
            }
        }
        String tempWord = String.valueOf(chars2);
        insertToList(tempWord);

    }


    private void printAllTheWord() {
        OutPut forOutPut = new OutPut();
        Input forInput = new Input();

        for (String t : listOfWords)
            forOutPut.output(t);


    }


    public static String readInput() {
        String input = null;
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    public void enterWordFuncFromUser() {
        OutPut forOutPut = new OutPut();
        Input forInput = new Input();
        forOutPut.output("please enter word");
        String word = forInput.input();
        insertToList(word);
    }

    public void insertToList(String s) {
        OutPut forOutPut = new OutPut();
        Input forInput = new Input();

        boolean flag = true;
        flag = listOfWords.add(s);
        if (!flag) {
            forOutPut.output("the word already exist in this list...please change the word");
            enterWordFuncFromUser();

        }


    }


    @Override
    public void printFileSuspect(File file, int key) {
        File tempFile = new File("/Users//Desktop/temp.txt");
        OutputStream outputStream = null;
        InputStream inputStream = null;
        byte[] buffer = new byte[1024];
        int actuallyRead = 0;
        try {
            actuallyRead = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        char[] charArray = new char[actuallyRead];
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) buffer[i];
        }
        for (int i = 0; i < charArray.length; i++) {
            System.out.println(charArray[i]);
        }
        System.out.println(key);
    }
}
