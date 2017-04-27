package com.company;

import java.io.File;

/**
 * Created by hackeru on 3/22/2017.
 */
public class DoubleAlgorithm <T extends Algorithms, S extends Algorithms> extends Algorithms {
    T first;
    S second;
    String inputAlgorithm;
    //int key1;
    int key2;

    public DoubleAlgorithm(T first, S second, int key2) {
        this.first=first;
        this.second=second;
        //this.key1=key1;
        this.key2=key2;
    }

    @Override
    public void encrypted(File sourceFile, int key) {

        first.encrypted(sourceFile,key);
        second.encrypted((makeFile(sourceFile,true)),key2);

    }

    @Override
    public void decrypted(File sourceFile, int key) {
        second.decrypted(sourceFile,key2);
        first.decrypted((makeFile(sourceFile,false)),key);

    }

    @Override
    public void crypt(File sourceFile, int key, boolean type) {


    }
}
