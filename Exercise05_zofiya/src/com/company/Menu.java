package com.company;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Random;

/**
 * Created by hackeru on 3/15/2017.
 */
public class Menu implements Listener {
    public static final String ENCRYPT = "1";
    public static final String DECRYPT = "2";
    public static final String XOR_REVERSE = "1";
    public static final String CAESAR_MULTI = "2";
    public static final String MULT = "3";
    public static final String REVERS = "4";

    Output myOutput; //= new ScreenOutput();
    Input myInput ;//= new ScreenInput();
    FileOperations myFileOperations = new FileOperations();
    String filePathString;
    File filePath;
    Operations crypt;
    String input;
    int key1,key2;
    Algorithms file;
    DoubleAlgorithm doubleAlgorithm=null;

    public Menu(Output myOutput, Input myInput) {
        this.myOutput = myOutput;
        this.myInput = myInput;
    }


    public void printMenu() {

        myOutput.output("please choose:\n 1. If you want to encrypt the file Press 1\n 2. If you want to decrypt the file Press 2\n 0. exit\n your choice:");
        chooseOption();

    }

    public void chooseOption() {
        String input = "";
        while (!(input = myInput.input()).equals("0")) {
            myOutput.output(myChoice(input));
            printMenu();
        }
    }

    public String myChoice(String input) {

        if (input.length() != 0) {
            switch (input) {
                case ENCRYPT:
                    key1=(Integer) IntKey.getKey("1");
                    FileOperations.writeObjectToFile(new File("Z:\\zofiya\\Exercisess\\Exercises\\key1.bin"),key1);
                    key2=(Integer) IntKey.getKey("1");
                    FileOperations.writeObjectToFile(new File("Z:\\zofiya\\Exercisess\\Exercises\\key2.bin"),key2);
                    selectionAlgorithm();
                    getPathFromUser();
                    doubleAlgorithm.encrypted(filePath,key1);
                    return "encrypt";
                case DECRYPT:
                    key1=(Integer)FileOperations.readObjectFromFile(new File("Z:\\zofiya\\Exercisess\\Exercises\\key1.bin"));
                    key2=(Integer)FileOperations.readObjectFromFile(new File("Z:\\zofiya\\Exercisess\\Exercises\\key2.bin"));
                    selectionAlgorithm();
                    getPathFromUser();
                    doubleAlgorithm.decrypted(filePath,key1);
                    return "decrypt";
                case "0":
                    return "exit" ;

                default:
                    return "invalid option. try again.";
            }
        }
        return "Something went wrong. Please try again";
    }

    // פונקציה שקולטת את הנתיב מהמשתמש
    public void getPathFromUser() {
        myOutput.output("Enter a file path:");
        filePathString = myInput.input();
        while (!(myFileOperations.checkpath(filePathString))) {
            filePathString = myInput.input();
        }
        filePath = new File(filePathString);
    }

    public void selectionAlgorithm(){
        myOutput.output("Select the algorithm to use:\n 1.XorAlgorithm& ReverseAlgorithm press  1\n 2. Caesar& MultAlgorithm Press 2\n your choice:");
        input=myInput.input();
        if (input.length()!=0){
            switch (input) {
                case XOR_REVERSE:{
                    doubleAlgorithm= new DoubleAlgorithm(new XorAlgorithm(),new ReverseAlgorithm(),key2);
                    break;
                }
                case CAESAR_MULTI:{
                    if (key2%2==0||key2==0)
                        key2+=1;
                    doubleAlgorithm= new DoubleAlgorithm(new Caesar(),new MultAlgorithm(),key2);
                    break;
                    }
                default:
                    myOutput.output("invalid option. try again.");
                    selectionAlgorithm();

                }
            }
    }

    @Override
    public void StartDetect() {
        myOutput.output("the action is start \nthe time now is"+System.nanoTime() );
    }

    @Override
    public void EndDetect() {
        myOutput.output("the action is end \nthe time now is" + System.nanoTime());
    }
}
