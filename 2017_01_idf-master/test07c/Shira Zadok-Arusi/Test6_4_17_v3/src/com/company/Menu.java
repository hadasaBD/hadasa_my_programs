package com.company;

import java.io.*;
import java.util.Set;


/**
 * Created by hackeru on 4/6/2017.
 */
public class Menu {
    public static final String WORDS_FROM_USER = "1";
    public static final String PRINT_LIST = "2";
    public static final String EXIT = "0";
    public static final String WORDS_FROM_FILE = "3";
    static Output output=new UserOutput();
    static Input input=new UserInput();


    public static void start(){
        //int i;
        MyWords myWords=new MyWords();
        while (true) {
            output.output("Hello");
            output.output("What do you want to do?");
            output.output("Enter 1 for enter word");
            output.output("Enter 2 for print the all word");
            output.output("Enter 3 for decrypt file");
            output.output("Enter 0 for exit in any time");
            String choice = input.input();
            switch (choice) {
                case WORDS_FROM_USER:
                    boolean[] isNotExist;
                    output.output("Enter list of words");
                    String words = input.input();
                    isNotExist=myWords.addWords(words);
                    for (int j=0;j<isNotExist.length;j++) {
                        if (!isNotExist[j])
                            output.output("The "+(j+1)+" word is exist");
                    }
                    break;

                case PRINT_LIST:
                    printList(myWords.getWords());
                    break;

                case WORDS_FROM_FILE:
                    String path=getFileFromUser();
                    if(path==null)
                        return;
                    byte[] bytes= FileToBytesArray(path);
                    FindWordsThread.WordsFoundListener listener=new FindWordsThread.WordsFoundListener() {
                        boolean firstResponse = true;

                        @Override
                        public void keyIsNotFound() {

                            if (!firstResponse) {
                                output.output("There isn't correct key");
                            }
                            else
                                firstResponse=false;
                        }

                        @Override
                        public void keyIsFound(int key, String string) {
                            output.output("The decrypt text is: "+string);
                            output.output("The key is: "+key);
                        }
                    };

                    FindWordsThread findWordsThread1=new FindWordsThread(bytes,myWords,listener,0,128);
                    FindWordsThread findWordsThread2=new FindWordsThread(bytes,myWords,listener,128,256);
                    findWordsThread1.start();
                    findWordsThread2.start();

                    try {
                        findWordsThread1.join();
                        findWordsThread2.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }



                    break;
                case EXIT:
                    output.output("bye bye");
                    return;
                default:
                    output.output("Your choice is incorrect please try again");
            }
        }



    }

    private static byte[] FileToBytesArray(String path) {
        InputStream inputStream = null;
        byte[] bytes = new byte[50];
        int i = 0;
        try {
            inputStream = new FileInputStream(new File(path));
            int actuallyRead;

            while ((actuallyRead = inputStream.read()) != -1) {
                bytes[i] = ((byte) actuallyRead);
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] newBytes = new byte[i];
        for (int j = 0; j <i ; j++) {
            newBytes[j]=bytes[j];
        }
        return newBytes;
    }

    public static void printList(Set<String> words){
        for (String word:words) {
            output.output(word);
        }
    }

    public static String getFileFromUser() {
        String s=null;
        output.output("Enter a path of file");
        s = input.input();
        File file = new File(s);
        while (!file.exists()||!file.isFile()) {
            output.output("The file isn't exist, enter a correct path again or enter 0 for exit");
            s = input.input();
            if(s.equals("0"))
                return null;
            file = new File(s);
        }
        return s;
    }








}