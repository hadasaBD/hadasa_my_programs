package com.company;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import static com.company.Menu.words;

/**
 * Created by hackeru on 4/19/2017.
 */
class MenuTest {
    Set<String> words=new HashSet<>();
    void setUp() {

    }


    void tearDown() {

    }

    @Test
    void checkpathNotGood() {

        File file=new File("C:\\Users\\hackeru\\Desktop\\test7andexe5\\test.encrypted.txt");
        String path1=file.getPath();
        String path2="";
        Menu menu=new Menu();
        if(!(path1==menu.checkpath()))
            Assertions.fail("error");

    }


    void printWords() {


    }

    @Test
    void enterWords() {

        Menu menu=new Menu();
        menu.enterWords();

        Set<String> words2=new HashSet<>();
        String[] arr = {"aaa", "aaa", "b", "c" };


        for (int i = 0; i < arr.length; i++) {
            if (!words.contains(arr[i]))
                words.add(arr[i]);
            if(words.size()==4)
            Assertions.fail("nooo");

        }

    }
   @Test
    void decryptTest(){

        ThreadDecryption threadDecryption;
       Menu menu=new Menu();
      String s="aa.bb,cccc.dd";
      byte[] arr1=s.getBytes();
      byte[] arr2=new byte[arr1.length];
       for (int i = 0; i <arr1.length ; i++) {
           arr2[i]= (byte) (arr1[i]+1);
       }
       menu.decryption4(arr2);

    }





}

