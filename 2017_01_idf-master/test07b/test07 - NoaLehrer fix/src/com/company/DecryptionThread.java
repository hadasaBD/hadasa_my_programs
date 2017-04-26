package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hackeru on 4/6/2017.
 */
public class DecryptionThread extends Thread{
    public static final int SIZE = 1000;
    public Map<String,Integer> map;
    byte[] text=new byte[SIZE];
    int indexOfAlgorithm;
    byte from,to;
    FindIndexListener findIndexListener;



    public DecryptionThread(Map<String, Integer> map, byte[] text,byte from,byte to,FindIndexListener findIndexListener) {
        this.map = map;
        this.from=from;
        this.to=to;
        this.text=text;
        this.findIndexListener=findIndexListener;
        indexOfAlgorithm=0;
    }

    @Override
    public void run() {
        while (from != to) {
            indexOfAlgorithm = 0;
            if (!decrypt(text, from)) {
                if (findIndexListener != null)
                    findIndexListener.find(from);
                break;
            }
            from++;
        }
    }

    private boolean decrypt(byte[] text,int index) {
        byte[] newText=new byte[SIZE];
        for (int i = 0; i <SIZE&&text[i]!=0 ; i++) {
            newText[i]=text[i];
        }
        int i=0;
        StringBuilder stringBuilder=new StringBuilder();
        while (newText[i]!=0&&i<SIZE){
            newText[i]= (byte)(newText[i]-index);
            if(newText[i]==' '||newText[i]=='.'||newText[i]==','){
                if(!check(stringBuilder)){return false;}
                stringBuilder.delete(0,stringBuilder.length());
            }
            else {
                stringBuilder.append((char)newText[i]);
            }
            i++;
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
