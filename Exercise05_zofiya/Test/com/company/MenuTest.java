        package com.company;

        import org.junit.jupiter.api.AfterEach;
        import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.mockito.InOrder;

        import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

/**
 * Created by hackeru on 3/16/2017.
 */
class MenuTest {

    int outputCount = 0;
    Menu myMenu;

    @BeforeEach
    void setUp() {
        System.out.println("Begin test Menu");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After test Menu");
    }

    @Test
    void chooseOptionTest(){
        Input input = mock(Input.class);
        when(input.input()).thenReturn("1").thenReturn("C:\\Users\\hackeru.HACKERU3\\Documents\\GitHub\\Exercisess\\Exercises\\z.txt").thenReturn("0");
        Output output = new Output() {
            @Override
            public void output(String s) {
                switch (outputCount){
                    case 0:
                        assertEquals("please choose:\n 1. If you want to encrypt the file Press 1\n 2. If you want to decrypt the file Press 2\n 0. exit\n your choice:", s);
                        break;
                    case 1:
                        assertEquals("Enter a file path:", s);
                        break;
                    case 2:
                        //assertEquals("The key is: "+ myMenu.keyLottery(), s);
                        break;
                    case 3:
                        assertEquals("encrypt",s);
                }
                outputCount++;
            }
        };

        myMenu = new Menu(output,input);
        myMenu.printMenu();
    }



    @Test
    void myChoiceTest1() {
        Menu myMenu = new Menu(new ScreenOutput(), new ScreenInput());
        Assertions.assertEquals(myMenu.myChoice(""), "Something went wrong. Please try again", "assertion failed for blank");
    }
    @Test
    void myChoiceTest2(){
        Menu myMenu = new Menu(new ScreenOutput(), new ScreenInput());
        Assertions.assertEquals(myMenu.myChoice( "\n"), "invalid option. try again.", "assertion failed for Enter");
    }
    @Test
    void myChoiceTest3(){
        Menu myMenu = new Menu(new ScreenOutput(), new ScreenInput());
        Assertions.assertEquals(myMenu.myChoice("a"), "invalid option. try again.", "assertion failed for letter");
    }
    @Test
    void myChoiceTest4(){
        Menu myMenu = new Menu(new ScreenOutput(), new ScreenInput());
        Assertions.assertEquals(myMenu.myChoice("6"), "invalid option. try again.", "assertion failed for number except 1,2");
    }

    @Test
    void chooseOptionTest2(){
        Input input = mock(Input.class);
        Output output = mock(Output.class);
        Menu myMenu1 = new Menu(output,input);
        when(input.input()).thenReturn("2").thenReturn("C:\\Users\\hackeru.HACKERU3\\Documents\\GitHub\\Exercisess\\Exercises\\z.txt").thenReturn("67").thenReturn("0");
        myMenu1.printMenu();
        InOrder orderedOutput = inOrder(output);
        orderedOutput.verify(output).output("please choose:\n 1. If you want to encrypt the file Press 1\n 2. If you want to decrypt the file Press 2\n 0. exit\n your choice:");
        orderedOutput.verify(output).output("Enter a file path:");
        orderedOutput.verify(output).output("Enter the encryption key in the file");
        orderedOutput.verify(output).output("decrypt");
    }



}