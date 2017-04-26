package graphicshape;

/**
 * Created by eladlavi on 05/02/2017.
 */
public class Circle extends Shape {

    protected int x;
    int y;
    private int radius;

    public Circle(int x, int y, int radius){
        if(x >= 0)
            this.x = x;
        if(y >= 0)
            this.y = y;
        setRadius(radius);
    }

    public Circle(int radius){
        this(20, 20, radius);
    }

    public Circle(){
        this(10);
    }

    @Override
    public String toString() {
        return "center = (" + x + "," + y + ") and radius = " + radius;
    }

    @Override
    public double area(){
        return Math.PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    public void fillColor(int red, int green, int blue){

    }
    public void fillColor(float hue, float saturation, float brightness){
        //fillColor float, float, float
    }


    /**
     * sets the radius of the cirle
     * @param radius non negative radius length
     */
    public void setRadius(int radius) {
        if(radius < 0)
            return;
        this.radius = radius;
    }
}
