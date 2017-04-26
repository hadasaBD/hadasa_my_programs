package com.company;

public class Main {

    public static void main(String[] args) {
        //System.out.println("welcome to hackeru");


        byte myByte; //-128 to 127
        myByte = 100;
        short myShort = 120; //2 bytes
        int myInt;// 4 bytes
        long myLong = 4234234L; // 8 bytes
        myByte = 127;
        myByte++;
        //System.out.println(myByte);
        float myFloat = 123.4f; //4 bytes
        double myDouble = 123.4; //8 bytes


        int x = 5;
        x = (5 + 3) * 2;
        x = 5 - 2;
        x = 5 * 2;
        x = 5 % 2;
        int y = 3;
        x = y + 1;
        x = x + 5;
        x += 5;
        x += 1;
        x++;
        ++x;
        x = 5;
        y = x++; //y = 5, x = 6
        char myChar = 'c';
        boolean myBoolean = true;
        myBoolean = 5 <= 7;
        myBoolean = 5 != 7;
        myBoolean = 5 == 7;
        myBoolean = !(5 == 7);
        myBoolean = 5 < 7 && 7%2 == 0;
        myBoolean = 5 < 7 || 7%2 == 0;
        myBoolean = (5<7 && 8<10) || 3!=5;
        //2*(3+4) = 2*3 + 2*4
        /*boolean a = false, b = true;
        boolean c = !(a && b);
        c = !a || !b;
        if(5<7)
            System.out.println("5 is less than 7");
        else
            System.out.println("5 is greater than or equal to 7");
        int age = 15;
        if(age < 18)
            System.out.println("you may not drink alcohol");
        else if (age < 21)
            System.out.println("you may drink with your parents");
        else
            System.out.println("you may drink alcohol");

        switch (age){
            case 18:
                System.out.println("age is exactly 18");

            case 21:
                System.out.println("age is exactly 21");
                break;
            case 35:
                break;
            default:
                break;
        }
        System.out.println("1" + 1 + 1); //prints 111
        System.out.println(1 + 1 + "1"); //prints 21
        System.out.println("1" + (1 + 1)); //prints 12*/
        /*int i = 0;
        while(i < 10){
            System.out.println("i = " + i);
            i++;
        }*/

        /*for(int i = 0; i < 10; i++){
            System.out.println(i);
        }*/

        /*x = 0;
        do{
            System.out.println(x);
            x++;
        }while(x<10);*/


        /*
        int z = 0;
        if(x < 10)
            z = 100;
        else
            z = 200;
        */
        //int z = x < 10 ? 100 : 200;


        //System.out.println("distance: " + distance(5,8));
        //System.out.println("product: " + product(7,3));
        //System.out.println("quotient: " + quotient(7,3));
        //System.out.println("remainder: " + remainder(7,3));
        //System.out.println("power: " + power(2, 3));


        //x = 5 * 1025;
        //x = 5 * 1024 + 5
        //bitwise operator
        //x = 3 << 3;

        //System.out.println(fib(150));
        int[] a = {4, 7, 1, 3, 6, 8};
        a[0] = 15;
        int[] b = new int[6];
        b[0] = 4;
        b[1] = 7;
        b[2] = 1;
        b[5] = 8;
        x = 6;
        int[] c = new int[x];


    }


    static int fib(int n){
        if(n==1 || n==2)
            return 1;
        //return fib(n-1) + fib(n-2);
        int a = 0;
        int b = 1;
        int c;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    static int distance(int x, int y){
        /*int result = x - y;
        if(result < 0)
            result *= -1;
        return result;*/

        int small = x;
        int large = y;
        if(x > y){
            small = y;
            large = x;
        }
        int result = 0;

        while(small + result < large)
            result++;
        return result;

    }

    /*static int product(int x, int y){

        int small = x;
        int large = y;
        if(x > y){
            small = y;
            large = x;
        }
        int result = 0;
        for (int i = 0; i < small; i++) {
            result += large;
        }
        return result;
    }*/

    // 21     7 * 0
    static int product(int x, int y){
        int result = 0;
        while(y > 0){
            if(y%2 == 0){
                y = y >> 1;
                x = x << 1;
            }else{
                y--;
                result += x;
            }
        }
        return result;
    }

    static int quotient(int x, int y){
        if(y==0)
            return -1;
        int result = 0;
        int sum = y;
        while(sum <= x){
            sum += y;
            result++;
        }
        return result;
    }

    static int remainder(int x, int y){
        if(y==0)
            return -1;
        return distance(x, product(quotient(x, y), y));
    }




    /*static int power(int x, int y){
        if(x == 0){
            if(y == 0)
                return -1;
            return 0;
        }
        if(y == 0 || x == 1)
            return 1;
        int result = x;
        for (int i = 1; i < y; i++) {
            result *= x;
        }
        return result;
    }*/

    /*static int power(int x, int y){
        if(x == 0){
            if(y == 0)
                return -1;
            return 0;
        }
        if(y == 0 || x == 1)
            return 1;
        return power(x,y-1) * x;
    }*/

    static int power(int x, int y){
        if(x == 0){
            if(y == 0)
                return -1;
            return 0;
        }
        if(y == 0 || x == 1)
            return 1;
        if(y%2 == 0){ //(y&1) != 1
            int temp = power(x, y/2);
            return temp * temp;
        }else{
            int temp = power(x, (y-1)/2);
            return temp * temp * x;
        }
    }

    static int sqrt(int x){
        int result = 0;
        while(result * result < x)
            result++;
        return result;
    }

    //power(7,4)
    //power(7,3) * 7
    //(power(7,2) * 7) * 7
    //((power(7,2)) * 7) * 7
    //((power(7,1) * 7) * 7) * 7
    //(((power(7,0) * 7) * 7) * 7) * 7
    //((((1) * 7) * 7) * 7) * 7
    //((((1) * 7) * 7) * 7) * 7

}

