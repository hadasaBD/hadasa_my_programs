package com.company;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import static com.company.Menu.words;

/**
 * Created by hackeru on 4/19/2017.
 */
class MenuTest {

    void setUp() {

    }


    void tearDown() {

    }
    //----------------------------------------------
    @Test
    void fileWordsNotValid() {
        Set<String> words = new HashSet<>();
        words.add("my");
        words.add("dd");
        words.add("aa");
        words.add("is");
        words.add("bb");
        String s = "my name is hodaya";
        byte[] bytes = s.getBytes();
        File fileEncrypt = encrypted(bytes);
        String pathEncrypt = fileEncrypt.getPath();
        byte[] bytesEncrypt = LoadToArray(pathEncrypt);
        Menu menu = new Menu(words);

        ThreadDecryption.FoundKeyListener listener = new ThreadDecryption.FoundKeyListener() {

            @Override
            public void foundKey(int key, String text) {

                    Assertions.fail("not found 3 words");
            }
        };


        int max = 256;
        int middle = max / 2;

        ThreadDecryption threadDecryption1 = new ThreadDecryption(1, middle, bytesEncrypt, listener);
        ThreadDecryption threadDecryption2 = new ThreadDecryption(middle + 1, 256, bytesEncrypt, listener);
        threadDecryption1.run();
        threadDecryption2.run();
    }
    @Test
    public void fileWordsgood() {
        Set<String> words = new HashSet<>();
        words.add("aaa");
        words.add("bb");
        words.add("ddd");
        words.add("yy");
        Menu menu = new Menu(words);
        String s = "aaa,bb.yy";
        byte[] bytes = s.getBytes();
        File fileEncrypt = encrypted(bytes);
        String pathEncrypt = fileEncrypt.getPath();
        byte[] bytesFromUser = LoadToArray(pathEncrypt);
        ThreadDecryption.FoundKeyListener listener = new ThreadDecryption.FoundKeyListener() {


            @Override
            public void foundKey(int key, String text) {
                //Assertions.assertEquals(233,key,"key valid");
                /*if(key==233)
                   Assertions.fail("invalid key");*/
               /* byte[] bytesDecrypted=text.getBytes();
                Assertions.assertEquals(233,key,"keyGood");
                Assertions.assertEquals('a',bytesDecrypted[0],"textGood");*/

                if (key != 2 || bytes[0] != 'a') {
                    Assertions.fail("key or text not valid");
                }

            }
        };
        //decryption3(LoadToArray(path));
        //decryption4(LoadToArray(path),listener);

        int max = 256;
        int middle = max / 2;
        ThreadDecryption threadDecryption1 = new ThreadDecryption(1, middle, bytesFromUser, listener);
        ThreadDecryption threadDecryption2 = new ThreadDecryption(middle + 1, 256, bytesFromUser, listener);
        threadDecryption1.run();
        threadDecryption2.run();
       /* try {
            threadDecryption1.join();
            threadDecryption2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public File encrypted(byte[] b) {
        File file = new File("C:\\Users\\hackeru\\Desktop\\test7andexe5-class\\testingEncrypt.txt");
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            byte bWrith;
            for (int i = 0; i < b.length; i++) {
                bWrith = (byte) (b[i] + 2);
                outputStream.write(bWrith);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }



    public byte[] LoadToArray(String path) {
        File file = new File(path);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            int acctualyRead;
            acctualyRead = inputStream.read(buffer);

            if (acctualyRead == file.length())
                return buffer;

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





}

