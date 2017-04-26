package com.company;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by eladlavi on 15/03/2017.
 */
class Menu2Test {

    @Mock
    MathOperations mathOperations;

    @Test
    void testMyApp(){

        /*Menu2.UserInterface userInterface = new Menu2.UserInterface() {
            @Override
            public void output(String s) {
                if(!s.equals("hello"))
                    fail("was not hello");
            }

            @Override
            public String input() {
                return "hi";
            }
        };*/

        //Menu2.UserInterface userInterface = mock(Menu2.UserInterface.class);



        Dog d = mock(Dog.class);
        when(d.bark()).thenReturn(1);

        mathOperations = mock(MathOperations.class);
        when(mathOperations.add(5,6)).thenReturn(11);

        Menu2 menu2 = new Menu2(null);
        menu2.setMathOperations(mathOperations);
        assertEquals(22, menu2.stam(5, 6));

        //verify(mathOperations).add(anyInt(),6);


        menu2.setD(d);
        assertEquals(0, menu2.stam2());

        //Menu2 menu2 = new Menu2(userInterface);
        //menu2.start();
    }



}