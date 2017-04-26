package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 4/19/2017.
 */
class DecryptionFromFileTest {
    public static final int SIZE = 1000;
    public Map<String,Integer> map = new HashMap<>();
    static int indexTest=0;
    static boolean ifFound=false;


    @Test
    void checkIfDecryption(){
        map.put("good",1);
        map.put("in",1);
        map.put("the",1);
        byte[] stringCheck = new String("abc,to good.in,good abc").getBytes();
        byte[] bytesFrom = new byte[SIZE];
        byte[] bytesTo = new byte[SIZE];
        byte num=56;
        for (int i = 0; i < stringCheck.length; i++) {
            bytesTo[i]=stringCheck[i];
            bytesFrom[i]= (byte) (bytesTo[i]+num);
        }
        checkIfDecryptionTogether(bytesFrom,bytesTo);
    }

    @Test
    void checkIfDecryptionFromFile() throws InterruptedException {
        map.put("good",1);
        map.put("in",1);
        map.put("the",1);
        fromFile();
    }

    @Test
    void checkIfDecryptionFromFileEmpty() throws InterruptedException {
        fromFile();
    }

    private void fromFile() {
        File fileFrom = new File("C:\\Users\\hackeru.HACKERU3\\Documents\\noalehrer\\MyFile5.encrypted.txt");
        File fileTo = new File("C:\\Users\\hackeru.HACKERU3\\Documents\\noalehrer\\MyFile5.txt");
        DecryptionFromFile decryptionFromFile = new DecryptionFromFile(map);
        byte[] bytesFrom = readFile(fileFrom);
        byte[] bytesTo = readFile(fileTo);
        checkIfDecryptionTogether(bytesFrom,bytesTo);
    }


    private void checkIfDecryptionTogether(byte[] bytesFrom,byte[] bytesTo) {
        FindIndexListener findIndexListenerTest = new FindIndexListener() {
            @Override
            public void find(byte index) {
                ifFound=true;
                indexTest=index;
            }
        };
        byte from=1,to= (byte) (255/2);
        DecryptionThread firstDecryptionThread1=new DecryptionThread(map,bytesFrom,from,to,findIndexListenerTest);
        firstDecryptionThread1.run();
        from= (byte) (255/2);
        to= (byte) 255;
        DecryptionThread secondDecryptionThread2=new DecryptionThread(map,bytesFrom,from,to,findIndexListenerTest);
        secondDecryptionThread2.run();
        if(ifFound) {
            for (int i = 0; i < SIZE && bytesFrom[i] != 0; i++) {
                Assertions.assertEquals(bytesTo[i], (byte) (bytesFrom[i] - indexTest), "not succeed");
            }
        }
        else {
            for (int i = 0; i < SIZE && bytesFrom[i] != 0; i++) {
                Assertions.assertEquals(bytesTo[i], (byte) (bytesTo[i] - indexTest), "not succeed");
            }

        }
    }


    public byte[] readFile(File file) {
        byte[] byteText = new byte[1000];
        int i = 0;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[1];
            while ((inputStream.read(bytes)) != -1 && i < 1000) {
                byteText[i++]= bytes[0];
            }
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
        return byteText;
    }

}