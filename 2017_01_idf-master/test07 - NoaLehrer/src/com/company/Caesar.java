package com.company;

import java.util.Map;

/**
 * Created by hackeru on 4/6/2017.
 */
public class Caesar {
    public static final int SIZE = 1000;
    public Map<String,Integer> map;
    int indexOfAlgorithm;

    public Caesar(byte[] text,Map<String,Integer> map) {
        this.map=map;
        byte key=1;
        for (int i=0; i <255 ; key++,i++) {
            indexOfAlgorithm=0;
            if(!decrypt(text,key))break;
        }
        int index=0;
        System.out.println(key);
        while (text[index++]!=0&&index< SIZE) {
            char c= (char) (text[index] - key);
            System.out.print(c);
        }
    }

    private boolean decrypt(byte[] text, byte index) {
        int i=0;
        byte[] tempChar=new byte[SIZE];
        for (int j = 0; j <SIZE ; j++) {
            tempChar[j]=text[j];
        }
        StringBuilder stringBuilder=new StringBuilder();
        while (tempChar[i++]!=0&&i<SIZE){
            tempChar[i]= (byte)(tempChar[i]-index);
            if(tempChar[i]==' '||tempChar[i]=='.'||tempChar[i]==','){
                if(!check(stringBuilder)){return false;}
                stringBuilder.delete(0,stringBuilder.length());
            }
            else {
                stringBuilder.append((char)tempChar[i]);
            }
        }
        return true;
    }

    private boolean check(StringBuilder stringBuilder) {
        if(map.get(stringBuilder.toString())!=null){
            indexOfAlgorithm++;
            if(indexOfAlgorithm==3){
                return false;
            }
        }
        return true;
    }
}
