package com.company;

/**
 * Created by eladlavi on 7/6/15.
 */
public class MyString implements Comparable<MyString> {
    private char[] chars;

    public MyString(char[] chars){
        this.chars = chars;
    }


    //hello    ll  2   lg -1 eo -1 lo 3
    //"how are you today?"    "are"->4
    public int indexOf(MyString another){
        if(another == null || another.chars.length == 0
                || another.chars.length>this.chars.length)
            return -1;
        final char firstChar = another.chars[0];
        final int lastPositionToCheck = this.chars.length - another.chars.length;
        for (int i = 0; i <= lastPositionToCheck; i++) {
            if(this.chars[i] == firstChar){
                boolean noMatch = false;
                for (int j = i+1; j < i+another.chars.length; j++) {
                    if(this.chars[j] != another.chars[j-i]) {
                        noMatch = true;
                        break;
                    }
                }
                if(!noMatch)
                    return i;
            }
        }

        return -1;
    }

    //{5, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0......}
    //"hello world today"   ' '    {"hello", "world", "today"}
    public MyString[] split(char delimeter){
        int delimeterCounter = 0;
        int[] wordsLength = new int[this.chars.length+1];
        for (int i = 0; i < this.chars.length; i++) {
            if(this.chars[i] == delimeter) {
                delimeterCounter++;
            }else{
                wordsLength[delimeterCounter]++;
            }
        }
        MyString[] myStrings = new MyString[delimeterCounter+1];
        int position = 0;
        for (int i = 0; i < myStrings.length; i++) {
            char[] wordChars = new char[wordsLength[i]];
            for (int j = 0; j < wordsLength[i]; j++) {
                wordChars[j] = this.chars[position++];
            }
            myStrings[i] = new MyString(wordChars);
            position++;
        }

        return myStrings;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(obj == this)
            return true;
        if(obj instanceof MyString){
            MyString other = (MyString)obj;
            if(this.chars.length != other.chars.length)
                return false;
            return indexOf(other) == 0;
        }
        return false;
    }

    @Override
    public String toString() {
        return new String(chars);
    }

    @Override
    public int compareTo(MyString o) {
        int n = chars.length;

        if(o.chars.length < n)
            n = o.chars.length;
        for (int i = 0; i < n; i++) {
            int asciiCodeThis = (int)chars[i];
            int asciiCodeOther = (int)o.chars[i];
            if(asciiCodeThis<asciiCodeOther)
                return 1;
            else if(asciiCodeOther<asciiCodeThis)
                return -1;
        }
        if(this.chars.length == o.chars.length)
            return 0;
        if(this.chars.length < o.chars.length)
            return 1;
        return -1;
    }

    public MyString toUpper(){
        char[] charsUpper = new char[chars.length];
        for (int i = 0; i < charsUpper.length; i++) {
            int asciiCode = (int)chars[i];
            if(asciiCode >= 97 && asciiCode <= 122){
                asciiCode -= 32;
                charsUpper[i] = (char)asciiCode;
            }else{
                charsUpper[i] = chars[i];
            }
        }
        return new MyString(charsUpper);
    }

    @Override
    public int hashCode() {
        int n = chars.length - 1;

        byte a = (byte) chars[0];

        byte b = (byte) chars[n/4];
        byte c = (byte) chars[3*n/4];
        byte d = (byte) chars[n];

        int hash = 0;
        hash |= (a & 0xFF);
        hash <<= 8;
        hash |= (b & 0xFF);
        hash <<= 8;
        hash |= (c & 0xFF);
        hash <<= 8;
        hash |= (d & 0xFF);

        return hash;



    }
}











