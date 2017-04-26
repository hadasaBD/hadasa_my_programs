package com.company;

/**
 * Created by eladlavi on 22/03/2017.
 */
public class Menu {

    private KeyGenerator keyGenerator;
    private EncryptionAlgo encryptionAlgo;

    public Menu(KeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    public void func1(){
        int key = keyGenerator.getKey();
    }

    public void func2(){
        encryptionAlgo.setKey(keyGenerator.getKey());
        encryptionAlgo.encrypt();
    }
}











class Dog{
    private String name;
    public Dog(){
        name = "";
    }

    public Dog(String name){
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}