package com.company;

/**
 * Created by hackeru on 20.04.2017.
 */
class HackTest {

    int actualKey;
    String actualMess;
    boolean crackSuccses;


 /*   @Test
    public void testHack(){
        actualMess="this is the original message";
        byte[] msgBytes=actualMess.getBytes();
        int key=13;
        for (int i = 0; i <msgBytes.length ; i++) {
            msgBytes[i]=(byte)((int)msgBytes[i]+ actualKey);
        }
        HashSet<String>commonWords=new HashSet<>();
        commonWords.add("is");
        commonWords.add("this");
        commonWords.add("the");
         crackSuccses=false;
        Hack.hack(msgBytes, commonWords, new Hack.MatchFound() {
            @Override
            public void found(int key, String crackedText) {
                if (key == actualKey && actualMess.equals(crackedText))
                    crackSuccses = true;
            }
        });
        if (!crackSuccses)
            fail("did not crack the message");
    }
*/
}