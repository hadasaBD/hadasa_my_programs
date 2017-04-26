package graphicshape;

/**
 * Created by eladlavi on 05/02/2017.
 */
public abstract class Shape {



    public final void refresh(){
        System.out.println("refreshing");
    }

    public abstract double area();

    public abstract double perimeter();
}
