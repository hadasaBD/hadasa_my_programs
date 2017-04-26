package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        WordsList wordsList =new WordsList();
        while (true) {
            MenuChoice choice = menu();
            switch (choice) {
                case INSERT_WORD:
                    insertWords(wordsList);
                    break;
                case GET_ALL_WORDS:
                    getAllWords(wordsList);
                    break;
                case DECRYPT_CAESAR:
                    decryptionFile(wordsList);
                    break;
            }
        }
    }

    static MenuChoice menu(){
        while(true) {
            System.out.println("please choose:");
            System.out.println("1. insert word");
            System.out.println("2. get all words");
            System.out.println("3. decryption file");
            System.out.println(" 'exit' to exit this program.");
            String choiceString = getUserInput();
            switch (choiceString){
                case "1":
                    return MenuChoice.INSERT_WORD;
                case "2":
                    return MenuChoice.GET_ALL_WORDS;
                case "3":
                    return MenuChoice.DECRYPT_CAESAR;
                case "exit":
                    exit();
                    break;
            }
        }
    }

    static String getUserInput(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static void exit(){
        System.out.println("bye bye");
        System.exit(0);
    }

    public static void insertWord(WordsList wordsList){
        System.out.println("enter word to insert");
        String word=getUserInput();
        if(!word.equals("")){
            InsertResult result=wordsList.insert(word);
            switch (result){
                case SUCCESS:{
                    System.out.println("SUCCESS");
                    break;
                }
                case WORD_EXIST:{
                    System.out.println("WORD_EXIST");
                    break;
                }
            }
        }
    }

    public static void insertWords(WordsList wordsList){
        System.out.println("enter words to insert with , after word");
        String word=getUserInput();
        String[]words=word.split(",");
        if(words.length>0){
            Set<String> wordsExist=wordsList.insert2(words);
            if(wordsExist.size()==0)
                System.out.println("SUCCESS");
            else{
                //or listener
                System.out.println("this words already exist, not inserted:");
                Collection<String> values=wordsExist;
                for (String i : values) {
                    System.out.println(i);
                }
            }
        }

    }

    public static void getAllWords(WordsList wordsList){
        Set<String> allWord=wordsList.getWords();
        Collection<String> values=allWord;
        for (String i : values) {
            System.out.println(i);
        }
    }

    public static void decryptionFile(WordsList wordsList){
        System.out.println("please enter path to file to decrypion:");
        String path = getUserInput();
        File fileToDecrypt = new File(path);
        try {
            byte[] fileData = Files.readAllBytes(Paths.get(fileToDecrypt.getPath()));

            byte[] tempData=new byte[fileData.length];
            for (int j = 0; j < fileData.length; j++) {
                tempData[j]=fileData[j];
            }

            for (int i = 1; i < 256 ; i++) {
                for (int j = 0; j < tempData.length; j++) {
                    tempData[j]-= 1;
                }

                String stringFromFile = new String(tempData);

                int counterWords=0;
                Set<String> allWords=wordsList.getWords();
                Collection<String> values=allWords;
                for (String w : values) {
                    int index=stringFromFile.indexOf(w);
                    if(index>=0){
                        int length=w.length();
                        //גלישה
                        if(isCharacterSeparator(stringFromFile.charAt(index-1)) && isCharacterSeparator(stringFromFile.charAt(index+length)))
                            counterWords++;
                    }
                }
                if(counterWords>=3){
                    System.out.println("key: "+i);
                    System.out.println(stringFromFile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     public static boolean isCharacterSeparator(char c){
        if(c==' ' || c=='.' || c==',')
            return true;
        return false;
     }
}

/*                while (k < tempData.length) {
                    if(tempData[k]==' '){
                        String s=stringBuilder.toString();

                        stringBuilder.delete(0,stringBuilder.length()-1);
                    }
                    else{
                        stringBuilder.append(tempData[k]);
                        k++;
                    }
                }*/

