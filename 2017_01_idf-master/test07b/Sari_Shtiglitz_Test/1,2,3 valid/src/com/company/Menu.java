package com.company;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Menu  {
    public static Set<String> listOfWords = new HashSet<>();

    /*
        public Menu(DecryptionCaesar.Listener listener) {
            this.listener = listener;
        }

       public DecryptionCaesar.Listener listener;*/
    int index = 0;



    public void printMenu() {
        OutPut forOutPut = new OutPut();
        Input forInput = new Input();

        System.out.println();
        System.out.println("please choose:");
        System.out.println("1. enter word");
        System.out.println("2. print all the words");
        System.out.println("3. enter string:");
        System.out.println("4. decrypted file:");
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
                    forOutPut.output("please enter string with ',' after evert word");
                    String wordArr = forInput.input();
                    enterStringFuncFromUser(wordArr);
                    break;
                case "4":
                    forOutPut.output("please enter path of file");
                    String path = forInput.input();
                    DecryptionCaesar decryption=new DecryptionCaesar(path);
                    File file1 = decryption.getPathFromInput(path);
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

    public void enterStringFuncFromUser(String wordArr){
        char[] chars;
        char[] chars2;
        int k = 0;
        int m = 0;
        int i = 0;
        OutPut forOutPut = new OutPut();
        Input forInput = new Input();

        int lengthArr = wordArr.length();
        chars = new char[lengthArr];
        chars2 = new char[chars.length - i];
        chars = wordArr.toCharArray();
        for (i = i; i < chars.length; i++) {
            if (chars[i] != ',') {
                chars2[k++] = chars[i];
                continue;
            } else {

                char[]temp = new char[k];
                for (int j = 0; j < temp.length; j++) {
                    temp[j]=chars2[j];
                }
                String tempWord = String.valueOf(temp);
                insertToList(tempWord);
                chars2 = new char[chars.length - i];
                k=0;
                continue;
            }
        }
        char[]temp = new char[k];
        for (int j = 0; j < temp.length; j++) {
            temp[j]=chars2[j];
        }
        String tempWord = String.valueOf(temp);
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



}