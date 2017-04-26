package com.company;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by eladlavi on 14/03/2017.
 */
class DogTest {


    @BeforeAll
    static void beforeAll(){
        System.out.println("in beforeAll()");
    }


    @AfterAll
    static void afterAll(){
        System.out.println("in afterAll()");
    }


    @BeforeEach
    void setUp() {
        System.out.println("in setUp()");
    }

    @AfterEach
    void tearDown() {
        System.out.println("in tearDown()");
    }


    @Test
    void dogTest() {

        MyInterface myInterface = mock(MyInterface.class);
        when(myInterface.myMethod(5,6)).thenReturn(11);
        assertEquals(11, myInterface.myMethod(5,6));

        System.out.println("in dogTest()");
        Dog d = new Dog("snoopy");
        if(!d.getName().equals("snoopy"))
            Assertions.fail("getName did not return name given in constructor");
        //is the same this as:
        Assertions.assertEquals(d.getName(), "snoopy", "getName did not return name given in constructor");
        d.setName("pluto");
        Assertions.assertEquals(d.getName(), "pluto", "getName did not return correct name after setName");
        Assertions.assertEquals(d.bark(), "waf waf pluto 0");
        Assertions.assertEquals(d.bark(), "waf waf pluto 1");
        long t1 = System.nanoTime();
        d.setName("bla bla");
        long t2 = System.nanoTime();
        Assertions.assertFalse(t2 - t1 < 1000L, "setName took too long");

        //verify(myInterface).myMethod(5,7);

    }


    @Test
    void anotherTest(){
        System.out.println("in anotherTest()");
    }

}













