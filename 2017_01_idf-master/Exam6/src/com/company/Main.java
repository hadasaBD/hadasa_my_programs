package com.company;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }

    public static void mergeFiles(File file1, File file2, File output){
        InputStream inputStreamA = null;
        InputStream inputStreamB = null;
        OutputStream outputStream = null;
        try{
            inputStreamA = new FileInputStream(file1);
            inputStreamB = new FileInputStream(file2);
            outputStream = new FileOutputStream(output);
            int a, b;
            int actuallyReadA, actuallyReadB;
            byte[] aBytes = new byte[4];
            byte[] bBytes = new byte[4];
            actuallyReadA = inputStreamA.read(aBytes);
            actuallyReadB = inputStreamB.read(bBytes);
            a = ByteBuffer.wrap(aBytes).getInt();
            b = ByteBuffer.wrap(bBytes).getInt();

            while(actuallyReadA == 4 && actuallyReadB == 4){
                if(a < b){
                    outputStream.write(aBytes);
                    actuallyReadA = inputStreamA.read(aBytes);
                    a = ByteBuffer.wrap(aBytes).getInt();
                }else{
                    outputStream.write(bBytes);
                    actuallyReadB = inputStreamB.read(bBytes);
                    b = ByteBuffer.wrap(bBytes).getInt();
                }
            }
            if(actuallyReadA == 4){
                do{
                    outputStream.write(aBytes);
                    actuallyReadA = inputStreamA.read(aBytes);
                }while(actuallyReadA == 4);
            }
            if(actuallyReadB == 4){
                do{
                    outputStream.write(bBytes);
                    actuallyReadB = inputStreamB.read(bBytes);
                }while(actuallyReadB == 4);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStreamA != null)
                    inputStreamA.close();
                if(inputStreamB != null)
                    inputStreamB.close();
                if(outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static Map<String, Integer> countWords(File file){
        Map<String, Integer> words = new HashMap<>();
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(file);
            int length = 0;
            char[] chars = new char[50];
            int nextByte;
            while((nextByte = inputStream.read()) != -1){
                char c = (char)nextByte;
                if(c == ' '){
                    String word = new String(chars, 0, length);
                    Integer currentCount = words.get(word);
                    if(currentCount == null){
                        words.put(word, 1);
                    }else{
                        words.put(word, currentCount + 1);
                    }
                    length = 0;
                }else{
                    chars[length] = c;
                    length++;
                }
            }
            if(length > 0){
                String word = new String(chars, 0, length);
                Integer currentCount = words.get(word);
                if(currentCount == null){
                    words.put(word, 1);
                }else{
                    words.put(word, currentCount + 1);
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
        return words;
    }

    public static int[] smallest(File file, int k){
        int[] smallest = new int[k];
        InputStream inputStream = null;
        try{
            inputStream = new FileInputStream(file);
            byte[] intBytes = new byte[4];
            int actuallyRead;
            int count = 0;
            while((actuallyRead = inputStream.read(intBytes)) != -1){
                int num = ByteBuffer.wrap(intBytes).getInt();
                smallest[count] = num;
                count++;
                if(count == k)
                    break;
            }
            if(count < k){
                //TODO: copy to array of size count
                return smallest;
            }
            MaxHeap maxHeap = new MaxHeap(smallest);
            while((actuallyRead = inputStream.read(intBytes)) != -1){
                int num = ByteBuffer.wrap(intBytes).getInt();
                if(num < maxHeap.getMax()){
                    maxHeap.extractMax();
                    maxHeap.insert(num);
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

        return smallest;
    }
}
















