package com.company;

import java.io.File;

/**
 * Created by eladlavi on 22/03/2017.
 */
public interface EncryptionAlgo {
    void encrypt(File in, File out);
    void decrypt(File in, File out);
    void setKey(int key);
    int getKey();

}
