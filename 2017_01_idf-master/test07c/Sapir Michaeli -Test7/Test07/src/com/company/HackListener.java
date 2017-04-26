package com.company;
/**
 * Created by hackeru on 19.04.2017.
 */

public class HackListener implements Hack.MatchFound{
    String decryptText = null;
    int actualKey;

    @Override
    public void found(int key, byte[] crackedText) {
        actualKey=key;
        decryptText=new String(crackedText);
    }


}