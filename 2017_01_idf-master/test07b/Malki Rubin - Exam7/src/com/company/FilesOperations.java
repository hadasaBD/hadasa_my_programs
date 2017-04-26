package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Rubin on 16/04/2017.
 */
public class FilesOperations {

    public static byte[] getFileData(File fileData){
        byte[] bytes=new byte[(int)fileData.length()];
        InputStream inputStream =null;
        try {
            inputStream = new FileInputStream(fileData);
            int oneByte;
            int pos=0;
            while ((oneByte=inputStream.read())!=-1){
                bytes[pos++]=(byte)oneByte;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(inputStream!=null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return bytes;
    }

    public static byte[] getFileData2(File file){
        byte[] fileData=null;

        try {
            fileData = Files.readAllBytes(Paths.get(file.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileData;
    }


}

