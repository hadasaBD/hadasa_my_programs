package com.company;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;

import java.util.Set;

import static com.company.Menu.Input;

/**
 * Created by hackeru on 4/19/2017.
 */
public class Breakthrough {
    public Set<String> words = new HashSet<>();

    public Breakthrough(Set<String> words) {
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

    public String hack(byte[] fromFile) {

        boolean isfound = false;
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < fromFile.length; j++) {
                fromFile[j]++;
            }
            String text = new String(fromFile);
            if (testIsDecoded(text)) {
                isfound=true;
                found(i, text);
                return text;
            }
        }
        if (isfound == false)
            System.out.println("No code found");
        return "No code found";
    }


    public void found(int key, String text) {
        System.out.println("The decoded ceremony: ");
        System.out.println(text);
        System.out.println("And its code:" + key);

    }

    public boolean testIsDecoded(String text ) {

        Set<String> word = new HashSet<>();
        for (String var : words) {
            int index = text.indexOf(var);
            char charBefore = ' ';
            if (index != -1) {
                if (index != 0)
                    charBefore = text.charAt(index - 1);
                if ((index == 0) || (charBefore == ' ') || (charBefore == ',') || (charBefore == '.')) {
                    index += var.length();
                    if (index != text.length())
                        charBefore = text.charAt(index);
                    if ((index == text.length()) || (charBefore == ' ') || (charBefore == ',') || (charBefore == '.'))
                        word.add(var);
                }
                if (word.size() == 3)
                    return true;

            }
        }
        return false;

    }
}
