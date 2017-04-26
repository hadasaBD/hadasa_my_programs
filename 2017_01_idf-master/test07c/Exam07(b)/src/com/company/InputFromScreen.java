package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Rubin on 19/04/2017.
 */

public class InputFromScreen implements Input {
    @Override
    public String getInput() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
        }
        return "error in InputFromScreen";
    }
}
