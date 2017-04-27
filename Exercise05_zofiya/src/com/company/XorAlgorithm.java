package com.company;

import java.io.*;

import static com.company.FileHandler.CloseFile;

/**
 * Created by hackeru on 3/20/2017.
 */
public class XorAlgorithm extends Algorithms {

    @Override
    public void crypt(File sourceFile, int key, boolean type) {
        started();
        makeFile(sourceFile,type);
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(sourceFile);
            outputStream = new FileOutputStream(destinationFile);
            int buffer;
            while ((buffer = inputStream.read()) != -1) {
                outputStream.write(buffer ^ key);
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
        crypt(sourceFile,key,true);
    }

    @Override
    public void decrypted(File sourceFile, int key) {
        crypt(sourceFile,key,false);

    }

}
