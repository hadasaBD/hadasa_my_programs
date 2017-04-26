package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */
public class DecryptionHack {
    private File file;
    private String charsFromFile;
    private MatchFound matchFound;
    private Set<String> words;
    private boolean exit;
    private TreadDecryptionT4 treadDecryption1;
    private TreadDecryptionT4 treadDecryption2;

    public void stop() {
        this.exit = true;
        if (treadDecryption1 != null) {
            treadDecryption1.interrupt();
            treadDecryption1.setStop();
        }
        if (treadDecryption2 != null) {
            treadDecryption2.interrupt();
            treadDecryption2.setStop();
        }
    }

    public DecryptionHack(File file, Set<String> words, MatchFound matchFound) {
        this.file = file;
        this.matchFound = matchFound;
        this.words = words;
    }

    public void decrypt() {
        fillArray();
        tryDecryption();
    }

    private void fillArray() {
        ////בהנחה שהקובץ לא גדול
        if(file.length()<Integer.MAX_VALUE) {
            charsFromFile = new String();
            StringBuilder stringBuilder = new StringBuilder((int) file.length());
            InputStream inputStream = null;
            try {
                inputStream = new FileInputStream(file);
                int actuallyRead;
                while ((actuallyRead = inputStream.read()) != -1) {
                    stringBuilder.append((char) actuallyRead);
                }
                charsFromFile = stringBuilder.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null)
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    private void tryDecryption() {
        treadDecryption1 = new TreadDecryptionT4((byte) 0, (byte) (Byte.MAX_VALUE / 2), charsFromFile, words, matchFound);
        treadDecryption2 = new TreadDecryptionT4((byte) (Byte.MAX_VALUE / 2), Byte.MAX_VALUE, charsFromFile, words, matchFound);
        treadDecryption1.start();
        treadDecryption2.start();
        try {
            treadDecryption1.join();
            treadDecryption2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public interface MatchFound {
        void found(byte key, String file);
    }
}
