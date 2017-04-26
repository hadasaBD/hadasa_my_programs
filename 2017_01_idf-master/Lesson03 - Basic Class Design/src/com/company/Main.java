package com.company;

import graphicshape.*;

public class Main {

    public static void main(String[] args) {
	    /*
        Circle circle1;
	    circle1 = new Circle();
	    int x;
        //System.out.println(x);
        //System.out.println(circle1.x);
        //System.out.println(circle1.area());


        //circle1.fillColor(4.0f, 5, 6);


        //HappyBrithday.wish("Sapir");
        */


	    Number[] nums = {5, 7, 4.3f, new Double(3),
                new RationalNumber(5,2)};
        System.out.println(sum(nums));

        RationalNumber num1 = new RationalNumber(1,2);
        RationalNumber num2 = new RationalNumber(1,4);
        num1.add(num2);


        System.out.println(RationalNumber.gcd(15, 10));

        Square square = new Square(5);
        square.setHeight(15);
        System.out.println(square.perimeter());

        Shape s = new Square(6);
        //s.setSide(5);
        //Circle c = new Square(3);
        System.out.println(s.area());


        Circle c = new Circle(3, 4, 10);
        c.setRadius(6);


        Point p = new Point(4,5);
        Segment segment = new Segment(p,
                new Point(7, 4));

        p = segment.getP1();
        p.setX(19);
        //segment.A();


    }


    static boolean divisibleBy7(int num){
        if(num<0)
            return divisibleBy7(-num);
        if(num == 0 || num == 7)
            return true;
        if(num < 10)
            return false;
        return divisibleBy7(num/10 - 2*(num-num/10*10));
    }


    public static double sum(Number[] nums){
        double sum = 0.0;
        /*for (int i = 0; i < nums.length; i++) {
            Number num = nums[i];
            sum += num.doubleValue();
        }*/
        for(Number num : nums){
            sum += num.doubleValue();
        }
        return sum;
    }


}


class Animal{
    void makeSound(){
        System.out.println("aaaaa");
    }
}
class Dog extends Animal{
    void bark(){
        System.out.println("waf waf");
    }

    @Override
    void makeSound() {
        bark();
    }
}

class Cat extends Animal{
    void howl(){
        System.out.println("grrrrrrr....");
    }

    @Override
    void makeSound() {
        howl();
    }
}

class Poodle extends Dog{
    @Override
    void bark() {
        System.out.println("bfff.. bffff..");
    }
}





abstract class A{
    abstract void func();
}
abstract class B extends A{
    void func2(){
        System.out.println("func2");
    }
}
class C extends B{

    @Override
    void func() {

    }
}










