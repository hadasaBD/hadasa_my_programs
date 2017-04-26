package com.company;

/**
 * Created by eladlavi on 12/02/2017.
 */
public class Circle {
    private Point center;
    private int radius;
    private int z;


    public class Point{
        private int x, y;
        private int z;

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

        public int getZ(){
            return Circle.this.z;
            //return this.z;
        }

        @Override
        public String toString() {
            return "(" + x + "," + y + ")";
        }
    }


    public Circle(int x, int y, int r){
        this.radius = r;
        this.center = this.new Point(x, y);
    }

    public Circle(){
        this(0, 0, 0);
    }

    @Override
    public String toString() {
        return "radius: " + radius + " center: " + center;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }
}
