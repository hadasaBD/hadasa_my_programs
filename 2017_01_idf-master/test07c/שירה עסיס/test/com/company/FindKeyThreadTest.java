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

    String text;
    boolean decryptSuccess;
    HashSet<String> set;
    byte[] buffer;
    FindKeyThread.KeyFoundListener listener;

    @BeforeEach
    void setUp() {
        text = "shira asis and noa lerer go to school";
        buffer = text.getBytes();
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = (byte) (buffer[i] - 152);
        }
        set = new HashSet<>();
        set.add("shira");
        set.add("go");
        set.add("noa");
        decryptSuccess = false;

        listener = new FindKeyThread.KeyFoundListener() {
            @Override
            public void keyFound(int key, String fileContent) {
                if ((key == 152) && (fileContent.equals(text)))
                    decryptSuccess = true;

            }
        };

    }


    @Test
    void runTest() {
        decryptSuccess = false;
        FindKeyThread findKeyThread = new FindKeyThread(0, 256, buffer, set, listener);
        findKeyThread.run();
        if (!decryptSuccess)
            Assertions.fail("There is a key!!");

    }

    @Test
    void searchTest() {
        FindKeyThread findKeyThread = new FindKeyThread(0, 0, buffer, set, listener);
        if (findKeyThread.search("shiradhjkhdk go noa"))
            Assertions.fail("words not exist");
        if (findKeyThread.search("dhjkhdkshira go noa"))
            Assertions.fail("words not exist");
        if (findKeyThread.search("dhjkshirahdk go noa"))
            Assertions.fail("words not exist");
        if (!findKeyThread.search("shira go noa"))
            Assertions.fail("words exist");


    }
}