package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 4/19/2017.
 */
class MyThreadTest {

    String word="sari";

    byte[] arr=word.getBytes();



    public static Set<String> listOfWords = new HashSet<>();

    public static Set<String> getListOfWords() {
        return listOfWords;
    }
    private MyThread.Listener listener;



    @Test
    void MyRun() {
        /*Random random=new Random(System.currentTimeMillis());
        int key=random.nextInt();*/
        int key=2;
        MyThread myThread=new MyThread(arr,listOfWords,0,256,listener);

        for (int i = 0; i < arr.length; i++) {
            arr[i]+=key;
        }
        myThread.start();

        if(listener==null)
            Assertions.fail("not valid");


   }

    }

