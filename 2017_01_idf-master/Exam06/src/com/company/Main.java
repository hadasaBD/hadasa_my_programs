package com.company;



public class Main {

    public static void main(String[] args) {
        MyClass<? super Dog> myClass = new MyClass<Animal>();

    }
}
class Animal{

}
class Dog extends Animal{

}

class Poodle extends Dog{

}

class MyClass<T>{

}

interface BI {} 
interface DI extends BI {}
class X <T extends DI> { } 