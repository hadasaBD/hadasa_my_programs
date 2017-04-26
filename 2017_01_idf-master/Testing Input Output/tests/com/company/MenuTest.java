package com.company;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by eladlavi on 15/03/2017.
 */
class MenuTest {

    @BeforeEach
    void before(){

    }

    @BeforeAll
    static void beforeAll(){

    }



    @Test
    void menuTest(){
        Menu menu = new Menu();
        menu.processInput("1");
        //assertEquals("incorrect path",
        //        menu.processInput("/Users/eladlavi/Documents/file_that_doesnt_exist.txt"));

        System.out.println("in menuTest()");
    }

    @Ignore
    @Test
    void anotherTest(){
        System.out.println("ignored test");
        //fail("failed");
    }


    @Test
    void testDivisionByZero(){
        Menu menu = new Menu();
        try{
            menu.divide(5, 0);
            fail("did not throw exception");
        }catch (ArithmeticException ex){

        }catch (Exception ex){
            fail("did not throw the correct exception");
        }

    }

}