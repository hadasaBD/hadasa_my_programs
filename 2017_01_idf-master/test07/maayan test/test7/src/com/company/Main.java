package com.company;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Set<String> list =new HashSet<>();
       // T1 t1=new T1(list);
       // t1.menu();
       // T2 t2=new T2(list);
       // t2.menu();
        Encryption encryption=new Caesar();
        encryption.encrypt(0,new File("C:\\Users\\HACKERU.HACKERU3\\Desktop\\f\\test.txt"));
        T3 t3=new T3();
        t3.menu();
    }

}
