package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;


public class Menu {
    public static final String NEW_COMMON_WORD = "1";
    public static final String ENCRYPTED_FILE = "2";
    public static final String PRINT = "3";
    public static final String EXIT = "4";
    public static boolean flag = true;
    Output myOutput;
    Input myInput;
    File myFile;
    int key;


    public Set<String> commonWordList;// = new HashSet();


    public Menu(Output myOutput, Input myInput) {
        this.myOutput = myOutput;
        this.myInput = myInput;
        this.commonWordList = new HashSet<>();
    }

    public Set<String> getCommonWordList() {
        return commonWordList;
    }

    public void setCommonWordList(Set<String> commonWordList) {
        this.commonWordList = commonWordList;
    }

    public void printMenu() {

        myOutput.output("Press a number from the menu shown:" +
                " \n 1. Insert a new common word \n 2. Enter a path to an encrypted file \n 3. Print common word list \n 4. exit from program");
        String myChoice = myInput.getInput();
        while (flag) {
            switch (myChoice) {
                case NEW_COMMON_WORD:
                    insertCommonWordList();
                    break;
                case ENCRYPTED_FILE:
                    decryptFile();
                    break;
                case PRINT:
                    printWordList();
                    break;
                case EXIT:
                    flag = false;
                    myOutput.output("bye bye.");
                    break;
                default:
                    myOutput.output("error , please try again");
            }
            if (flag)
                printMenu();
        }

    }

    public void insertCommonWordList() {
        myOutput.output("Enter a list of new common words separated by a comma ");
        StringTokenizer str = new StringTokenizer(myInput.getInput(), ",", false);
        while (str.hasMoreTokens())
            insertCommonWord(str.nextToken());
    }

    public void insertCommonWord(String newCommonWord) {
        if (!commonWordList.add(newCommonWord))
            myOutput.output("error, The word " + newCommonWord + " is present");
        if (newCommonWord.contains(" "))
            commonWordList.remove(newCommonWord);
        if (newCommonWord.isEmpty())
            myOutput.output("The input is empty");
    }

    public void decryptFile() {
        myOutput.output("Enter a path to the file");
        String filePathString = myInput.getInput();
        if (checkPath(filePathString)) {
            myFile = new File(filePathString);
            try {
                byte[] fileData = Files.readAllBytes(Paths.get(myFile.getPath()));
                key = decryptKey(fileData, commonWordList);
                myOutput.output("The key is : " + key);
                printWordListFromFile(fileData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public int decryptKey(byte[] fileData, Set<String> commonWordList) {
        int count = 0, i = 0;
        /*String fileString = new String(fileData);
        StringTokenizer wordsFromFile = new StringTokenizer(fileString, ",. ", false);
        while (wordsFromFile.hasMoreTokens()) {
            newSet(wordsFromFile.nextToken());
        }*/
        for (; i < 256; i++) {
            count = 0;
            for (int j = 0; j < fileData.length; j++) {
                fileData[j]++;
            }
            String fileString = new String(fileData);
            for (String setList : commonWordList) {
                if (containValidWord(setList, fileString)) {
                    count++;
                    if (count == 3)
                        return i;
                }
            }
        }

        return -1;
    }
    public boolean containValidWord(String setList , String fileString){
        int indexWord = fileString.indexOf(setList);
        if (indexWord == -1)
            return false;
        if (indexWord == 0) {
            char charAfterIndex = fileString.charAt(indexWord + setList.length());
            if (". ,".indexOf(charAfterIndex) != -1)
                return true;
        }
        if (indexWord != 0 && indexWord + setList.length() < fileString.length()) {
            char charBeforeIndex = fileString.charAt(indexWord - 1);
            char charAfterIndex = fileString.charAt(indexWord + setList.length());
            if (". ,".indexOf(charBeforeIndex) != -1 && ". ,".indexOf(charAfterIndex) != -1) {
                return true;
            }
        }
        if (indexWord + setList.length() == fileString.length()) {
            char charBeforeIndex = fileString.charAt(indexWord - 1);
            if (". ,".indexOf(charBeforeIndex) != -1 )
                return true;
        }

        return false;
    }
    public void printWordListFromFile(byte[] fileData) {
        String s = new String(fileData);
        myOutput.output(s);
    }


    public boolean checkPath(String filePathString) {
        if (!new File(filePathString).exists() || !new File(filePathString).isFile()) {
            return false;
        }
        return true;
    }

    public void printWordList() {
        /*for (int i = 0; i < commonWordList.size(); i++) {
            System.out.println(commonWordList.toArray()[i]);
        }*/
        for (String s : commonWordList) {
            myOutput.outputInLine(s + " ");
        }
        myOutput.output("");
    }


}


