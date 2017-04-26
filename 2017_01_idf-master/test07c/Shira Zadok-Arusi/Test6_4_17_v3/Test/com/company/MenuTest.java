package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hackeru on 4/19/2017.
 */
class MenuTest {
    static Input input;
    static Output output;

    @BeforeEach
    void setUp() {
        input = mock(Input.class);
        output = mock(Output.class);
        Menu.input = input;
        Menu.output = output;
    }

    @Test
    void EnterWord() {
        when(input.input()).thenReturn("1").thenReturn("Shira").thenReturn("0");
        Menu.start();
        verify(output).output("Enter list of words");
    }


    @Test
    void EnterExitWord() {
        when(input.input()).thenReturn("1").thenReturn("Shira").thenReturn("1").thenReturn("Shira").thenReturn("0");
        Menu.start();
        verify(output).output("The 1 word is exist");
    }

    @Test
    void invalidPath_fail() {
        String s = "hello";
        when(input.input()).thenReturn("3").thenReturn(s).thenReturn("0");
        Menu.start();
        verify(output).output("The file isn't exist, enter a correct path again or enter 0 for exit");

    }
}