package com.company;

/**
 * Created by hackeru on 4/20/2017.
 */
public class PrintInfoCrackListener implements FindKeyListener {
    Output output;
    public PrintInfoCrackListener(Output output) {
        this.output=output;
    }

    @Override
    public void findKey(int key, String value) {
        output.getOutput("key: "+key);
        output.getOutput("text: "+value);
    }
}
