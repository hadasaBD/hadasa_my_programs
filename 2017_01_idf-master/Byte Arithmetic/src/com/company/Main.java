package com.company;

public class Main {

    public static void main(String[] args) {
	    byte b = 127;
	    b = (byte)((int)b + 200);
        System.out.println(b);
        b = (byte)((int)b - 200);
        System.out.println(b);

    }
}
