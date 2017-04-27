package com.company;

import java.io.File;
import java.security.InvalidParameterException;

/**
 * Created by hackeru on 2/28/2017.
 */
public class Encryption extends FileOperations {


    public static void encode(){
        if(pathTesting())
            System.out.println("Right path");
        else
            throw new InvalidParameterException("Improper lane");


    }


}
