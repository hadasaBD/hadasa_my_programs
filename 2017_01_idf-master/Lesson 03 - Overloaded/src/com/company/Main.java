package com.company;

public class Main {

    public static void main(String[] args) {
	    /*byte b = 9;
	    aMethod(b);
        aMethod(9);
        Integer i = 9;
        aMethod(i);
        aMethod("9");*/


	    //aMethod(9);//compilation error because compiler is afraid to lose information
        //aMethod(9, 10); compilation error because compiler can't choose
        //both options match exactly the same
    }

    /*
    public static void aMethod(int val){ System.out.println("int"); }
    public static void aMethod(short val){ System.out.println("short"); }
    public static void aMethod(Object val){ System.out.println("object"); }
    public static void aMethod(String val){ System.out.println("String"); }
    */

    //public static void aMethod(byte val){ System.out.println("byte"); }
    //public static void aMethod(short val){ System.out.println("short"); }

    public static void aMethod(long val1, int val2){ System.out.println("long,int"); }
    public static void aMethod(int val1, long val2){ System.out.println("int,long"); }
}
