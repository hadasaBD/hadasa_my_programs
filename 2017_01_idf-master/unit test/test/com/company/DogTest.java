package com.company;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by eladlavi on 02/03/2017.
 */
class DogTest {
    @Test
    void bark() {
        System.out.println("this is a test");
        //Assert.fail("barking failed");
    }

    @Test
    void anotherTest(){
        System.out.println("in anotherTest()");
    }

}