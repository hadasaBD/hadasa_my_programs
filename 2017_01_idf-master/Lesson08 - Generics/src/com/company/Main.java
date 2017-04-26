package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        //eladlavi


        /*
        LinkedList<Dog> linkedList = new LinkedList<Dog>();
        linkedList.add(new Dog());
        LinkedList<Cat> cats = new LinkedList<>();

        //linkedList.add(new Cat());
        //Cat c = (Cat)linkedList.get(0);

        Pair<Dog, Cat> pair = new Pair<>(new Dog(), new Cat());
        Pair<Dog, Dog> pair2 = new Pair<>(new Dog(), new Dog());


        //raw types
        List list = new LinkedList<Object>();
        list.add("First");
        list.add("Second");
        list.add(123);
        List<String> strings = list;
        List<Integer> ints = list;
        //String s = strings.get(2);
        //System.out.println(s);
        //Integer i = ints.get(1);
        //System.out.println(i);

        Animal a = new Dog();
        Dog d = (Dog)a;

        LinkedList<Animal> animals = new LinkedList<Animal>();
        LinkedList animals2 = animals;
        LinkedList<Animal> animals3 = animals2;
        //animals.add(new Cat());
        animals.add(new Dog());
        animals.add(new Dog());
        LinkedList<Dog> dogs = new LinkedList<>();
        for (int j = 0; j < animals.size(); j++) {
            dogs.add((Dog)animals.get(j));
        }

        List<?> list2 = animals;

        System.out.println(list2.get(0));

        Pair<?, ?> pair1 = new Pair<Integer, Integer>();
        Integer integer = (Integer) pair1.getObject1();
//        pair1.setObject1(integer);

        */


        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(System.in));
        System.out.println("please enter your name:");
        try {
            //String name = bufferedReader.readLine();
            //System.out.println("your name is: " + name);
            String ageAsString = bufferedReader.readLine();
            int age = Integer.valueOf(ageAsString);
            System.out.println("your age is: " + age);
        } catch (IOException e) {
            System.out.println("error reading...");
        }catch (NumberFormatException exception){
            System.out.println("must enter an integer");
        }


        String s = "hello";
        byte[] sBytes = s.getBytes();
        char c = (char)sBytes[0];
        System.out.println(sBytes[0]);

        try {
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static <T> void fill(List<T> list, int quantity, T obj){
        for (int i = 0; i < quantity; i++) {
            list.add(obj);
        }
    }
}

class Animal{

}

class Dog extends Animal{

}
class Cat extends Animal{

}
