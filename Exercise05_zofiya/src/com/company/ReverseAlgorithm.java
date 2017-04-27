package com.company;

import java.io.File;

/**
 * Created by hackeru on 3/20/2017.
 */
public class ReverseAlgorithm extends Algorithms {
    public static final String CAESAR = "1";
    public static final String XOR = "2";
    public static final String MULT = "3";
    Operations AlgorithmType;
    Output output=new ScreenOutput();
    Input input=new ScreenInput();

    public ReverseAlgorithm() {
        output.output("Select the algorithm to use:\n 1. Caesar 1\n 2. XorAlgorithm Press 2\n 3. MultAlgorithm Press 3\n your choice:");
        String input1 = input.input();
        if (input1.length() != 0) {
            switch (input1) {
                case CAESAR: // put in const/final
                    AlgorithmType = new Caesar();
                    return;
                case XOR:
                    AlgorithmType = new XorAlgorithm();
                    return;
                case MULT:
                    AlgorithmType = new MultAlgorithm();
                    return;
            }
        }
    }


    @Override
    public void crypt(File sourceFile, int key, boolean type) {
        AlgorithmType.crypt(sourceFile, key, type);
    }

    @Override
    public void encrypted(File sourceFile, int key) {
        crypt(sourceFile, key, false);
        makeFile(sourceFile,false).renameTo((makeFile(sourceFile,true)));
    }

    @Override
    public void decrypted(File sourceFile, int key) {
        crypt(sourceFile, key, true);
        makeFile(sourceFile,true).renameTo((makeFile(sourceFile,false)));
    }
}