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
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getSetTest() {

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
    void uploadFileTest() {

    }

    @Test
    void caesarEncryptTest() {

    }

}