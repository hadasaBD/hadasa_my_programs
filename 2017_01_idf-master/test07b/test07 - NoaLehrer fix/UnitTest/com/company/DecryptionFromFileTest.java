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
    @Test
    void checkIfDecryption() {
        map.put("good",1);
        File fileFrom = new File("C:\\Users\\hackeru.HACKERU3\\Documents\\noalehrer\\MyFile5.encrypted.txt");
        File fileTo = new File("C:\\Users\\hackeru.HACKERU3\\Documents\\noalehrer\\MyFile5.txt");
        DecryptionFromFile decryptionFromFile = new DecryptionFromFile(map);
        byte[] bytesFrom = readFile(fileFrom);
        byte[] bytesTo = readFile(fileTo);
        FindIndexListener findIndexListenerTest = new FindIndexListener() {
            @Override
            public void find(byte index) {
                Assertions.assertEquals(49,index);
//                for (int i = 0; i <SIZE ; i++) {
//                    Assertions.assertEquals(bytesTo[i],(byte)(bytesFrom[i]-index),"not succeed");
//                }

            }
        };
        byte from=0,to= (byte) (255/2);
        DecryptionThread firstDecryptionThread1=new DecryptionThread(map,bytesFrom,from,to,findIndexListenerTest);
        firstDecryptionThread1.start();
        from= (byte) (255/2);
        to= (byte) 255;
        DecryptionThread secondDecryptionThread2=new DecryptionThread(map,bytesFrom,from,to,findIndexListenerTest);
        secondDecryptionThread2.start();
    }

    @Test
    void checkIfDecryptionWithEmptyMap() {
        File fileFrom = new File("C:\\Users\\hackeru.HACKERU3\\Documents\\noalehrer\\MyFile5.encrypted.txt");
        File fileTo = new File("C:\\Users\\hackeru.HACKERU3\\Documents\\noalehrer\\MyFile5.txt");
        DecryptionFromFile decryptionFromFile = new DecryptionFromFile(map);
        byte[] bytesFrom = readFile(fileFrom);
        byte[] bytesTo = readFile(fileTo);
        FindIndexListener findIndexListenerTest = new FindIndexListener() {
            @Override
            public void find(byte index) {
                for (int i = 0; i <SIZE ; i++) {
                    Assertions.assertEquals(bytesTo[i],(byte)(bytesFrom[i]-index),"not succeed");
                }

            }
        };
        byte from=0,to= (byte) (255/2);
        DecryptionThread firstDecryptionThread1=new DecryptionThread(map,bytesFrom,from,to,findIndexListenerTest);
        firstDecryptionThread1.start();
        from= (byte) (255/2);
        to= (byte) 255;
        DecryptionThread secondDecryptionThread2=new DecryptionThread(map,bytesFrom,from,to,findIndexListenerTest);
        secondDecryptionThread2.start();
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