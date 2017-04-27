package com.company;


import java.io.File;

/**
 * Created by hackeru on 2/28/2017.
 */
public class FileOperations {
    //  public static  File filePath;




    public boolean checkpath(String filePathString){//
        //  int counter=0;
        /*String filePathString;
        System.out.println("Enter a file path:");
        filePathString = Menu.readInput();*/
       if (!new File(filePathString).exists()||!(new File(filePathString).isFile())){// && counter < 3
           /* counter++;
            if (counter==3)
                return false;
            System.out.println("Your path doesn't seem to exist. Please try again: ");*/
            return false;
        }
        /*while(!(new File(filePathString).isFile()&&new File(filePathString).canRead()&&new File(filePathString).canWrite())) {//
            //System.out.println("Your path doesn't seem to be valid. Please try again: ");
            filePathString = Menu.readInput();

        }*/
        //filePath = new File(filePathString);
        return true;
    }


}