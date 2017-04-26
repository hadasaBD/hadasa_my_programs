package com.company;

import java.io.Closeable;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            int[] arr = new int[3];
            //arr[15] = 90;
            System.out.println("end of try");
            //throw new ClassCastException();
            throw new Exception("big balagan");

            //stam();
            //Animal a = new Dog();
            //Cat c = (Cat)a;

        }catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("there was an error");
            System.out.println(ex.getMessage());
        }catch (ClassCastException ex){

            System.out.println("there was a casting error");
        }catch (Exception ex){
            System.out.println("error: " + ex.getMessage());

        }


        try {
            stam2();
        } catch (Exception e) {
            //e.printStackTrace();
        }finally {
            System.out.println("in finally");
        }


        System.out.println("done");




        try(MyExpensiveResource myExpensiveResource =
                    new MyExpensiveResource();
            MyExpensiveResource myExpensiveResource2 =
                    new MyExpensiveResource()){

            myExpensiveResource.read();


        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            stam4();
        } catch (MyException e) {
            System.out.println(e.getImage());
        }

    }

    public static void stam(){
        throw new ArrayIndexOutOfBoundsException();
    }


    public static void stam2() throws Exception{
        int x;
        throw new Exception("something bad happened");
    }

    public static int stam3(){


        try{
            int x = 5;
            x++;
            return x;

        }catch (Exception ex){

        }finally {
            System.out.println("finally");
        }
        System.out.println("end");

        return 19;
    }

    public static void stam4() throws MyException {

        throw new MyException(8);
    }



}
class Animal{

}
class Dog extends Animal{

}
class Cat extends Animal{

}

class MyExpensiveResource implements Closeable{


    public void read(){

    }

    @Override
    public void close() throws IOException {
        System.out.println("closing expensive resources...");
    }
}


class MyException extends Exception{
    private int image;


    public MyException(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}