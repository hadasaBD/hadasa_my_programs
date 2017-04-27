package com.company;

import java.io.File;

/**
 * Created by hackeru on 3/20/2017.
 */
public abstract class Algorithms implements Operations {
    File destinationFile;
    static Listener listener;
    IntKey key;


    public static void setListener(Listener listener) {
        Algorithms.listener = listener;
    }

    void started() {
        if (listener != null)
            listener.StartDetect();
    }

    void ended() {
        if (listener != null)
            listener.EndDetect();
    }

    public File makeFile(File sourceFile, boolean type) {
        String fileName = sourceFile.getAbsolutePath();
        int pos = fileName.lastIndexOf(".");
        if (pos > 0) {
            fileName = fileName.substring(0, pos);
        }
        if (type) {
            destinationFile = new File(new String(fileName + "_encrypted.txt"));
        } else {
            destinationFile = new File(new String(fileName + "_decrypted.txt"));
        }
        return destinationFile;
    }


}
