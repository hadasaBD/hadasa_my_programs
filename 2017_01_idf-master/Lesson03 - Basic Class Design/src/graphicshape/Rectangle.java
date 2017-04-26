package graphicshape;



//p1(x1, y1)  p2(x2, y2)   deltaY/deltaX
//m=(y1-y2)/(x1-x2)
//m1 m2    m1 * m2 = -1

/**
 * Created by eladlavi on 06/02/2017.
 */
public class Rectangle extends Shape {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        setWidth(width);
        setHeight(height);
    }



    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if(width < 0)
            return;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if(height < 0)
            return;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public double perimeter() {
        return 2 * (width + height);
    }
}
