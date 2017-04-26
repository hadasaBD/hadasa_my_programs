package graphicshape;

import java.security.InvalidParameterException;

/**
 * Created by eladlavi on 07/02/2017.
 */
public class Parallelogram extends Shape {
    private Point p1, p2, p3, p4;


    /**
     * make sure that p1p2 is equal in length and parallel to p3p4
     * and p1p3 is parallel to p2p4
     * throws exception otherwise
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     */
    public Parallelogram(Point p1, Point p2, Point p3, Point p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;


        Segment seg1 = new Segment(p1, p2);
        Segment seg2 = new Segment(p3, p4);
        if(seg1.slope() != seg2.slope() ||
                seg1.getLength() != seg2.getLength())
            throw new InvalidParameterException("no a parallelogram");
        Segment seg3 = new Segment(p1, p3);
        Segment seg4 = new Segment(p2,p4);
        if(seg3.slope() != seg4.slope())
            throw new InvalidParameterException("no a parallelogram");


        //we need to do set of checks in order to know
        //how to build a parallelogram from these four points
        //if any.
    }

    @Override
    public double perimeter() {
        Segment seg1 = new Segment(p1, p2);
        Segment seg2 = new Segment(p2, p4);
        return 2 * (seg1.getLength() + seg2.getLength());
    }

    @Override
    public double area() {
        return new Triangle(p1, p2, p3).area() * 2;
    }
}
