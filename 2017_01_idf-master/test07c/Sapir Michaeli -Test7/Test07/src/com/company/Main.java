package com.company;

import java.io.*;
import java.util.Set;


public class Main {

    public static final String MAIN = "please choose:\n 1. add word \n 2. print the words\n 3. decode \n type 4 at any point to exit this program";
    private static final String ADD_WORD = "1";
    private static final String PRINT_WORDS = "2";
    private static final String EXIT = "0";
    private static final String DECODE = "3";
    private static IO userIO = new UserIO();
    private static MyDictionary myDictionary = new MyDictionary();


    public static void main(String[] args) {
        createEncryptedFile((byte) 20);
        menu();
    }

    //encrypt the file- for testing
    public static void createEncryptedFile(byte key){
        File file=new File("C:\\Users\\HACKERU.HACKERU3\\Desktop\\1.txt");
        String s="and a the rhjerh to wgdgfFREYWRYN ,,, ";
        OutputStream outputStream=null;
        try {
            outputStream =new FileOutputStream(file);
            for (int i = 0; i <s.length() ; i++) {
                outputStream.write(s.charAt(i)+key);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void menu() {
        userIO.output(MAIN);
        String action;
        while (!(action = userIO.input()).equals("4")) {
            switch (action) {
                case ADD_WORD:
                    addWord();
                    userIO.output(MAIN);
                    break;

                case PRINT_WORDS:
                    printWords();
                    userIO.output(MAIN);
                    break;

                case DECODE:
                    try {
                        boolean valid = false;
                        File file = null;
                        while (!valid) {
                            userIO.output("enter path");
                            String path = userIO.input();
                            file = new File(path);
                            if (!file.exists() && file.isFile()) {
                                userIO.output("error input");
                                valid = false;
                            }
                            valid = true;
                        }
                        HackListener listener = new HackListener();
                        byte[] charsFile=copyFileToArrByte(file);
                        Hack first = new Hack(charsFile, 0, Byte.MAX_VALUE / 2, listener,myDictionary);
                        Hack second = new Hack(charsFile, (Byte.MAX_VALUE / 2) + 1, Byte.MAX_VALUE, listener,myDictionary);
                        first.start();
                        second.start();

                        try {
                            first.join();
                            second.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (listener.decryptText != null) {
                            userIO.output("key " + listener.actualKey);
                            userIO.output("Decrypted file:");
                            userIO.output(listener.decryptText); // ידפיס את ההצפנה
                        } else userIO.output("Couldn't crack the code");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case EXIT:
                    break;
                default:
                    System.out.println("It is not a correct choice");
            }
        }
    }


    private static void addWord() {
        userIO.output("Enter a word");
        myDictionary.addWord(userIO.input());
        userIO.output("Would you like to add more words?  \n press YES Or NO");
        String answer;
        while (!(answer = userIO.input()).equals("YES") && !answer.equals("NO")) {
            userIO.output("Please answer YES or NO");
        }
        if (answer.equals("YES")) {
            userIO.output("Enter words separated by ,  \n To finish press enter");
            myDictionary.addWord(userIO.input());

        }
    }

    private static void printWords() {
        userIO.output("List of words:");
        Set<String> tempDico = myDictionary.getWords();
        for (String word : tempDico)
            userIO.output(word);
    }
    private static byte[] copyFileToArrByte(File file){
        byte [] bytes=null;
        if(file.length()<=Integer.MAX_VALUE){
            bytes=new byte[(int) file.length()];
            InputStream inputStream=null;
            int a,counter=0;
            try {
                inputStream =new FileInputStream(file);
                while ((a=inputStream.read())!=-1){
                    bytes[counter++]= (byte) a;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

}