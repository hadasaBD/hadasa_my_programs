package com.company;

import java.util.Iterator;

/**
 * Created by eladlavi on 14/02/2017.
 */
public class LinkedList implements List, Iterable, Iterator {

    private Node anchor;
    private Node last;
    private int size;
    private Node current;

    public LinkedList() {
        anchor = new Node(123);
        size = 0;
        last = anchor;
    }

    @Override
    public void add(int x) {
        /*Node last = anchor;
        while (last.next != null)
            last = last.next;*/
        last.next = new Node(x);
        last = last.next;
        size++;

    }

    @Override
    public void add(int x, int index) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        if(index == size){
            add(x);
            return;
        }
        Node theOneBefore = anchor;
        for (int i = 0; i < index; i++)
            theOneBefore = theOneBefore.next;
        Node added = new Node(x);
        added.next = theOneBefore.next;
        theOneBefore.next = added;
        size++;
    }

    @Override
    public void remove(int index) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        Node theOneBefore = anchor;
        for (int i = 0; i < index; i++)
            theOneBefore = theOneBefore.next;
        if(index == size){
            last = theOneBefore;
        }
        theOneBefore.next = theOneBefore.next.next;
        size--;
    }

    @Override
    public void set(int index, int x) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        Node node = anchor;
        for (int i = 0; i <= index; i++) {
            node = node.next;
        }
        node.value = x;

    }

    @Override
    public int get(int index) {
        if(index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        Node node = anchor;
        for (int i = 0; i <= index; i++) {
            node = node.next;
        }
        return node.value;
    }

    @Override
    public int indexOf(int x) {
        Node node = anchor.next;
        for (int i = 0; i < size; i++, node = node.next) {
            if(node.value == x)
                return i;
        }
        return -1;
    }

    @Override
    public int[] toArray() {
        int[] temp = new int[size];
        Node node = anchor.next;
        for (int i = 0; i < size; i++, node = node.next) {
            temp[i] = node.value;
        }
        return temp;
    }

    @Override
    public String toString() {
        if(size == 0)
            return "{}";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        Node node = anchor.next;
        for (int i = 0; i < size - 1; i++, node = node.next) {
            stringBuilder.append(node.value + ",");
        }
        stringBuilder.append(node.value);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public Iterator iterator() {
        current = anchor;
        return this;
    }

    @Override
    public boolean hasNext() {
        return current.next != null;
    }

    @Override
    public Object next() {
        current = current.next;
        return current.value;
    }

    private static class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            next = null;

        }

    }


    @Override
    public int size() {
        return size;
    }










}
