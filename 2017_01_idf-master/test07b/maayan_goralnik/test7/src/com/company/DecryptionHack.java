package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Set;

/**
 * Created by hackeru on 4/6/2017.
 */
public class DecryptionHack {
    private File file;
    private String charsFromFile;
    private MatchFound matchFound;
    private Set<String> words;
    private boolean exit;
    private  TreadDecryptionT4 treadDecryption1;
    private TreadDecryptionT4 treadDecryption2;
    public void stop() {
        this.exit = true;
        if(treadDecryption1!=null){
            treadDecryption1.interrupt();
            treadDecryption1.setStop();
        }
        if(treadDecryption2!=null){
            treadDecryption2.interrupt();
            treadDecryption2.setStop();
        }
    }

    public DecryptionHack(File file, Set<String> words, MatchFound matchFound) {
        this.file = file;
        this.matchFound = matchFound;
        this.words=words;
    }
    public void decrypt(){
        fillArray();
        tryDecryption();
    }

    private void fillArray() {
        ////בהנחה שהקובץ לא גדול
        charsFromFile =new String();
        StringBuilder stringBuilder=new StringBuilder((int) file.length());
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(file);
            int actuallyRead;
            while((actuallyRead = inputStream.read()) != -1) {
                stringBuilder.append((char)actuallyRead);
            }
            charsFromFile =stringBuilder.toString();
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
        for (byte i = 0; i <Byte.MAX_VALUE/2&&!exit; i++) {
            treadDecryption1=new TreadDecryptionT4(charsFromFile,words,matchFound,i);
            treadDecryption2=new TreadDecryptionT4(charsFromFile,words,matchFound, (byte) (Byte.MAX_VALUE-i));
            treadDecryption1.start();
            treadDecryption2.start();
        }
    }
/*    private String encryptWord(String word, byte key) {
        byte newWord[]=new  byte[word.length()];
        newWord=word.getBytes();
        for (int i = 0; i <newWord.length ; i++) {
           newWord[i]= (byte) (newWord[i]+key);
        }
        String result=new String(newWord);


        return result;
    }*/
    public  interface MatchFound{
        void found(int key,String file);
    }
}
