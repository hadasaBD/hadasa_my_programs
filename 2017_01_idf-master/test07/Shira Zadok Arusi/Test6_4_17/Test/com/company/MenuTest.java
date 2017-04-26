package com.company;

/**
 * Created by hackeru on 4/6/2017.
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MenuTest {
    static Input input;
    static Output output;

    @BeforeEach
    void setUp() {
        input= mock(Input.class);
        output=mock(Output.class);
        Menu.input=input;
        Menu.output=output;
    }

    @Test
    void firstIo1() {
        String s = " ";
        when(input.input()).thenReturn(1).thenReturn("Shira").thenReturn(0);
        Menu.start();
        verify(output).output("Enter list of words"");
    }

}
