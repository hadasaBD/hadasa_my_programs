package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by eladlavi on 06/03/2017.
 */
class DogTest {
    @Test
    void testEat() {
        Dog d = new Dog();
        int result = d.eat(7);
        //if(result != 12)
        //    Assertions.fail("eat did not increase by 5");

        Assertions.assertEquals(12, result);

    }

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testBark() {
        Dog d = new Dog();
        int result = d.bark(3);
        if(result != 6)
            Assertions.fail("bark did not return double the input");

    }

}