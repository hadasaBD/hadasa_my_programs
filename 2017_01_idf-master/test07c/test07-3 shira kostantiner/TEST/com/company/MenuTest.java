package com.company;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class MenuTest {


    @Test
    void addTest() {
      Menu menu = new Menu();
        menu.checkWord("shira,shira");
        if (menu.commonWords.contains("shira shira"))
            Assertions.fail("add word is exists ");

    }

}