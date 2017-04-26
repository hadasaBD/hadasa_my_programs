package com.company;

import java.io.*;
import java.util.HashSet;

/**
 * Created by hackeru on 4/19/2017.
 */
public class CollectionHandler {
    HashSet<String> set;

    public CollectionHandler() {
        this.set = new HashSet<>();
    }

    public HashSet<String> getSet() {
        return set;
    }

    public boolean add(String s) {
        return set.add(s);
    }


    public byte[] uploadFile(String file_name) {
        File file = new File(file_name);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            int actuallyRead = inputStream.read(buffer);
            if (actuallyRead == file.length())
                return buffer;
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
        return null;
    }

    public void caesarEncrypt(byte[] fileContent, FindKeyThread.KeyFoundListener listener) {

        FindKeyThread thread1 = new FindKeyThread(0, 256 / 2, fileContent, set, listener);
        FindKeyThread thread2 = new FindKeyThread(256 / 2, 256, fileContent, set, listener);
        thread1.start();
        thread2.start();

       /* String ret = "";
        byte[] temp = new byte[fileContent.length];
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < temp.length; j++) {
                temp[j] = (byte) (i+fileContent[j]);
            }
            String string = new String(temp);
            if (search(string)){
                ret += "The Key: " + i + "\n" + string;
            }
        }
        return ret;*/
    }
    /*public boolean search( String text){

        HashSet<String> words = new HashSet<>();
        for (String var: set) {
            int index = text.indexOf(var);
            char tav = '*';
            if (index != -1){
                if (index != 0)
                    tav = text.charAt(index - 1);
                if ((index == 0) || (tav == ' ') || (tav == ',') || (tav == '.'))
                    words.add(var);
                if (words.size() == 3)
                    return true;

            }
        }
        return false;

    }*/
}
