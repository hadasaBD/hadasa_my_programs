package com.company;
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.util.HashSet;
 import java.util.Set;


public class Menu {

    private static Set<String> words = new HashSet<>();
    public static void menu() {
        System.out.println("please enter your choice \n 1: Enter a word \n 2: Print to the screen the words \n 3: Enter a path\n 4: Exit  ");
        String choice  = Menu.getInputFromUser();
        switch (choice) {
            case "1":
                EnterWord();
                break;
            case "2":
                printAllWords();
                break;
            case "3":
                enterPathToTheCaesarFile();
            case "exit":
                System.out.println("Bye Bye...");
                return ;
            default:
                System.out.println("invalid choice. please try again");
                menu();


        }
    }


    private static void EnterWord() {
        System.out.println("Please enter a word ");
        String word = Menu.getInputFromUser();
        String[] strings = word.split(",");
        for (int i = 0; i <strings.length ; i++) {
            if(!words.contains(strings[i])){
                words.add(strings[i]);}
            else {
                System.out.println("the word:" + strings[i] +" already exist ");
            }
        }
        menu();
    }

    private static void printAllWords() {
        for (String word : words) {
            System.out.println(word);
        }
        menu();
    }



    private static void enterPathToTheCaesarFile() {
        boolean illegal = true;
        while (illegal) {
            System.out.println("Enter a path ");
            String path = Menu.getInputFromUser();
            File file = new File(path);
            if (file.exists()&&file.isFile()==false) {
                System.out.println("The path is illegal ");
            }
            else {
                illegal = false;
                Cipher cipher = new Cipher(file);
                cipher.HandleWithFile();
            }
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



