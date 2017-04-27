package com.company;

import java.io.*;

import static com.company.FileHandler.CloseFile;

/**
 * Created by hackeru on 3/20/2017.
 */
public class MultAlgorithm extends Algorithms {

    @Override
    public void crypt(File sourceFile, int key, boolean type) {
        makeFile(sourceFile, type);
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(destinationFile);
            int buffer;
            if (type) {
                while ((buffer = inputStream.read()) != -1) {
                    outputStream.write(buffer * key);
                }
            } else {
                int decryptionKey = 0;
                for (int i = 1; i <= 255; i++) {
                    if (((i * key) & 0x000000FF) == 1) {
                        decryptionKey = i;
                        break;
                    }
                }
                while ((buffer = inputStream.read()) != -1) {
                    outputStream.write(buffer * decryptionKey);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ended();
            CloseFile(outputStream, inputStream);
        }
    }


    @Override
    public void encrypted(File sourceFile, int key) {
        crypt(sourceFile, key, true);

    }

    @Override
    public void decrypted(File sourceFile, int key) {
        crypt(sourceFile, key, false);
    }
}
