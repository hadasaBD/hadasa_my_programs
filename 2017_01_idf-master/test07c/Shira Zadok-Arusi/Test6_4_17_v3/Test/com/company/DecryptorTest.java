package com.company;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 4/20/2017.
 */
class DecryptorTest {

    int actualyKey;
    String actualMessage;
    boolean decryptSuccess;

    @Test
    void decrypt() {
        decryptSuccess=true;
        actualMessage = "this is the,original message";
        byte[] msgBytes = actualMessage.getBytes();
        this.actualyKey = 13;
        for (int i = 0; i < msgBytes.length; i++) {
            msgBytes[i] = (byte)((int)msgBytes[i] - actualyKey);
        }
        byte[]decryptBytes=Decryptor.decrypt(actualMessage.getBytes(),actualyKey);
        for (int i = 0; i <msgBytes.length ; i++) {
            if(msgBytes[i]!=decryptBytes[i])
                decryptSuccess=false;

        }
        if(!decryptSuccess)
            fail("did not decrypt the message");


    }

}



