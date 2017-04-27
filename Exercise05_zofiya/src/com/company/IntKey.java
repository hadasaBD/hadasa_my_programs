package com.company;

import java.io.*;
import java.util.Random;

/**
 * Created by hackeru on 3/22/2017.
 */
public class IntKey implements Key {
    Output myOutput;

    static Integer getKey(String inputAlgorithm) {
        Output myOutput=null;
        Random random = new Random(System.currentTimeMillis());
        Integer key = random.nextInt(255);
        if (Integer.parseInt(inputAlgorithm) == 3 || Integer.parseInt(inputAlgorithm) == 0) {
            if (key % 2 == 0)
                key = key + 1;
        }

        File fileKey = new File("Z:\\zofiya\\Exercisess\\Exercises\\key.bin");
        ObjectOutputStream objectOutputStream=null;
        OutputStream outputStream=null;
        try {

            outputStream=new FileOutputStream(fileKey);
            objectOutputStream= new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(key);

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(objectOutputStream!=null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }if(outputStream!=null){
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return key;
    }
}