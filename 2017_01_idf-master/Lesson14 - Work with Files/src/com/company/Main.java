package com.company;

import java.io.*;
import java.nio.ByteBuffer;
import java.security.InvalidParameterException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	    File file = new File("/Users/eladlavi/Desktop/MyFile.txt");

	    reverseWords(file);

        //File file = new File("C:\\Users\\eladlavi\\Desktop\\MyFile.txt");
        //System.out.println(file.exists());
        /*OutputStream outputStream = null;
        try{
            outputStream = new FileOutputStream(file);
            outputStream.write("how are you".getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }*/


        /*InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(file);

            byte[] buffer = new byte[2];
            *//*int actuallyRead = inputStream.read(buffer);
            if(actuallyRead != -1){
                String s = new String(buffer, 0, actuallyRead);
                System.out.println(s);
            }*//*
            int actuallyRead;
            StringBuilder stringBuilder = new StringBuilder();
            while((actuallyRead = inputStream.read(buffer)) != -1) {
                stringBuilder.append(
                        new String(buffer, 0, actuallyRead));
            }
            System.out.println(stringBuilder.toString());

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
        }*/


        /*OutputStream outputStream = null;
        try{
            outputStream = new FileOutputStream(file);
            byte b = 17;
            outputStream.write(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(file);
            int oneByte = inputStream.read();
            if(oneByte == -1){
                System.out.println("end of stream");
            }
            System.out.println(oneByte);
            byte b = (byte)oneByte;
            System.out.println(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //writeRandomIntegersFile(file);
        //System.out.println(getMaxInteger(file));

    }


    static void writeRandomIntegersFile(File file){
        Random random = new Random(System.currentTimeMillis());
        int numberOfIntegers = random.nextInt(100000) + 100000;
        OutputStream outputStream = null;
        int max = Integer.MIN_VALUE;
        try{
            outputStream = new FileOutputStream(file);
            byte[] bytes = new byte[4];
            for (int i = 0; i < numberOfIntegers; i++) {
                int nextInt = random.nextInt();
                if(nextInt > max)
                    max = nextInt;
                ByteBuffer.wrap(bytes).putInt(nextInt);
                outputStream.write(bytes);





            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        System.out.println("for testing max is " + max);

    }


    static int getMaxInteger(File file){
        InputStream inputStream = null;
        int max = Integer.MIN_VALUE;
        try{
            inputStream = new FileInputStream(file);
            byte[] bytes = new byte[4];
            int actuallyRead;
            while((actuallyRead = inputStream.read(bytes)) != -1){
                if(actuallyRead != 4)
                    throw new InvalidParameterException("עבדת עלי זה בכלל לא מספרים");
                int num = ByteBuffer.wrap(bytes).getInt();
                if(num > max)
                    max = num;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return max;
    }


    public static void reverseWords(File file){
        File tempFile = new File("/Users/eladlavi/Desktop/temp.txt");
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(file);
            outputStream = new FileOutputStream(tempFile);
            char[] chars = new char[50];
            int wordLength = 0;
            int oneByte;
            while((oneByte = inputStream.read()) != -1){
                byte b = (byte)oneByte;
                char c = (char)b;
                if(c == ' '){
                    for (int i = wordLength-1; i >= 0; i--) {
                        outputStream.write(chars[i]);
                    }
                    outputStream.write(' ');
                    wordLength = 0;

                }else{
                    chars[wordLength] = c;
                    wordLength++;
                }
            }
            for (int i = wordLength-1; i >= 0; i--) {
                outputStream.write(chars[i]);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


}
