package com.company;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Description {
    File file;
    private static Set<String> commonWords = new HashSet<>();

    private void insertWord() {
        commonWords.add("the,be,to,of,ans,a,in");
    }


    public Description(File file) {
        this.file = file;
    }

     void getFile() {
        insertWord();
        char[] chars = new char[10000];
        InputStream inputStream = null;
        int oneByte;
        int i = 0;
        try {
            inputStream = new FileInputStream(file);
            //byte[] buffer = new byte[2];
            //מטעין את הקובץ למערך
            while ((oneByte = inputStream.read()) != -1) {
               // char word = ByteBuffer.wrap(buffer).getChar();
                chars[i] = (char) oneByte;
            }
            decryption(chars);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void decryption(char[] chars) {
        char[] word = new char[50];
        int k = 0;
        for (int j = 0; j < 255; j++) {
                for (int i = 0; i < chars.length; i++) {
                int sum = 0;
                if ((chars[i] != ' ') || (chars[i] != '.')) {
                    word[k] = (char) (chars[i] + j);
                    k++;
                } else {
                    for (String word1 : commonWords) {
                        String s = new String(word);
                        if (word1 == s) {
                            sum++;
                            printFile(chars, sum);
                        }
                    }
                    k = 0;
                }
            }
        }
    }

    private void printFile(char[] chars, int sum) {
        if (sum > 3) {
            System.out.println("print the file ");
            for (int i = 0; i < chars.length; i++) {
                System.out.println(chars[i]);
            }
        }

    }

}
