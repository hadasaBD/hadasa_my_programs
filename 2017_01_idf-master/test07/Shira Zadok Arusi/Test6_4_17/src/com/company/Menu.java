package com.company;

import java.io.*;

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
        int i;


        while (true) {
            output.output("Hello");
            output.output("What do you want to do?");
            output.output("Enter 1 for enter word");
            output.output("Enter 2 for print the all ward");
            output.output("Enter 3 for decrypt file");
            output.output("Enter 0 for exit in any time");
            String choice = input.input();
            switch (choice) {
                case WORDS_FROM_USER:
                  /*  output.output("Enter a word");
                    String newWord=input.input();
                    boolean success= Main.words.add(newWord);
                    if(!success)
                        output.output("Error: the word is exist");
                    else
                        output.output("Good! the word was added successfully!");
                    break;*/
                    String newWord;
                    i = 1;
                    output.output("Enter list of words");
                    String words = input.input();
                    int indexOf = words.indexOf(',');
                    while (indexOf != -1) {
                        newWord = words.substring(0, indexOf);
                        addWord(newWord, i);
                        words = words.substring(indexOf + 1);
                        indexOf = words.indexOf(',');
                        i++;
                    }
                    if (indexOf != words.length()) {
                        addWord(words, i);
                    }
                    break;
                case PRINT_LIST:
                    printList();
                    break;
                case WORDS_FROM_FILE:
                   String path=getFileFromUser();
                   if(path==null)
                       return;
                    InputStream inputStream=null;
                    char[] c=new char[50];
                    i=0;
                    try {
                        inputStream = new FileInputStream(new File(path));
                        int actuallyRead;
                        i = 0;
                        while ((actuallyRead = inputStream.read()) != -1) {
                            c[i] = ((char) actuallyRead);
                            i++;
                        }
                        for (int j = 2; j < 256; j++) {
                            int wordsInFile=0;
                            char decrypt[] = c;
                            for (int k = 0; k < decrypt.length; k++)
                                decrypt[k] = (char) (decrypt[k] - j);
                            String strings = new String(decrypt);
                            for (String word:Main.words) {
                                if(strings.indexOf(word)!=-1)
                                    wordsInFile++;
                            }
                            if(wordsInFile>=3){
                                output.output("The decrypt text is:");
                                output.output(strings);
                                output.output("The key is: "+j);
                            }

                        }



                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
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
    public static void printList(){
        for (String word:Main.words) {
            output.output(word);
        }
    }
    public static void addWord(String word,int i){
        boolean success=Main.words.add(word);
        if(!success)
            output.output("The "+i+" word is exist");
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
                break;
            file = new File(s);
        }
        return s;
    }








}
