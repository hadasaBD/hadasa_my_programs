package com.company;
/**
 * Created by hackeru on 4/6/2017.
 */
import static com.company.T3.words;
public class TreadDecryptionT4 extends Thread{
    private int key;
    private char[] arrayChars;

    public TreadDecryptionT4(int key, char[] arrayChars) {
        this.key = key;
        this.arrayChars = arrayChars;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public char[] getArrayChars() {
        return arrayChars;
    }

    public void setArrayChars(char[] arrayChars) {
        this.arrayChars = arrayChars;
    }
////////////////////פתרתי בדרך יותר נכונה בתרגיל 3
    @Override
    public void run() {
        int counter=0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arrayChars.length; i++) {
            if(arrayChars[i]-key=='.'||arrayChars[i]-key==' '||arrayChars[i]-key==','){
                for (int j = 0; j <words.length ; j++) {
                    if(stringBuilder.toString().equals(words[j])) {
                        counter++;
                        break;
                    }
                }
                stringBuilder=new StringBuilder();
            }
            else{
                stringBuilder.append((char) (byte)(arrayChars[i]-key));
            }
        }

    }
}
