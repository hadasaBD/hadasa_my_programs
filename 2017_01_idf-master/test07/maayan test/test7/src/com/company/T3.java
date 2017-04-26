package com.company;

import java.io.*;

/**
 * Created by hackeru on 4/6/2017.
 */
public class T3 {
    public  static  final String[] words= new String[]{"and", "a","the","be","in","a"};
    private File file;
    private String arrayChars;
    private Output output;

    public T3() {
        output=new Output();
    }

    public void menu(){
        output.getOutput("enter path");
        String path=getInputFromUser();
        file=new File(path);
        if(!file.exists()&&file.isFile())
            output.getOutput("error input");
        fillArray();
        tryDecryption();
    }

    private void fillArray() {
        ////בהנחה שהקובץ לא גדול
        arrayChars=new String();
        StringBuilder stringBuilder=new StringBuilder((int) file.length());
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(file);
            int actuallyRead;
            while((actuallyRead = inputStream.read()) != -1) {
              stringBuilder.append((char)actuallyRead);
            }
            arrayChars=stringBuilder.toString();
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
        for (int i = 0; i <Integer.MAX_VALUE; i++) {
           if( decryption(i)) {
               output.getOutput("key:"+i);
               break;
           }
        }
    }

    private boolean decryption(int key) {
        int counter=0;
        for (int i = 0; i <words.length ; i++) {
            String wordEncryption = encryptWord(words[i], key).toString();
            if (arrayChars.indexOf(wordEncryption)!=-1) {
                counter++;
            }
        }
        if(counter>=3)
            return  true;
        return false;
    }

    private String encryptWord(String word, int key) {
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i <word.length() ; i++) {
            stringBuilder.append((char)(word.charAt(i)+key));
        }
        String result=stringBuilder.toString();
        return result;
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
