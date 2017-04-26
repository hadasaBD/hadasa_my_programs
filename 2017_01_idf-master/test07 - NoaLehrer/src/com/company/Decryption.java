package com.company;

import java.io.*;
import java.util.Map;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Decryption {
    public Map<String,Integer> map;
    File file=new File("abc");

    public Decryption(Map<String,Integer> map) {
        this.map=map;
        enterPath();
    }

    private void enterPath() {
        while (!file.exists()||!file.canRead())
        {
            System.out.println("enter path");
            file=new File(setMessage());
        }
        Caesar caesar=new Caesar(readFile(),map);
        readFile();
    }

    private byte[] readFile() {
        byte[] byteText = new byte[1000];
        int i = 0;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[1];
            while ((inputStream.read(bytes)) != -1 && i < 1000) {
                byteText[i++]= bytes[0];
            }
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
        return byteText;
    }

    public String setMessage() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
