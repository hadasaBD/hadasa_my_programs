package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by hackeru on 3/21/2017.
 */
public class FileHandler {

    public static void CloseFile(OutputStream outputStream, InputStream inputStream) {

        if (outputStream != null)
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        if (inputStream != null)
            try{
                inputStream.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }

    }

}
