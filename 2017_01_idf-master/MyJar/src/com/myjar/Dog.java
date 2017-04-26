package com.myjar;

/**
 * Created by eladlavi on 19/03/2017.
 */
public class Dog {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void bark(){
        System.out.println("waf waf my name is " + name);
    }
}
