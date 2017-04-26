package com.company;

import java.io.*;
import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) {

        /*for (int i = 0; i < 256; i++) {
            byte b = (byte)i;
            int x = b & 0xFF;
            if(i != x){
                System.out.println("didn't work for " + i);
            }
        }*/



        String[] strings1 = {"hello, how are you?",
            "Today is sunday", "Today is a beautiful day"};


	    File file = new File("/Users/eladlavi/Desktop/MyStrings.txt");
        OutputStream outputStream = null;
        try{
            outputStream = new FileOutputStream(file);
            for (int i = 0; i < strings1.length; i++) {


                byte[] stringBytes = strings1[i].getBytes();
                byte b = (byte)stringBytes.length;
                outputStream.write(b);
                outputStream.write(stringBytes);

                /*outputStream.write(strings1[i].getBytes());
                if(i<strings1.length-1)
                    outputStream.write("&".getBytes());*/
            }
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
        }




        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(file);
            /*StringBuilder stringBuilder = new StringBuilder();
            byte[] buffer = new byte[8];
            int actuallyRead;
            while((actuallyRead = inputStream.read(buffer)) != -1){
                stringBuilder.append(new String(buffer, 0, actuallyRead));
            }
            inputStream.close();
            inputStream = null;
            String[] strings2 = stringBuilder.toString().split("&");
            for(String s : strings2)
                System.out.println(s);*/

            /*char[] buffer = new char[500];
            int pos = 0;
            int nextByte;
            while((nextByte = inputStream.read()) != -1){
                char c = (char) nextByte;
                if(c != '&'){
                    buffer[pos] = c;
                    pos++;
                }else{
                    String s = new String(buffer, 0, pos);
                    System.out.println(s);
                    pos = 0;
                }
            }
            if(pos > 0) {
                String s = new String(buffer, 0, pos);
                System.out.println(s);
            }*/


            int actuallyRead;
            int nextByte;
            while((nextByte = inputStream.read()) != -1){
                byte[] stringBytes = new byte[nextByte];
                actuallyRead = inputStream.read(stringBytes);
                if(actuallyRead != nextByte){
                    //TODO: something really bad happened
                }else{
                    String s = new String(stringBytes, 0, actuallyRead);
                    System.out.println(s);
                }
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


        /*Dog d = new Dog("Snoopy", 121, 200);
        byte[] bytes = d.getBytes();
        Dog d2 = new Dog(bytes);
        System.out.println(d.getName());
        System.out.println(d.getAge());
        System.out.println(d.getColor());*/

    }
}


class Dog{
    private String name;
    private int age;
    private int color;

    public Dog(String name, int age, int color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public Dog(byte[] bytes){
        int length = bytes[0] & 0xFF;
        this.name = new String(bytes, 1, length);
        this.age = ByteBuffer.wrap(bytes).getInt(1 + length);
        this.color = ByteBuffer.wrap(bytes).getInt(1 + length + 4);
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public byte[] getBytes(){
        byte[] nameBytes = name.getBytes();
        byte[] bytes = new byte[1 + nameBytes.length + 4 + 4];
        bytes[0] = (byte)nameBytes.length;
        for (int i = 0; i < nameBytes.length; i++) {
            bytes[1 + i] = nameBytes[i];
        }
        ByteBuffer.wrap(bytes).putInt(1 + nameBytes.length, age);
        ByteBuffer.wrap(bytes).putInt(1 +nameBytes.length + 4, color);
        return bytes;
    }

}
