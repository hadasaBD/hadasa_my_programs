
package com.company;
import java.io.*;
import java.util.HashSet;
import java.util.Set;


public class Cipher {
    File file;
    private static Set<String> commonWords = new HashSet<>();
    ThreadCipher threadCipher1;
    ThreadCipher threadCipher2;

    public Cipher(File file) {
        this.file = file;
        commonWords.add("the");
        commonWords.add("be");
        commonWords.add("to");
        commonWords.add("of");
        commonWords.add("and");
        commonWords.add("a");
        commonWords.add("in");
    }

    void HandleWithFile() {
        InputStream inputStream = null;
        int oneByte;
        int i = 0;
        try {
            inputStream = new FileInputStream(file);
            int l = (int) file.length();
            byte[] bytes = new byte[l];
            while ((oneByte = inputStream.read()) != -1) {
                bytes[i] = (byte) oneByte;
                i++;
            }
            String stringAllFile = new String(bytes, 0, i);

            ThreadCipher.FoundListener listener = key -> {
                printFile(stringAllFile, key);
            };
            threadCipher1 = new ThreadCipher(bytes, commonWords, 0, 127, listener);
            threadCipher2 = new ThreadCipher(bytes, commonWords, 128, 256, listener);
            threadCipher1.start();
            threadCipher2.start();

            try {
                threadCipher1.join();
                threadCipher2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void printFile(String stringFileDecrypt,int key) {
        System.out.println("The key is: " + key);
        System.out.println(" The file is: ");
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < stringFileDecrypt.length(); i++) {
            stringBuilder.append((char) (stringFileDecrypt.charAt(i) - key));
        }
        String fileEncrypt = new String(stringBuilder.toString()) ;
        System.out.println(fileEncrypt);
        return;
    }

}





