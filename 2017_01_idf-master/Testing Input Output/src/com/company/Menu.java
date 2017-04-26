package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidParameterException;

/**
 * Created by eladlavi on 14/03/2017.
 */
public class Menu {

    public static final int START = 1;
    public static final int GET_PATH = 2;

    private int state;

    public Menu(){
        state = START;
    }

    public String getInitialInstructions(){
        return "please choose:\n 1. sign in\n 2. log in\n type exit at any point to exit this program";
    }



    public String processInput(String input){
        String output = "";
        switch (state) {
            case START:
                switch (input){
                    case "1":
                        break;
                    case "2":
                        state = GET_PATH;
                        return "enter path";
                    case "3":
                        break;
                }
                output = "bla";
                break;
            case GET_PATH:
                output = "bla bla";
                if(input == "go back")
                    state = START;
                break;
            default:
                output = "bla bla bla";
                break;
        }
        return output;
    }


    public int divide(int x, int y){
        if(y == 0)
            throw new ArithmeticException();
        return x / y;
    }
}
