package com.company;

/**
 * Created by hackeru on 19.04.2017.
 */
public class HackListener implements Hack.MatchFound{

    int realKey = Integer.MIN_VALUE;

    @Override
    public void found(int key, String text) {
        if (key != Integer.MAX_VALUE)
            realKey = key;
    }

}
