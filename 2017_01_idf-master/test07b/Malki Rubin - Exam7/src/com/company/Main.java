package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Set;

public class Main {

    public static final Input INPUT = new InputFromScreen();
    public static final Output OUTPUT = new OutputToScreen();

    public static void main(String[] args) {
        WordsList wordsList =new WordsList();
        while (true) {
            MenuChoice choice = menu();
            switch (choice) {
                case INSERT_WORDS:
                    insertWords(wordsList, INPUT);
                    break;
                case GET_ALL_WORDS:
                    getAllWords(wordsList);
                    break;
                case DECRYPT_CAESAR:
                    byte[] fileData= FilesOperations.getFileData(getFileFromUser());
                    findKeyFileQuickly(wordsList,fileData);
                    break;
            }
        }
    }

    static MenuChoice menu(){
        while(true) {
            OUTPUT.getOutput("please choose:");
            OUTPUT.getOutput("1. insert words");
            OUTPUT.getOutput("2. get all words");
            OUTPUT.getOutput("3. decryption file");
            OUTPUT.getOutput(" 'exit' to exit this program.");
            String choiceString = INPUT.getInput();
            switch (choiceString){
                case "1":
                    return MenuChoice.INSERT_WORDS;
                case "2":
                    return MenuChoice.GET_ALL_WORDS;
                case "3":
                    return MenuChoice.DECRYPT_CAESAR;
                case "exit":
                    exit();
                    break;
                default: OUTPUT.getOutput("invalid option. try again");
                break;
            }
        }
    }

    static void exit(){
        System.out.println("bye bye");
        System.exit(0);
    }

    public static void insertWords(WordsList wordsList, Input input){
        System.out.println("enter words with , after each word");
        String word=input.getInput();
        String[]words=word.split(",");
        if(words.length>0){
            wordsList.insertListWords(words, s -> System.out.println("this word exist:" + s));
        }
    }

    public static void getAllWords(WordsList wordsList){
        Set<String> allWord=wordsList.getWords();
        Collection<String> values=allWord;
        for (String i : values) {
            System.out.println(i);
        }
    }

    public static void findKeyFileQuickly(WordsList wordsList, byte[] bytesToDecrypt){

        FindKeyListener listener= new FindKeyListener() {
            @Override
            public void findKey(int key,String data) {
                System.out.println("key: "+key);
                System.out.print("text: "+data);
            }
        };

        EncryptionCrackingThread firstThread=new EncryptionCrackingThread(0,128, listener, bytesToDecrypt, wordsList);
        EncryptionCrackingThread secondThread=new EncryptionCrackingThread(129, 255, listener, bytesToDecrypt, wordsList);
        firstThread.start();
        secondThread.start();
        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static File getFileFromUser(){
        OUTPUT.getOutput("please enter path to file to decryption:");
        String path=INPUT.getInput();
        File file = new File(path);
        while (!(file.exists() && file.canRead())) {
            OUTPUT.getOutput("is not file or file not exists. please enter path again.");
            getFileFromUser();
        }
        return file;
    }
}




