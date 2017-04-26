package com.company;

public class Main {

    public static void main(String[] args) {
        //1. Static nested class
        //2. Inner class
        //3. Local Inner class
        //4. Anonymous inner class


        Shape.Color myColor = new Shape.Color(34,45,100);
        Circle.Point point = new Circle().new Point(4, 5);
        Circle c = new Circle();
        Circle.Point point1 = c.new Point(7, 8);
        Circle.Point point2 = c.new Point(87, 32);
        c.setCenter(point1);

        Dog d = new Dog(){
            @Override
            void bark() {
                System.out.println("boof boof");
            }
        };
        d.bark();

        Dog d2 = new Dog();
        d2.bark();


        Dog d3 = new Dog(){
            @Override
            void bark() {
                System.out.println("boof boof");
            }
        };

        MyAbstractClass myAbstractClass = new MyAbstractClass() {
            @Override
            void myAbstractMethod() {

            }
        };

        Student shira = new Student();
        shira.cityId = Student.JERUSALEM;
        shira.maritalStatus = Student.MARRIED;
        shira.gender = Gender.FEMALE;

        int x5 = 13;
        //int 5x = 13;
        PrinterType myPrinterType = PrinterType.LASER;

        int y;

        if(x5 == 3){

        }else if(x5 == 4){

        }else if(x5 == 5){

        }else if(x5 == 6){

        }else{

        }

        switch (x5){
            case 3:

                break;
            case 4:

                break;
            case  5:

                break;
            case 6:

                break;
            default:

                break;
        }


        switch (myPrinterType){
            case DOTMATRIX:
                System.out.println("dotmatrix");
                break;
            case INKJET:
                System.out.println("inkjet");
                break;
            case LASER:
                System.out.println("laser");
                break;
            case THERMAL:
                break;
            case THREE_DIMENSION:
                break;
        }

        System.out.println(myPrinterType);


    }

    static Dog trainDog(Dog d){
        class TrainedDog extends Dog{
            @Override
            void bark() {
                System.out.println("abcdefghijklmnopqrstuvwxyz");
            }
        }
        TrainedDog trainedDog = new TrainedDog();
        trainedDog.name = d.name;
        return trainedDog;
    }


}
class Dog{
    String name;
    void bark(){
        System.out.println("waf waf... " + name);
    }
}
abstract class MyAbstractClass{
    abstract void myAbstractMethod();
}

enum Gender{
    MALE, FEMALE, OTHER
}

class Student{

    public static final int KFAR_SAVA = 43;
    public static final int RAANANA = 56;
    public static final int JERUSALEM = 23;


    public static final int MARRIED = 45;
    public static final int DIVORCED = 13;
    public static final int SINGLE = 14;
    public static final int WIDOW = 2;

    String firstName;
    String lastName;
    int cityId;
    int maritalStatus;
    Gender gender;

}

enum PrinterType{
    DOTMATRIX(4, "dotmatrix"), INKJET(3, "inkjet"),
    LASER(10, "laser"), THREE_DIMENSION(1, "3D"), THERMAL(20, "thermal");

    private int pageCapacity;
    private String name;

    private PrinterType(int pageCapacity, String name){
        this.pageCapacity = pageCapacity;
        System.out.println("in PrinterType Constructor");
        this.name = name;
    }

    public int getPageCapacity() {
        return pageCapacity;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Car{
    private Car(){

    }
}











