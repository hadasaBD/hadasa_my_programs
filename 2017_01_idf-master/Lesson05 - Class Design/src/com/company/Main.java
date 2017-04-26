package com.company;

public class Main {

    public static void main(String[] args) {
	    /*Point p1 = new Point(9, 57);
        System.out.println(p1.hashCode());

        D d = new D();
        d.y = 5;
        D.x = 5;

        D.func7();*/

        //System.out.println(Dog.getCounter());
        Dog d1 = new Dog();
	    Dog d2 = new Dog();
	    //d1 = new Dog();
        Dog d3 = d1;
        //System.out.println(Dog.getCounter());
        //MyClass myClass = new MyClass(4);
        //System.out.println(MyClass.x);

        System.out.println("test");
        new MyClass(100);
    }
}

abstract class A{
    void func1(){
        System.out.println("in func1");
    }
    abstract void func2();
}
abstract class B extends A{
    void func3(){
        System.out.println("in func3");
    }
    abstract void func4();
}
class C extends B{
    //int y = 6;
    //int z = y;
    int y;
    int z;


    //final int x = 5;
    final int x;
    public C(){
        this.x = 5;
        this.y = 7;
        y = 6;
        z = y;
    }

    final void func5(final int param){
        //param = 5;
        final int temp = 5;
        //temp++;
    }

    @Override
    void func2() {
        System.out.println("func2");
    }
    @Override
    void func4() {
        System.out.println("func4");
    }
}
class D extends C{

    final Dog dog;
    static int x;
    int y;

    public D(Dog dog) {
        this.dog = dog;
    }

    public D(){
        this.dog = null;
    }

    void func6(){
        //this.dog = new Dog();
        this.dog.name = "Snoopy";
        System.out.println(y);
    }

    static void func7(){
        System.out.println("func7");
        System.out.println(x);
    }

    /*@Override
    void func5(final int param){

    }*/
}
class Dog{
    String name;

    private static int counter = 0;
    public Dog(){
        counter++;
    }

    public static int getCounter(){
        return counter;
    }

}
class MyClass{
    static int x = 3, y;
    int z = 70;
    static {
        System.out.println(x);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        System.out.println("in static constructor");
        x = sum;
        y = 18;
    }
    public MyClass(int z){
        System.out.println("in MyClass constructor (not the static one)");
        this.z = z;
        //y++;
    }

}










