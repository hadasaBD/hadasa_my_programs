package com.company;

import InputAndOutput.Input;
import InputAndOutput.Output;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Menu {

    CollectionHandler collectionHandler;
    Input input;
    Output output;

    public Menu(Input input, Output output) {
        this.input = input;
        this.output = output;
        this.collectionHandler = new CollectionHandler();
    }


    public void startMenu() {
        boolean continueProgram = true;
        while (continueProgram) {
            printMenu();
            String input_user = input.input();
            switch (input_user) {
                case "1":
                    checkWords();
                    break;
                case "2":
                    printWords();
                    break;
                case "3":
                    decryption();
                    break;
                case "4":
                    continueProgram = false;
                    break;
                default:
                    output.output("Key does not exist");
            }
        }
    }

    public void printMenu() {
        output.output("please choose:\n" +
                "1. enter words\n" +
                "2. print words\n" +
                "3. enter path\n" +
                "4. exit\n" +
                "your choice:");
    }

    public void checkWords() {
        output.output("enter  a words");
        String word = input.input();
        String[] arr = word.split(",");
        for (int i = 0; i < arr.length; i++) {
            if (!collectionHandler.add(arr[i]))
                output.output(arr[i] + " exist word.");
        }
    }

    public void printWords() {
        for (String var : collectionHandler.getSet()) {
            output.output(var);
        }
    }

    public String getPath() {
        output.output("enter path:");
        String input_user = input.input();
        File file = new File(input_user);
        if ((file.exists()) && (file.isFile()))
            return input_user;
        return null;
    }


    public void decryption() {
        String path = getPath();
        if (path == null)
            return;
        FindKeyThread.KeyFoundListener listener = new FindKeyThread.KeyFoundListener() {
            @Override
            public void keyFound(int key, String fileContent) {
                output.output("The Key: " + key + "\n" + fileContent);

            }
        };
        collectionHandler.caesarEncrypt(collectionHandler.uploadFile(path), listener);
    }

}
