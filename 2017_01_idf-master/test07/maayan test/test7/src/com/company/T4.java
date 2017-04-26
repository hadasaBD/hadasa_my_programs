package com.company;

import java.io.*;

/**
 * Created by hackeru on 4/6/2017.
 */
public class T4 {
    public  static  final String[] words= new String[]{"and", "a","the","be","in","a"};
    private File file;
    private char[] arrayChars;
    public void menu(){
        System.out.println("enter path");
        String path=getInputFromUser();
        file=new File(path);
        if(!file.exists()&&file.isFile())
            System.out.println("error input");
        fillArray();
        tryDecryption();
    }

    private void fillArray() {
        ////בהנחה שהקובץ לא גדול
        arrayChars=new char[(int) file.length()];
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(file);
            int actuallyRead,counter=0;
            while((actuallyRead = inputStream.read()) != -1) {
                arrayChars[counter++]= (char) actuallyRead;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    private void tryDecryption() {
        for (int i = 0; i <Integer.MAX_VALUE/2; i++) {
          TreadDecryptionT4 treadDecryptionT4=new TreadDecryptionT4(i,arrayChars);
          TreadDecryptionT4 treadDecryptionT41=new TreadDecryptionT4(Integer.MAX_VALUE-i,arrayChars);
        }
    }


    private static String getInputFromUser(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

