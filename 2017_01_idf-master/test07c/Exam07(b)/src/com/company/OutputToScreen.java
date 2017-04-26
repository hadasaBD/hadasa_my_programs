package com.company;

/**
 * Created by Rubin on 19/04/2017.
 */
public class OutputToScreen implements Output {
    @Override
    public void getOutput(String s) {
        System.out.println(s);
    }
}
