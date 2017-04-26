package com.company;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File file = new File("/Users/eladlavi/Documents/");
        File largest = getLargestFile(file);
        System.out.println(largest);

    }

    public static boolean fileExistsInFolder(File folder, String fileName){
        if(!folder.exists())
            throw new InvalidParameterException("folder does not exist");
        if(!folder.isDirectory())
            throw new InvalidParameterException("this is not a folder");
        File[] files = folder.listFiles();
        for(File f : files){
            if(f.isFile() && f.getName().equals(fileName))
                return true;
        }
        for(File f : files){
            if(f.isDirectory() && fileExistsInFolder(f, fileName))
                return true;
        }
        return false;
    }


    public static void fileChooser() throws Exception {
        File file = new File("/Users/");
        if (!file.exists())
            throw new Exception("does not exist");
        if (!file.isDirectory())
            throw new Exception("is not a directory");
        while(true) {

            System.out.println("please choose file within " + file.getAbsolutePath());
            System.out.println("enter 0 to go to parent directory");
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                System.out.println((i + 1) + " " + files[i].getName());
            }
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(System.in));
            String line = bufferedReader.readLine();
            int option = 0;

            try {
                option = Integer.valueOf(line);
            } catch (Exception ex) {
                System.out.println("must enter a number");
                continue;
            }
            if (option < 0 || option > files.length)
                throw new Exception("invalid option");
            if(option == 0){
                if(file.getParentFile() != null)
                    file = file.getParentFile();
                continue;
            }
            File f = files[option - 1];
            if (f.isFile()) {
                System.out.println("you have chosen the file:");
                System.out.println(f.getAbsolutePath());
                break;
            } else {
                file = f;
            }
        }
    }

    public static File getLargestFile(File file){
        //TODO: exists and a directory
        long maxSize = -1L;
        File largestFile = null;
        File[] files = file.listFiles();
        for(File f : files){
            long size;
            File currentFile = null;
            if(f.isHidden())
                continue;

            if(f.isFile()){
                size = f.length();
                currentFile = f;
            }else{
                currentFile = getLargestFile(f);
                if(currentFile != null)
                    size = currentFile.length();
                else
                    size = -1L;
            }
            if(size > maxSize){
                maxSize = size;
                largestFile = currentFile;
            }
        }
        return largestFile;
    }


}
