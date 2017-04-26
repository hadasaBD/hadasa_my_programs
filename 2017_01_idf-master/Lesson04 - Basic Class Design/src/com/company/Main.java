package com.company;


//import mypackage.MyClass;
//import mypackage.MyOtherClass;
import mypackage.*;

import static java.lang.Math.PI;
import static java.lang.Math.*;

public class Main {

    public static void main(String[] args) {
        /*
        Dog d = null;
        handleDog(d);
        //System.out.println("end");

        Segment segment = new Segment(new Point(4, 5), null);
        segment.setP1(null);
        Point p = new Point(6, 7);
        segment.setP1(p);
        segment.setP2(new Point(2, 3));
        //d = new Dog();
        //System.out.println(d);
        p.setX(13);
        System.out.println(segment);
        System.out.println(sumOfDigits(123));*/

        Dog d = new Poodle();
        d.bark();
        handleDog(d);
        //Animal a2 = d;
        Poodle p = (Poodle) d;//downcast   explicit cast
        Animal a = d;  //upcast      implicit cast
        //Cat c = (Cat)d;
        p.roll();
        //d.roll();
        //Cat c = (Cat)a; //downcast
        //c.howl();

        int x = 5;
        long myLong = x;
        x = (int)myLong;


        if(a instanceof Poodle) {
            Poodle p2 = (Poodle) a;
        }





        Point p1 = new Point(4, 5);
        Point p2 = new Point(4, 5);
        //p1 = null;
        //p2 = null;
        //Point p2 = p1;
        if(p1.equals(p2)){//p1 == p2
            System.out.println("p1 == p2");
        }else{
            System.out.println("p1 != p2");
        }
        //mypackage.MyClass myClass;
        MyClass myClass;
        MyOtherClass myOtherClass;

        double perimeter = 2.0 * PI * 10.0;
    }




    public static void handleDog(Dog d){
        if(d != null)
            d.bark();
    }

    public static int sumOfDigits(int num){
        int result = 0;

        while (num != 0){
            int lastDigit = num % 10;
            num /= 10;

            result += lastDigit;

        }
        return result;
    }

    public static int largestDigit(int num){
        int result = 0;

        while (num != 0){
            int lastDigit = num % 10;
            num /= 10;

            if(lastDigit > result)
                result = lastDigit;

        }
        return result;
    }


    public static int reverseDigits(int num) {
        int reversed = 0;

        while (num != 0){
            int lastDigit = num % 10;
            num /= 10;

            reversed = reversed * 10 + lastDigit;

        }
        return reversed;
    }

    public static int finalSumOfDigits(int num){
        int result = num;
        while (result >= 10)
            result = sumOfDigits(result);
        return result;
    }

    public static boolean isDivisibleBy3(int num){
        int sumOfDigits = finalSumOfDigits(num);
        return sumOfDigits == 3 || sumOfDigits == 6 ||
                sumOfDigits == 9;
    }





}

class Animal{
    public void goToSleep(){
        System.out.println("going to sleep");
    }
}


class Dog extends Animal{
    void bark(){
        System.out.println("waf.. waf...");
    }

    @Override
    public String toString() {
        return "I am a dog";
    }
}

class Cat extends Animal{
    void howl(){
        System.out.println("miaooooooooo");
    }

}

class Poodle extends Dog{
    @Override
    void bark() {
        System.out.println("bfff..  bff...");
    }
    public void roll(){
        System.out.println("rolling");
    }
}


/*
class A extends B{

}

class B extends A{

}
*/





class A{
    private int x;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
class B extends A{
    private int x;
    void func(){
        System.out.println(x);
        System.out.println(getX());
    }
}



