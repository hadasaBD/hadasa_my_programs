package com.company;

import java.util.Set;

/**
 * Created by hackeru on 4/19/2017.
 */
public class Result {

    private int key;
    private String string;


    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public boolean decrypt(Set<String> words, String path) {

        Decryptor decryptor=new Decryptor();
        for (int i = 1; i < 256; i++) {
            byte[] file=decryptor.decrypt(path,i);
            int wordsInFile = 0;
            String strings = new String(file);
            for (String word : words) {
                int indexOf=strings.indexOf(word);
                if (indexOf != -1) {

                    if((indexOf==0||((strings.charAt(indexOf-1)==' ')||(strings.charAt(indexOf-1)=='.')||(strings.charAt(indexOf-1)==','))))
                            //&& ((indexOf+word.length()==strings.length())||(indexOf+word.length()==' ')||(indexOf+word.length()=='.')||(indexOf+word.length()==',')))
                    wordsInFile++;
                }

            }
            if (wordsInFile >= 3) {

                this.setString(strings);
                this.setKey(i);
                return true;

            }

        }
            return false;
    }


}