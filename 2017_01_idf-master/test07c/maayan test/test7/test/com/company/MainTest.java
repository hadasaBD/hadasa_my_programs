package com.company;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 4/20/2017.
 */
class MainTest {
    static final  String PATH="C:\\Users\\HACKERU.HACKERU3\\Desktop\\test\\test.txt";
    static final  String PATH_ENCRYPT="C:\\Users\\HACKERU.HACKERU3\\Desktop\\test\\testencrypt.txt";
    byte key;
    boolean find;
    private String charsFile="and afg to a hfgh of @a";
    private Set<String> words;
    private File file;
    private String charsFileEncrypt;
    private File encryptFile;
    @Test
    void main() {
        fillWords();
        createFile();
        DecryptionHack.MatchFound matchFound = new DecryptionHack.MatchFound() {
            @Override
            public void found(byte keyFound, String file) {
                if (key == keyFound)
                    find = true;
            }
        };
        for (byte i = 0; i < Byte.MAX_VALUE; i++) {
            key=i;
            find=false;
            encryptFile();
            TreadDecryptionT4 decryption = new TreadDecryptionT4((byte) 0, (byte) (Byte.MAX_VALUE/2),charsFileEncrypt, words, matchFound);
            TreadDecryptionT4 decryption2 = new TreadDecryptionT4((byte) (Byte.MAX_VALUE/2), Byte.MAX_VALUE,charsFileEncrypt, words, matchFound);
            decryption.start();
            decryption2.start();
            try {
                decryption.join();
                decryption2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!find)
                fail("not found key"  +i);

        }
    }

    private void encryptFile() {
        encryptFile = new File(PATH_ENCRYPT);
        OutputStream outputStream = null;
        byte bytes[]=new byte[charsFile.length()];
        StringBuilder stringBuilder=new StringBuilder();
        try {
            outputStream=new FileOutputStream(encryptFile);
            for (int i = 0; i < charsFile.length(); i++) {
                byte temp=(byte)(charsFile.charAt(i)+key);
                outputStream.write(temp);
                bytes[i]= temp;
                stringBuilder.append((char)temp);
            }
            charsFileEncrypt=new String(stringBuilder.toString());
            //charsFileEncrypt=new String(bytes);
            //ניסתי לעשות עם מערך של byte זה לא עובד

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }



    private void createFile() {
        file = new File(PATH);
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write(charsFile.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


    private void fillWords() {
        words = new HashSet<>();
        words.add("and");
        words.add("a");
        words.add("to");
        words.add("the");
    }
    @Test
    void isDecrypt(){
        createFile();
        encryptFile();
        TreadDecryptionT4 decryption = new TreadDecryptionT4((byte) 0, (byte) (Byte.MAX_VALUE/2),charsFileEncrypt, words, null);
        String r=decryption.decryptFile(key);
        if(!r.equals(charsFile))
           fail("not decrypt");
    }

}