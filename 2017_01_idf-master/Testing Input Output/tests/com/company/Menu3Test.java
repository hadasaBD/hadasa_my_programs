package com.company;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Created by eladlavi on 16/03/2017.
 */
class Menu3Test {

    int inputCount = 0;
    int outputCount = 0;

    @Test
    public void testMenu3(){
        UserInterface userInterface = mock(UserInterface.class);
        when(userInterface.input()).thenReturn("hi").thenReturn("bye").thenReturn("die");
        Menu3 menu3 = new Menu3(userInterface);
        menu3.start();
        InOrder orderedOutput = inOrder(userInterface);
        orderedOutput.verify(userInterface).output("potato");
        orderedOutput.verify(userInterface).output("tomato");
        orderedOutput.verify(userInterface).output("mockito");



        /*
        UserInterface userInterface = new UserInterface() {
            @Override
            public void output(String s) {
                switch (outputCount){
                    case 0:
                        assertEquals("tomato", s);
                        break;
                    case 1:
                        assertEquals("potato", s);
                        break;
                    case 2:
                        assertEquals("mokito", s);
                        break;
                }
                outputCount++;
            }

            @Override
            public String input() {
                String result = "";
                switch (inputCount){
                    case 0:
                        result = "hi";
                        break;
                    case 1:
                        result = "bye";
                        break;
                    case 2:
                        result = "die";
                        break;
                }
                inputCount++;
                return result;
            }
        };
        */
    }

}