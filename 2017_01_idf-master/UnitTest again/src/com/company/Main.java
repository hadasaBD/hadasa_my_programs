package com.company;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Main {

    public static void main(String[] args) {
        PrintStream outPrintStream = new PrintStream(new MyOutputStream());
        //System.out = outPrintStream;
    }
}

class MyOutputStream extends OutputStream {

    byte[] bytes = new byte[1024];
    int size;


    @Override
    public void write(int b) throws IOException {
        bytes[size++] = (byte) b;
    }

    @Override
    public void close() throws IOException {
        super.close();

    }
}
