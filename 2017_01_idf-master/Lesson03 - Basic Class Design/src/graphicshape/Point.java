package graphicshape;

/**
 * Created by eladlavi on 07/02/2017.
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        setX(x);
        setY(y);
    }

    //clone constructor
    public Point(Point other){
        this(other.x, other.y);
    }

    public int getX() {
        return this.x;
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
        return "(" + commaSeparated() + ")";
    }

    protected String commaSeparated(){
        return x + "," + y;
    }
}
