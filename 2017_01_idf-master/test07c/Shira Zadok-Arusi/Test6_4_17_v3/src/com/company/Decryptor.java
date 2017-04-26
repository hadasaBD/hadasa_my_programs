package com.company;

import java.io.*;

/**
 * Created by hackeru on 4/19/2017.
 */
public class Decryptor {

    public static byte[] decrypt(byte[] bytes, int j) {

        byte decrypt[] = new byte[bytes.length];
        for (int i = 0; i < decrypt.length; i++)
            decrypt[i] = (byte) (bytes[i] - j);
        return decrypt;
    }
}