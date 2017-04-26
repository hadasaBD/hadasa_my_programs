package com.company;

/**
 * Created by eladlavi on 14/02/2017.
 */
public class ArrayList implements List {

    int[] arr;
    int size;

    public ArrayList(){
        arr = new int[10];
        size = 0;
    }

    public ArrayList(int[] arr){
        this.arr = arr;
        this.size = arr.length;
    }

    @Override
    public void add(int x) {
        makeRoom();
        arr[size++] = x;
    }

    private void makeRoom(){
        if(size == arr.length){
            int[] temp = new int[size * 2];
            for (int i = 0; i < size; i++) {
                temp[i] = arr[i];
            }
            this.arr = temp;
        }
    }

    @Override
    public void add(int x, int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("what are you doing?");
        makeRoom();
        for (int i = size; i > index; i--) {
            arr[i] = arr[i-1];
        }
        arr[index] = x;
        size++;
    }

    @Override
    public void remove(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("what are you doing?");
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i+1];
        }
        size--;

    }

    @Override
    public void set(int index, int x) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("what are you doing?");
        arr[index] = x;
    }

    @Override
    public int get(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("what are you doing?");
        return arr[index];
    }

    @Override
    public int indexOf(int x) {
        for (int i = 0; i < size; i++) {
            if(arr[i] == x)
                return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        if(size == 0)
            return "{}";
        String s = "{";
        for (int i = 0; i < size - 1; i++) {
            s += arr[i] + ",";
        }
        s += arr[size - 1];
        s += "}";
        return s;
    }

    @Override
    public int[] toArray() {
        int[] temp = new int[size];
        for (int i = 0; i < size; i++) {
            temp[i] = arr[i];
        }
        return temp;
    }

    @Override
    public int size() {
        return size;
    }
}
