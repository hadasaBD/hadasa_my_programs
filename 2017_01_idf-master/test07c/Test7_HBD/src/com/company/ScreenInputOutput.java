package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ScreenInputOutput implements Input,Output {
    @Override
    public String getInput() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "exit";
    }

    @Override
    public void output(String s) {
        System.out.println(s);
    }

    @Override
    public void outputInLine(String s) {
        System.out.print(s);
    }
}
