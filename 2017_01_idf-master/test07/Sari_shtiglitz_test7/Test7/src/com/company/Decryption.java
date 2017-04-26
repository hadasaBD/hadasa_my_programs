package com.company;

import java.io.*;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Decryption extends File {

    public Decryption(String pathname) {
        super(pathname);
    }



    public File getPathFromInput(String path, Listener listener) {
        OutPut forOutPut = new OutPut();
        Input forInput = new Input();
        File file = new File(path);
        if (!file.exists()) {
            forOutPut.output("is not file or file not exists. please enter path again");

        }
        decryption(file, listener);


        return file;
    }

    public void decryption(File file1, Listener listener) {
        File file = new File("C:\\Users\\hackeru\\Documents\\sari_shtiglitz\\IntSari\\Decryption.decrypted.txt");
        InputStream inputStream = null;
        OutputStream outputStream = null;
        int key;


        try {
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(file1);
            int actually;
            for (int i = 0; i <= 255; i++) {
                key = i;
                while ((actually = inputStream.read()) != -1)
                    outputStream.write(actually - key);
                checkFile(file1, listener, key);

            }

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

    private void checkFile(File file1, Listener listener, int key) throws IOException {
        int counter = 0;
        File tempFile = new File("/Users//Desktop/temp.txt");
        OutputStream outputStream = null;
        InputStream inputStream = null;
        byte[] buffer = new byte[1024];
        int actuallyRead = inputStream.read(buffer);
        char[] charArray = new char[actuallyRead];
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = (char) buffer[i];
        }

        for (int i = 0; i < buffer.length; i++) {
            if (charArray[i] == ' ' || charArray[i] == '.' || charArray[i] == ',') {
                String s = charArray.toString();
                for (String t : Menu.listOfWords) {
                    if (s == t) {
                        counter++;

                    }
                    if (counter >= 3) {
                        listener.printFileSuspect(file1, key);
                    }


                }
            }
        }


    }
}


