package com.company;

/**
 * Created by eladlavi on 16/02/2017.
 */
public class Node<T> {
    T value;
    Node<T> next;
    Node<T> previous;

    public Node(T value) {
        this.value = value;
    }


}

