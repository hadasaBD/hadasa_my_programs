package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 3/14/2017.
 */
class MenuTest {
    //לפני כל בדיקה מופעלת המתודה הזאת
    @BeforeEach
    void setUp() {
        System.out.println("in setUp()");

    }

    //אחרי כל בדיקה
    @AfterEach
    void tearDown() {
        System.out.println("in tearDown()");

    }

    @Test
    void printMenuTest() {
        System.out.println("in printMenuTest()");

    }

    @Test
    void readInputTest() {
        System.out.println("in readInputTest");

    }

}