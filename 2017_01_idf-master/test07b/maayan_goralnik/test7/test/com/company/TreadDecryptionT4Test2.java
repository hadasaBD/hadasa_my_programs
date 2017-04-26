package com.company;

import org.junit.Test;

import java.io.*;
import java.util.Set;

import static org.junit.Assert.fail;

/**
 * Created by hackeru on 4/19/2017.
 */
class TreadDecryptionT4Test2 {
    static byte key = 20;
    static boolean find;
    private String charsFile;
    private Set<String> words;

    @Test
    void findKey() {
        try {
            words.add("and");
            words.add("a");
            words.add("to");
            words.add("of");
            createFile();
            encryptFile();
            DecryptionHack.MatchFound matchFound = new DecryptionHack.MatchFound() {
                @Override
                public void found(int key, String file) {
                    if (key == TreadDecryptionT4Test2.key)
                        find = true;
                }
            };

            TreadDecryptionT4 decryption = new TreadDecryptionT4(charsFile, words, matchFound, key);
            decryption.start();
        }
        finally {
            if(!find)
                fail();
        }
    }

    private void createFile() {
        File file = new File("C:\\Users\\HACKERU.HACKERU3\\Desktop\\test.txt");

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write("and a of hgdfh to bbbbb".getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }




    private void encryptFile() {
        StringBuilder stringBuilder = new StringBuilder();
        File file = new File("C:\\Users\\HACKERU.HACKERU3\\Desktop\\test.txt");
        File encryptFile = new File("C:\\Users\\HACKERU.HACKERU3\\Desktop\\test.encrypted.txt");
        OutputStream outputStream = null;
        InputStream inputStream = null;

        try {
            int a;
            while ((a = inputStream.read()) != -1) {
                outputStream.write(a + key);
                stringBuilder.append((char) (a + key));
            }
            charsFile = stringBuilder.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}


