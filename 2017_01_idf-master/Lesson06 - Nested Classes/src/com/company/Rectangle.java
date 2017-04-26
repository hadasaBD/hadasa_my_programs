package com.company;

/**
 * Created by eladlavi on 12/02/2017.
 */
public class Rectangle {

    int width, height;

    public Rectangle makeMeSquare(){
        class Square extends Rectangle{
            public Square(int side){
                this.width = side;
            }
            public int getSide(){
                return this.width;
            }

            @Override
            public String toString() {
                return "I am a square with side " + width;
            }
        }
        Square s = new Square(width);
        s.getSide();
        return s;
    }




    @Override
    public String toString() {
        return "I am a rectangle width: " + width
                +" height: " + height;
    }
}

