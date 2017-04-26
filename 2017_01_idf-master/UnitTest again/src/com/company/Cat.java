package com.company;

/**
 * Created by eladlavi on 14/03/2017.
 */
public class Cat {
    private String name;
    private int count;


    public Cat(String name) {
        this.name = name;
        count = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String purr(){
        return "grrrr... " + name + " " + count;
    }
}
