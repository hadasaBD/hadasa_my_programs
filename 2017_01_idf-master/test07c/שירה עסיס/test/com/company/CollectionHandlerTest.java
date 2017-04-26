package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 4/19/2017.
 */
class CollectionHandlerTest {

    String text;
    FindKeyThread.KeyFoundListener listener;
    boolean decryptSuccess;
    CollectionHandler collectionHandler;
    int optionKey;

    @BeforeEach
    void setUp() {
        collectionHandler = new CollectionHandler();
        collectionHandler.add("shira");
        collectionHandler.add("go");
        collectionHandler.add("noa");
        text = "shira asis and noa lerer go to school";
        listener = new FindKeyThread.KeyFoundListener() {
            @Override
            public void keyFound(int key, String fileContent) {
                if ((key == optionKey) && (fileContent.equals(text)))
                    decryptSuccess = true;

            }
        };
    }

    @Test
    void addTest() {
        CollectionHandler collectionHandler = new CollectionHandler();
        if (!collectionHandler.add("shira"))
            Assertions.fail("not add");
        if (collectionHandler.add("shira"))
            Assertions.fail("add an existing word ");
    }

    @Test
    void caesarEncryptTest_thereIsKey() {
        byte[] buffer = text.getBytes();
        decryptSuccess = false;
        for (optionKey = 0; optionKey < 256; optionKey++) {
            for (int j = 0; j < buffer.length; j++) {
                buffer[j] = (byte) (buffer[j] - optionKey);
            }
            collectionHandler.caesarEncrypt(buffer, listener);
            if (!decryptSuccess)
                Assertions.fail("There is a key!!");
            buffer = text.getBytes();
        }
    }
    @Test
    void caesarEncryptTest_thereIsNotKey() {
        collectionHandler.remove("go");
        byte[] buffer = text.getBytes();
        decryptSuccess = false;
        for (optionKey = 0; optionKey < 256; optionKey++) {
            for (int j = 0; j < buffer.length; j++) {
                buffer[j] = (byte) (buffer[j] - optionKey);
            }
            collectionHandler.caesarEncrypt(buffer, listener);
            if (decryptSuccess)
                Assertions.fail("There isn't a key!!");
            buffer = text.getBytes();
        }

    }

}