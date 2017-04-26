package com.company;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by eladlavi on 14/03/2017.
 */
class CatTest {

    @BeforeAll
    static void beforeAll(){
        System.out.println("in beforeAll() of Cat");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("in afterAll() of Cat");
        System.out.println();
    }


    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void catTest() {
        System.out.println("in catTest()");
    }

}