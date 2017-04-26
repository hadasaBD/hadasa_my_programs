package com.company;

import java.util.Random;

/**
 * Created by eladlavi on 22/03/2017.
 */
public class RandomKeyGenerator implements KeyGenerator {

    private Random random;

    public RandomKeyGenerator() {
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public int getKey() {
        return random.nextInt();
    }
}
