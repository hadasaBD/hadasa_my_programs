package com.company;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
          Menu.menu();
        //checkCipher();
    }


   /* private static void checkCipher() {
        Set<String> commonWords = new HashSet<>();
        commonWords.add("the");
        commonWords.add("be");
        commonWords.add("to");
        commonWords.add("of");
        commonWords.add("and");
        commonWords.add("a");
        commonWords.add("in");


        String stringWords = "the, be, to, sivan, This";
        byte [] bytesWords = stringWords.getBytes();

        for (int i=0; i< bytesWords.length;i++){
            bytesWords[i] +=7;
        }

        ThreadCipher.FoundListener listener1 = key -> {
            System.out.println("The key is:"+key);
            System.out.print("the array is: ");
            for(int i=0; i<bytesWords.length;i++)
                bytesWords[i]-=key;
            String s =new String(bytesWords);
            System.out.println(s);
        };

        ThreadCipher threadCipher1=new ThreadCipher(bytesWords,commonWords,1,5,listener1);
        threadCipher1.run();
        ThreadCipher threadCipher2=new ThreadCipher(bytesWords,commonWords,5,10,listener1);
        threadCipher2.run();

    }*/
}
