package com.company;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by TOSHIBA on 16/04/17.
 */
public class Menu {

    public static  Set<String> words;

    public Menu() {
        this.words = new HashSet<>();
    }

    public void startMenu()
    {
        while (true) {
            MenuChoice Choice = printMenu();
            switch (Choice) {
                case ENTER_WORD:
                    enterWords();
                    break;
                case PRINT:
                    printWords();
                    break;
                case FILE_WORDS:
                    fileWords();
                    break;
            }
        }

    }

    public void fileWords() {
        String path=checkpath();
        if(path==null)
            return;
        //decryption3(LoadToArray(path));
        decryption4(LoadToArray(path));
    }

    private void decryption3(byte[] chars) {

        byte[] temp = new byte[chars.length];
        for (int i = 0; i <chars.length ; i++) {
            temp[i]=chars[i];
        }
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < temp.length; j++) {
                temp[j] = (byte) (temp[j] - 1);
            }
            int count = 0;
            String text = new String(temp);

            for (String word : words) {
                if (contain(word, text)) {
                    count++;
                    if (count == 3) {
                        System.out.println("the key:" + i+1);
                        System.out.println(text);

                    }
                }
            }


        }
    }


    public boolean contain(String word,String text)
    {
        int index=text.indexOf(word);
        if(index==-1)
            return false;
        if(index!=0)
        {
            char beforeIndex=text.charAt(index-1);
            if (beforeIndex!='.' &&beforeIndex!=' ' && beforeIndex!=',')
                return false;
        }
        return true;

    }

    ThreadDecryption.FoundKeyListener listener = new ThreadDecryption.FoundKeyListener(){


        @Override
        public void foundKey(int key, String text) {
            System.out.println("the key:" + key);
            System.out.println(text);
        }
    };

    public void decryption4(byte[] chars)
    {
        int max=256;
        int middle=max/2;
        for (int i = 1; i <=128 ; i++) {
            ThreadDecryption threadDecryption1=new ThreadDecryption(i,chars,listener);
            ThreadDecryption threadDecryption2=new ThreadDecryption(middle+i,chars,listener);
            threadDecryption1.start();
            threadDecryption2.start();
        }


    }




    private byte[] LoadToArray(String path) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
            byte[] buffer = new byte[100];
            int acctualyRead;
            acctualyRead = inputStream.read(buffer);

            byte[] text = new byte[acctualyRead];
            for (int i = 0; i < text.length; i++) {
                text[i] = buffer[i];
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

    public String checkpath() {
        System.out.println("enter path");
        String path = inputFromUser();
        File file = new File(path);
        if (file.exists() && file.isFile())
            return path;
        return null;
    }




    public MenuChoice printMenu() {

        System.out.println("please choose:");
        System.out.println("1. entet words");
        System.out.println("2. print word");
        System.out.println("3. enter file with words");
        System.out.println("type 'exit' at any time to exit this program.");
        String input = inputFromUser();
        switch (input) {
            case "1":
                return MenuChoice.ENTER_WORD;
            case "2":
                return MenuChoice.PRINT;
            case "3":
                return MenuChoice.FILE_WORDS;
            case "exit":
                exit();
                break;
        }
        return null;
    }

    public void exit() {
        System.out.println("bye bye...");
        System.exit(0);
    }

    public void printWords() {

        for (String s:words) {
            System.out.println(s);
        }
    }

    public void enterWord() {
        System.out.println("enter word");
        String word = inputFromUser();
        if (!word.equals("")) {
            ResultAddWords result = add(word);
            switch (result) {
                case SUCCESS:
                    System.out.println("SUCCESS");
                    break;
                case WORD_EXIST:
                    System.out.println("WORD_EXIST TRY AGAIN");
                    break;


            }
        }
    }

    public ResultAddWords add(String word) {
        if (words.contains(word))
            return ResultAddWords.WORD_EXIST;
        words.add(word);
        return ResultAddWords.SUCCESS;
    }


    public void enterWords() {

        String[] arr;
        System.out.println("Tap words with , between them");
        String input=inputFromUser();
        arr=input.split(",");
        for (int i = 0; i <arr.length ; i++) {
            if(words.contains(arr[i]))
                System.out.println(arr[i]+ " "+ "exist");
            words.add(arr[i]);

        }

    }



    public String inputFromUser()
    {

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


}

enum MenuChoice{
    ENTER_WORD, PRINT,FILE_WORDS
}

enum ResultAddWords{
    SUCCESS,WORD_EXIST
}





