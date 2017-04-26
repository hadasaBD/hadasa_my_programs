package com.company;

/**
 * Created by eladlavi on 01/02/2017.
 */
public class Canvas {
    private boolean[][] canvas;

    public Canvas(int width, int height){
        canvas = new boolean[height][width];
    }

    public void drawRectangle(int x, int y, int width, int height){
        /*int col=0, row=0;

        for (int i = 0; i < y; i++)
            row++;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < x; j++)
                col++;
            for (int j = 0; j < width; j++) {
                if(i==0 || i==height-1 || j == 0 || j == width-1) {
                    canvas[row][col] = true;
                }
                col++;
            }
            row++;
            col = 0;
        }*/
        for (int i = 0; i < width; i++) {
            canvas[y][x+i] = true;
            canvas[y+height][x+i] = true;
        }
        for (int i = 0; i <= height; i++) {
            canvas[y+i][x] = true;
            canvas[y+i][x+width] = true;
        }


    }

    public void drawCircle(int a, int b, int radius){
        // (x, y)
        //  radius^2 = (x - a)^2 + (y - b)^2
    }

    public void render(){
        for (int i = 0; i < canvas.length; i++) {
            for (int j = 0; j < canvas[i].length; j++) {
                System.out.print(canvas[i][j] ? "*" : " ");
            }
            System.out.println();
        }
    }




}

