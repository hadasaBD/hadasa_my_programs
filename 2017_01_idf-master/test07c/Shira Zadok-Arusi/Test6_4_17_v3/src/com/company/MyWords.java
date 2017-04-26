package com.company;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hackeru on 4/19/2017.
 */
public class MyWords {

    private Set<String> wordSet;

    public MyWords() {
        wordSet=new HashSet<String>();
    }

    public  Set<String> getWords() {
        return wordSet;
    }

    public void setWords(Set<String> words) {
        this.wordSet = words;
    }

/*    public static void printList(){
        for (String word:Main.words) {
            output.output(word);
        }
    }*/



    public boolean addWord(String word){
        boolean success=wordSet.add(word);
        return success;
        //  output.output("The "+i+" word is exist");
    }

    public boolean[] addWords(String words){
        boolean[]isNotExist=new boolean[10];
        for (int i = 0; i <isNotExist.length ; i++) {
            isNotExist[i]=true;
        }

        int i=0;
        int indexOf = words.indexOf(',');
        while (indexOf != -1) {
            isNotExist[i]=addWord(words.substring(0, indexOf));
            words = words.substring(indexOf + 1);
            indexOf = words.indexOf(',');
            i++;
        }
        if (indexOf != words.length()) {
            isNotExist[i]=addWord(words);
        }
        return isNotExist;
    }


/*    public boolean decrypt(String path) {
        InputStream inputStream=null;
        byte[] bytes=new byte[50];
        try {
            inputStream = new FileInputStream(new File(path));
            int actuallyRead;
            int i = 0;
            while ((actuallyRead = inputStream.read()) != -1) {
                bytes[i] = ((byte) actuallyRead);
                i++;
            }
            for (int j = 1; j < 256; j++) {
                int wordsInFile=0;
                byte decrypt[] = bytes;
                for (int k = 0; k < i; k++)
                    decrypt[k] =  (byte) (decrypt[k] - j);
                String strings = new String(decrypt);
                for (String word:Main.words) {
                    if(strings.indexOf(word)!=-1)
                        wordsInFile++;
                }
                if(wordsInFile>=3){
                    return true;
                    output.output("The decrypt text is:");
                    output.output(strings);
                    output.output("The key is: "+j);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }*/
}