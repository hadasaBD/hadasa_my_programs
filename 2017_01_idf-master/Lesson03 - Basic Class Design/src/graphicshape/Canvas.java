package graphicshape;

import com.company.Ball;

/**
 * Created by eladlavi on 05/02/2017.
 */
public class Canvas {

    private final boolean[][] canvas;


    public Canvas(){
        this.canvas = new boolean[10][10];
    }

    public void setCanvas(boolean[][] canvas){
        //this.canvas = canvas;
    }


    void draw(){
        Circle c = new Circle();
        c.x = 120;
        Ball b = new Ball();

        final int y = 5;
        //y++;

    }

    void refreshAll(final Shape[] shapes){
        for(Shape shape: shapes)
            shape.refresh();
    }
}
