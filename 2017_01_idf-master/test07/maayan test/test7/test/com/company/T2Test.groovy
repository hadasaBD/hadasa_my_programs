package com.company

import org.junit.Test

/**
 * Created by hackeru on 4/6/2017.
 */
class T2Test extends GroovyTestCase {
    @Test
    void addWord() {
        Set<String> listWord;
        Set<String> list =new HashSet<>();
        OutputInterface outputInterface=new OutputInterface() {
            int counter;

            @Override
            void getOutput(String print) {
                counter++;
                if(!print.equals("the word"+counter+"is find"))
                    fail();
            }
        }
        T1 t1=new T1(list,outputInterface);
        t1.addWord("and,and");
    }
}
