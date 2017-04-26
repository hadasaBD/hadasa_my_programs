package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.company.Menu.Input;

/**
 * Created by hackeru on 4/19/2017.
 */
public class Breakthrough {
    public Map<String,Integer> words= new HashMap<>();

    public Breakthrough(Map<String, Integer> words) {
        this.words = words;
    }

    public void getFileFromUser() {
        System.out.println("enter path");
        String path = Input();
        File filePath = null;
        try {
            if (!new File(path).exists() || !(new File(path).isFile())) {
                System.out.println("File not found");
                return;
            } else
                filePath = new File(path);
            byte[] fileData = Files.readAllBytes(Paths.get(filePath.getPath()));
            hack(fileData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void hack (byte[] fromFile){
        Set allTheWords=words.keySet();
        boolean isfound=false;
        for (int i = 0; i <256 ; i++) {
            for (int j = 0; j <fromFile.length ; j++) {
                fromFile[j]++;
            }
            int count=0;
            String text=new String(fromFile);
            for (Object word: allTheWords) {
                if (includeWord((String) word,text)) {
                    count++;
                    if (count==3) {
                        isfound=true;
                        found(i, text);
                    }
                }
            }
        }
        if(isfound==false)
            System.out.println("No code found");
    }

    public static boolean includeWord(String word, String text){
        int index=text.indexOf(word);
        if (index==-1)
            return false;
        if (index!=0 ) {
            char charBefore = text.charAt(index - 1);
            if (charBefore!='.' &&charBefore!=' ' && charBefore!=',')
                return false;
        }
        return true;
    }

    public void found(int key, String text){
        System.out.println("The decoded ceremony: ");
        System.out.println(text);
        System.out.println("And its code:"+ key);

    }

    /*public String Input() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {

        }
        return "error";
    }*/
}
