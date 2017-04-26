package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hackeru on 4/19/2017.
 */
class MenuTest {
    @Test
    void MyInsertToList() {

        String word = "hello";
        String word2 = "sari";
        Menu menu = new Menu();
        menu.insertToList(word);
        menu.insertToList(word2);
        for (String s : Menu.listOfWords)
            if (s != "sari" || s != "hello")
                Assertions.fail("the word not insert to the list");
        if (Menu.listOfWords.isEmpty())
            Assertions.fail("not Succeed");


    }

    @Test
    void MyEnterStringFuncFromUser() {
        Menu menu = new Menu();
        String string = "sari,hello";
        menu.enterStringFuncFromUser(string);
        if (Menu.listOfWords.size() != 2)
            Assertions.fail("not valid");


    }

}