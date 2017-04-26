package com.company;

/**
 * Created by eladlavi on 22/02/2017.
 */
public class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("in equals");
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(obj instanceof Point){
            Point other = (Point)obj;
            return this.x == other.x && this.y == other.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        System.out.println("in hashCode");
        return 8;
        //return (7 * x) ^ (11 * y) ^ (53 * y);
    }
}
