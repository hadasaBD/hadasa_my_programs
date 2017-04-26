package com.company;

import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */
//import static com.company.T3.words;
public class TreadDecryptionT4 extends Thread {
    private String charsFromFile;
    private String charsFromFileDecrypt;
    private Set<String> words;
    private byte key;
    private boolean stop;
    DecryptionHack.MatchFound matchFound;

    public TreadDecryptionT4(String charsFromFile, Set<String> words, DecryptionHack.MatchFound matchFound, byte key) {
        this.charsFromFile = charsFromFile;
        this.words = words;
        this.matchFound = matchFound;
        this.key = key;
    }

    public void setStop() {
        stop = true;
    }

    @Override
    public void run() {
        if (decryption(key)) {
            matchFound.found(key, charsFromFileDecrypt);
        }
    }

    private String decryptFile(byte key) {
        byte[] newString = new byte[charsFromFile.length()];
        for (int i = 0; i < newString.length && !stop; i++) {
            newString[i] = (byte) (charsFromFile.charAt(i) - key);
        }
        String result = new String(newString);
        return result;
    }

    private boolean decryption(byte key) {
        int counter = 0;
        int where;
        charsFromFileDecrypt = decryptFile(key);
        for (String word : words) {
            int wordLength = word.length();
            if ((where = charsFromFileDecrypt.indexOf(word)) != -1) {
                if (where == 0 || charsFromFileDecrypt.charAt(where - 1) == ' ' || charsFromFileDecrypt.charAt(where - 1) == '.' || charsFromFileDecrypt.charAt(where - 1) == ',') {
                    if (where + wordLength >= charsFromFileDecrypt.length() || charsFromFileDecrypt.charAt(where + wordLength) == '.' || charsFromFileDecrypt.charAt(where + wordLength) == ',' || charsFromFileDecrypt.charAt(where + wordLength) == ' ')
                        counter++;
                }
            }

        }
        if (counter >= 3)
            return true;
        return false;
    }

}
