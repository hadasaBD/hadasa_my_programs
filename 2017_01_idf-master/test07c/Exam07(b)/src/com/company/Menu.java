package com.company;

import java.io.File;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Rubin on 19/04/2017.
 */
public class Menu {
    Input input;
    Output output;

    public Menu(Input input,Output output) {
        this.input=input;
        this.output=output;
    }

    public void start(){
        CommonWords commonWords =new CommonWords();
        while (true) {
            MenuChoice choice = menu();
            switch (choice) {
                case INSERT_WORDS:
                    insertWords(commonWords);
                    break;
                case GET_ALL_WORDS:
                    getAllWords(commonWords);
                    break;
                case DECRYPT_CAESAR:
                    byte[] fileData= FilesOperations.getFileData(getFileFromUser());
                    PrintInfoCrackListener listener=new PrintInfoCrackListener(new OutputToScreen());
                    findKeyFileQuickly(commonWords,fileData,listener);
                    break;
            }
        }
    }

    public MenuChoice menu(){
        while(true) {
            output.getOutput("please choose:");
            output.getOutput("1. insert words");
            output.getOutput("2. get all words");
            output.getOutput("3. decryption file");
            output.getOutput(" 'exit' to exit this program.");
            String choiceString = input.getInput();
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
                default: output.getOutput("invalid option. try again");
                    break;
            }
        }
    }

    public void exit(){
        output.getOutput("bye bye");
        System.exit(0);
    }

    public void insertWords(CommonWords commonWords){
        output.getOutput("enter words with , after each word");
        String word=input.getInput();
        String[]words=word.split(",");
        if(words.length>0){
            commonWords.insertListWords(words, s -> System.out.println("this word exist:" + s));
        }
    }

    public void getAllWords(CommonWords commonWords){
        Set<String> allWord= commonWords.getWords();
        Collection<String> values=allWord;
        for (String i : values) {
            output.getOutput(i);
        }
    }

    public void findKeyFileQuickly(CommonWords commonWords, byte[] bytesToDecrypt, FindKeyListener listener){
        EncryptionCrackingThread firstThread=new EncryptionCrackingThread(0,128, listener, bytesToDecrypt, commonWords);
        EncryptionCrackingThread secondThread=new EncryptionCrackingThread(129, 255, listener, bytesToDecrypt, commonWords);
        firstThread.start();
        secondThread.start();
        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public File getFileFromUser(){
        output.getOutput("please enter path to file to decryption:");
        String path=input.getInput();
        File file = new File(path);
        while (!(file.exists() && file.canRead())) {
            output.getOutput("is not file or file not exists. please enter path again.");
            getFileFromUser();
        }
        return file;
    }



}
