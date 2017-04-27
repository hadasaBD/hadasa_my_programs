package com.company;

import java.io.File;

/**
 * Created by hackeru on 2/28/2017.
 */
public abstract class FileOperations {
    public static File file;


    public static boolean pathTesting(){//String filePathString
        int counter=0;
        String filePathString;
        System.out.println("Enter a file path:");
        filePathString = Menu.readInput();

        while(!new File(filePathString).exists() && counter < 3){
            counter++;
            if (counter==3)
                return false;
            System.out.println("Your path doesn't seem to exist. Please try again: ");
            filePathString = Menu.readInput();

        }

        while(!(new File(filePathString).isFile()&&new File(filePathString).canRead()&&new File(filePathString).canWrite()&& counter < 3)) {
            counter++;
            if (counter==3)
                return false;

            System.out.println("Your path doesn't seem to be valid. Please try again: ");
            filePathString = Menu.readInput();

        }
        file = new File(filePathString);
        return true;
    }









}
