package com.company;

/**
 * Created by hackeru on 3/21/2017.
 */
public class ListenerRealization {
    Output myOutput;
    public void StartDetect() {

        myOutput.output("the action is start \nthe time now is"+System.nanoTime() );
    }

    public void EndDetect() {
        myOutput.output("the action is end \nthe time now is" + System.nanoTime());
    }
}
