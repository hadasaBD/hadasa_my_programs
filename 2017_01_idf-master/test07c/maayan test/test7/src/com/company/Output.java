package com.company;

/**
 * Created by hackeru on 3/15/2017.
 */
public class Output implements OutputInterface {

    @Override
    public void getOutput(String print) {
        System.out.println(print);
    }
}
