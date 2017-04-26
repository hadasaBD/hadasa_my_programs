package graphicshape;

/**
 * Created by eladlavi on 07/02/2017.
 */
public class Segment {

    private Point p1, p2;
    private double length;
    private boolean isLengthCalculated;

    public Segment(Point p1, Point p2) {
        setP1(p1);
        setP2(p2);
    }

    public Point getP1() {
        //return new Point(p1.getX(), p1.getY());
        return new Point(p1);
    }

    public void setP1(Point p1) {
        this.p1 = new Point(p1);
        isLengthCalculated = false;
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
        isLengthCalculated = false;
    }

    public void calculateLength(){
        int deltaY = p1.getY() - p2.getY();
        int deltaX = p1.getX() - p2.getX();
        length = Math.sqrt(deltaX*deltaX + deltaY*deltaY);
        isLengthCalculated = true;
    }

    public double slope(){
        double deltaY = p1.getY() - p2.getY();
        double deltaX = p1.getX() - p2.getX();
        if(deltaX == 0)
            return Double.MAX_VALUE;
        return deltaY / deltaX;
    }

    public double getLength(){
        if(!isLengthCalculated)
            calculateLength();
        return length;
    }

    /**
     * the line that goes through the two points p1,p2
     * that determine this segment can be represented
     * as Ax + By + C = 0
     * @return returns the A of the equation
     */
    public double A(){
        return -1*slope();
    }

    public double B(){
        return 1;
    }

    public double C(){
        return slope()*p1.getX() - p1.getY();
    }

    public double distanceToPoint(Point point){
        double A = A();
        double numerator = A * point.getX() + B() * point.getY()
                + C();
        if(numerator < 0)
            numerator *= -1;
        double denominator = Math.sqrt(A*A + B()*B());
        //B()*B*()==1
        return numerator / denominator;
    }
}
