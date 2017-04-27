package com.company;

/**
 * Created by hackeru on 2/28/2017.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu {

    public static String inputPath;


    public static void printMenu() {
        System.out.println();
        System.out.println("please choose:");
        System.out.println("1. If you want to encrypt the file Press 1");
        System.out.println("2. If you want to decode the file Press 2");
        System.out.println("0. exit");
        System.out.println("your choice: ");
        String input = readInput();
        if (input.length() != 0) {
            switch (input) {
                case "1":
                    Encryption e=new Encryption();
                    e.encode();
                    break;
                case "2":
                    Decoding d=new Decoding();
                    d.decipher();
                    break;
                case "0":
                    System.out.println("bye bye.");
                    return;
                default:
                    System.out.println("invalid option. try again.");
            }
        }
        printMenu();
    }

    public static String readInput(){
        String input = null;
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }


}
