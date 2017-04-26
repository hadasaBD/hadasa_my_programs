package com.company;

import java.io.File;
import java.util.Collection;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Menu menu=new Menu(new InputFromScreen(),new OutputToScreen());
        menu.start();
    }
}
