package com.company;

/**
 * Created by eladlavi on 14/03/2017.
 */
public class Dog {
    private String name;
    private int count;


    public Dog(String name) {
        this.name = name;
        count = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String bark(){
        return "waf waf " + name + " " + count++;
    }
}
