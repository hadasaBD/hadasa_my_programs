package com.company;

import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */
//import static com.company.T3.words;
public class TreadDecryptionT4 extends Thread {
    private final  int MIN_WORDS_TO_FIND =3;
    private String charsFromFile;
    private String charsFromFileDecrypt;
    private Set<String> words;
    private boolean stop;
    private byte from,to;
    private DecryptionHack.MatchFound matchFound;

    public TreadDecryptionT4(byte from,byte to,String charsFromFile, Set<String> words,DecryptionHack.MatchFound matchFound) {
        this.charsFromFile = charsFromFile;
        this.words = words;
        this.matchFound = matchFound;
        this.from=from;
        this.to=to;
    }

    public void setStop() {
        stop = true;
    }

    @Override
    public void run() {
        for (byte i = from; i < to; i++) {
            if (decryption(i)&&matchFound!=null) {
                matchFound.found(i, charsFromFileDecrypt);
            }
        }
    }

    public String decryptFile(byte key) {
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
            where=0;
            int wordLength = word.length();
            while ((where = charsFromFileDecrypt.indexOf(word,where)) != -1) {
                if(isWord(where,wordLength)) {
                    counter++;
                    break;
                }
                where++;
            }

        }
        if (counter >= MIN_WORDS_TO_FIND)
            return true;
        return false;
    }
    private boolean isWord(int where,int wordLength) {
        if (where == 0 || charsFromFileDecrypt.charAt(where - 1) == ' ' || charsFromFileDecrypt.charAt(where - 1) == '.' || charsFromFileDecrypt.charAt(where - 1) == ',') {
            if (where + wordLength >= charsFromFileDecrypt.length() || charsFromFileDecrypt.charAt(where + wordLength) == '.' ||
                    charsFromFileDecrypt.charAt(where + wordLength) == ',' || charsFromFileDecrypt.charAt(where + wordLength) == ' ')
                return true;


        }
        return false;
    }

}
