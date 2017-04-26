package com.company;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Set<String> list = new HashSet<>();
        Encryption encryption = new Caesar();
        File file= new File("C:\\Users\\HACKERU.HACKERU3\\Desktop\\New Text Document.txt");
        encryption.encrypt(20,file);

        Menu menu = new Menu();
        menu.getMenu();
    }

}
