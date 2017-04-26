package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Menu {
    HashSet<String> set;
    public static final int length = 100;

    public Menu() {
        this.set = new HashSet<>();
    }

    public String input() {
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

    public void output(String s) {
        System.out.println(s);
    }

    public void printMenu(){
        output("please choose:\n" +
                "1. enter words\n" +
                "2. print words\n" +
                "3. enter path\n" +
                "4. exit\n" +
                "your choice:");
    }
    public boolean add(String s){
        return set.add(s);
    }
    /* הפונקציה לתרגיל 1
    public void checkWords(){
        boolean rightWord = false;
        while (!rightWord) {
            output("enter  a word");
            String word = input();
            if (word.equals("0"))
                return;
            if (!add(word))
                output("exist word, enter again or 0 to menu");
            else
                rightWord = true;
        }
    }*/

    public void checkWords(){
        output("enter  a words");
        String word = input();
        String[] arr = word.split(",");
        for (int i = 0; i < arr.length ; i++) {
            if (!add(arr[i]))
                output(arr[i] + " exist word.");
        }
    }

    public void printWords(){
        for (String var: set) {
            output(var);
        }
    }

    public String getPath(){
        output("enter path:");
        String input = input();
        File file = new File(input);
        if ((file.exists()) && (file.isFile()))
            return input;
        return null;
    }

    public void startMenu(){
        boolean continueProgram = true;
        while (continueProgram) {
            printMenu();
            String input = input();
            switch (input) {
                case "1":
                    checkWords();
                    break;
                case "2":
                    printWords();
                    break;
                case "3":
                    break;
                case "4": decryption();
                    continueProgram = false;
                    break;
                default: output("Key does not exist");
            }
        }
    }
    public void decryption(){
        String path = getPath();
        if (path == null)
            return;
       caesarDecrypt( uploadFile(path));
    }
    public char[] uploadFile(String file) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] buffer = new byte[length];
            int actuallyRead = inputStream.read(buffer);

            char[] text = new char[actuallyRead];
            for (int i = 0; i < text.length; i++) {
                text[i] = (char) buffer[i];
            }
            return text;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        return null;
    }
    public void caesarDecrypt(char[] text){
        char[] temp = new char[text.length];
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < temp.length; j++) {
                temp[j] = (char)(i+text[i]);
            }
            String string = new String(temp);
            if (search(string)){
                output("The Key: " + i);
                output(string);
            }
        }
    }
    public boolean search( String text){

        HashSet<String> words = new HashSet<>();
        for (String var: set) {
            if (text.indexOf(var) != -1){
                words.add(var);
                if (words.size() == 3)
                    return true;

            }
        }
        return false;

    }



}
