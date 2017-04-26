package com.company;

public class Main {

    public static void main(String[] args) {
        Writable writable1 = new Writable() {
            @Override
            public void write(String s) {
                System.out.println(s);
            }
        };

        writable1.write("hello");

        Writable writable2 = s -> System.out.println(s);


        writable2.write("hello");

        BinaryOperator multiplication = (x, y) -> x * y;

        BinaryOperator addition = (x, y) -> x + y;

        int z = multiplication.operate(5, 7);
        System.out.println(z);


        BinaryOperator power = (x, y) -> {
            int sum = x;
            for (int i = 0; i < y-1; i++) {
                sum *= x;
            }
            return sum;
        };



        Thread t = new Thread(()-> System.out.println("test"));
        t.start();

        Point[] points = {new Point(3, 4),
            new Point(7, 3),
            new Point(98, 1)};
        Point p = new Point(7, 3);

        //System.out.println(
        //        contains(points, p, (a, b) -> a.x==b.x && a.y==b.y));


        System.out.println(contains(points, p, new Equal() {
            @Override
            public boolean areEqual(Point a, Point b) {
                return a.x==b.x && a.y==b.y;
            }
        }));








        Button button = new Button();
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(Object obj) {
                System.out.println("button clicked");
            }
        });

        button.setOnClickListener(obj -> System.out.println("clicked"));




    }

    public static boolean contains(Point[] arr, Point z, Equal equal){
        for (int i = 0; i < arr.length; i++) {
            if(equal.areEqual(arr[i], z))
                return true;
        }
        return false;
    }
}
class Point{
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}




interface Equal{
    boolean areEqual(Point x, Point y);
}


interface Writable{
    void write(String s);
}

interface BinaryOperator{
    int operate(int x, int y);
}
interface OnClickListener{
    void onClick(Object obj);
}

class Button{
    private OnClickListener listener;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    private void func(){
        if(listener != null)
            listener.onClick(this);
    }
}













