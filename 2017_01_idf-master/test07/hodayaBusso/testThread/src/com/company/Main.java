package com.company;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {


    //Set<String> words;

    public static void main(String[] args) {
        // write your code here

        Set<String> words = new HashSet<>();
        while (true) {

            Menu menu = new Menu(words);
            menu.start();
        }

    }
}


