package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by hackeru on 4/6/2017.
 */
public class FileManipulator {


    public static byte[] fileToCharArray(File file) throws IOException {
        byte[] fileChars = new byte[(int) file.length()];
        InputStream fileInputStream = new FileInputStream(file);
        int actuallyRead;
        int counter = 0;
        while ((actuallyRead = fileInputStream.read())!=-1)
            fileChars[counter++] = (byte) actuallyRead;
        return fileChars;
    }

}
