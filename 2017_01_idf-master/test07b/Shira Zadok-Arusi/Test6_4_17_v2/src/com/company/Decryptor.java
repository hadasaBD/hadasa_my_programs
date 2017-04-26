package com.company;

import java.io.*;

/**
 * Created by hackeru on 4/19/2017.
 */
public class Decryptor {

    public byte[] decrypt(String path, int j) {

        InputStream inputStream = null;
        byte[] bytes = new byte[50];
        try {
            inputStream = new FileInputStream(new File(path));
            int actuallyRead;
            int i = 0;
            while ((actuallyRead = inputStream.read()) != -1) {
                bytes[i] = ((byte) actuallyRead);
                i++;
            }
            byte decrypt[] = new byte[i];
            for (i = 0; i < decrypt.length; i++)
                decrypt[i] = (byte) (bytes[i] - j);
            return decrypt;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


 /*    for (int j = 1; j < 256; j++) {
                int wordsInFile=0;
                 = bytes;

                String strings = new String(decrypt);
                for (String word:) {
                    if(strings.indexOf(word)!=-1)
                        wordsInFile++;
                }
                if(wordsInFile>=3){
                    return true;
                    this.setString(strings);
                    this.setKey(j);
                    break;
                }

            }*/
}