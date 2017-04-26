package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 4/19/2017.
 */
class FindKeyThreadTest {
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }
//===========================================אני באמצע הפו הנ"ל
    @Test
    void runTest() {
        File file1 = new File("C:\\Users\\hackeru.HACKERU3\\Downloads\\shira.txt");
        File file2 = new File("C:\\Users\\hackeru.HACKERU3\\Downloads\\shiraDec.txt");

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(file1);
            byte[] buffer = new byte[(int) file1.length()];
            inputStream.read(buffer);

            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = (byte) (buffer[i] - 152);
            }
            outputStream = new FileOutputStream(file2);
            outputStream.write(buffer);

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
            if (outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }
        HashSet<String> set = new HashSet<>();
        set.add("shira");
        set.add("go");
        set.add("noa");
        byte[] b = new byte[5];
        FindKeyThread.KeyFoundListener listener = new FindKeyThread.KeyFoundListener() {
            @Override
            public void keyFound(String string) {
                System.out.println(string);

            }
        };
        FindKeyThread findKeyThread = new FindKeyThread(0, 256, b, set, listener);

    }

    @Test
    void searchTest() {
        HashSet<String> set = new HashSet<>();
        set.add("shira");
        set.add("go");
        set.add("noa");
        byte[] b = new byte[5];
        FindKeyThread.KeyFoundListener listener = new FindKeyThread.KeyFoundListener() {
            @Override
            public void keyFound(String string) {
                System.out.println(string);

            }
        };
        FindKeyThread findKeyThread = new FindKeyThread(0, 0, b, set, listener);
        if (findKeyThread.search("shiradhjkhdk go noa"))
            Assertions.fail("words not exist");
        if (findKeyThread.search("dhjkhdkshira go noa"))
            Assertions.fail("words not exist");
        if (!findKeyThread.search("shira go noa"))
            Assertions.fail("words exist");


    }

}