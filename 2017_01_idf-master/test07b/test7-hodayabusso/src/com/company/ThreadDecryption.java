package com.company;

import static com.company.Menu.*;

/**
 * Created by TOSHIBA on 18/04/17.
 */
public class ThreadDecryption extends  Thread {


   // private int fromKey;
    //private int toKey;
    private byte[] arrayChars;
    private FoundKeyListener listener;
    private  int key;

    /*public ThreadDecryption(int fromKey, int toKey, byte[] arrayChars,FoundKeyListener listener) {
        this.fromKey = fromKey;
        this.toKey = toKey;
        this.arrayChars = arrayChars;
        this.listener=listener;
    }*/

    public ThreadDecryption(int key, byte[] arrayChars,FoundKeyListener listener) {
        this.key=key;
        this.arrayChars = arrayChars;
        this.listener=listener;
    }

    @Override
    public void run() {

            byte[] temp = new byte[arrayChars.length];

        //for (int i = fromKey; i < toKey; i++) {
                for (int j = 0; j < temp.length; j++) {
                    temp[j] = (byte) (arrayChars[j] -key);
                }
                int count = 0;
                String text = new String(temp);
                for (String word :words) {
                    if (contain(word, text)) {
                        count++;
                        if (count == 3) {
                            if(listener!=null)
                                listener.foundKey(key,text);
                            //System.out.println("the key:" + i);
                            //System.out.println(text);

                        }
                    }
                }
            }

    public boolean contain(String word,String text)
    {
        int index=text.indexOf(word);
        if(index==-1)
            return false;
        if(index!=0)
        {
            char beforeIndex=text.charAt(index-1);
            if (beforeIndex!='.' &&beforeIndex!=' ' && beforeIndex!=',')
                return false;
        }
        return true;

    }


    interface FoundKeyListener{
        void foundKey(int key,String text);
    }
}
