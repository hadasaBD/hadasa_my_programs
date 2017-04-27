package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Created by hackeru on 3/14/2017.
 */
class MenuTest {
    @BeforeEach
    void setUp() {
        System.out.println("Begin test Menu");

    }

    @AfterEach
    void tearDown() {
        System.out.println("After test Menu");

    }

    @Test
    void testInputTest() {

        Menu m = new Menu();
        //Assertions.assertEquals(m.testInput("a"), "1","not number");
        if (m.testInput("a") != null)
            Assertions.fail("is not number");
    }

}