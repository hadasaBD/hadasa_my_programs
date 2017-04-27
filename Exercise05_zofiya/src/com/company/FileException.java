package com.company;

/**
 * Created by hackeru on 3/21/2017.
 */
public class FileException extends Throwable {

    public FileException() throws Exception {
        throw new Exception("File not opened") ;

    }


}
