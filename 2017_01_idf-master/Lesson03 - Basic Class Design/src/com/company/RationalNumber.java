package com.company;

import java.security.InvalidParameterException;

/**
 * Created by eladlavi on 06/02/2017.
 */
public class RationalNumber extends Number {

    private int numerator;
    private int denominator;

    public RationalNumber(int numerator, int denominator) {
        if(denominator == 0)
            throw new InvalidParameterException("division by zero");
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public int intValue() {
        return numerator / denominator;
    }

    @Override
    public long longValue() {
        return intValue();
    }

    @Override
    public float floatValue() {
        return (float)numerator / denominator;
    }

    @Override
    public double doubleValue() {
        return (double)numerator / denominator;
    }

    public void add(RationalNumber num){
        //this.denominator = 15    num.denominator = 10
        //gcd = 5
        //a = 15 / 5 = 3
        //b = 10 / 5 = 2
        int gcd = gcd(this.denominator, num.denominator);
        int a = this.denominator / gcd;
        int b = num.denominator / gcd;
        this.denominator *= b;
        this.numerator *= b;
        this.numerator += num.numerator * a;
        reduce();
    }

    public void reduce(){
        int gcd = gcd(this.denominator, this.numerator);
        this.denominator /= gcd;
        this.numerator /= gcd;
    }


    public static int gcd(int x, int y){
        if(x == 0)
            return y;
        return gcd(y%x, x);
    }

}
