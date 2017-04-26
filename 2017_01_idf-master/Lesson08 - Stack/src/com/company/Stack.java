package com.company;

/**
 * Created by eladlavi on 21/02/2017.
 */
public interface Stack<T> {
    void push(T element);
    T pop();
    boolean isEmpty();
}
